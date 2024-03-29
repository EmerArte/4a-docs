from rest_framework import generics, status
from authApp.models.user import User
from authApp.serializer.userSerializer import UsuarioSerializer
from django.conf import settings
from rest_framework.response import Response
from rest_framework_simplejwt.views import TokenVerifyView
from rest_framework_simplejwt.backends import TokenBackend
from rest_framework_simplejwt.exceptions import InvalidToken, TokenError
from rest_framework_simplejwt.serializers import TokenVerifySerializer
#ESTE MÉTODO CREA Y LISTA TODOS LOS USUARIOS
class UserDetailView(generics.ListCreateAPIView):
    queryset = User.objects.all()
    serializer_class = UsuarioSerializer
    def post(self, request, *args, **kwargs):
        serializer = UsuarioSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)
#ESTE MÉTODO RETORNA LOS DATOS DE UN SOLO USUARIO
class UsuarioDetallesView(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UsuarioSerializer

#VALIDAR EL TOKEN DEVUELVE EL ID DEL PROPIETARIO DEL TOKEN
class VerifyTokenView(TokenVerifyView):
    def post(self, request, *args, **kwargs):
        serializer = TokenVerifySerializer(data=request.data)
        tokenBackend = TokenBackend(algorithm=settings.SIMPLE_JWT['ALGORITHM'])
        try:
            serializer.is_valid(raise_exception=True)
            token_data = tokenBackend.decode(request.data['token'],verify=False)
            serializer.validated_data['num_doc_id'] = token_data['user_id']
        except TokenError as e:
            raise InvalidToken(e.args[0])
        return Response(serializer.validated_data, status=status.HTTP_200_OK)

#ACTUALIZA Y ELIMINA USUARIOS
class UserUpdateAndDestoyView(generics.RetrieveUpdateDestroyAPIView):
    queryset = User.objects.all()
    serializer_class=UsuarioSerializer
    def delete(self, request, *args, **kwargs):
        datos = User.objects.filter(num_doc_id = kwargs['pk'])
        datos.delete()
        return Response({'mensaje':'Usuario eliminado correctamente'}, status=status.HTTP_200_OK)