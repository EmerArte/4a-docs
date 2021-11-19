from rest_framework import serializers
from authApp.models.user import User

class UsuarioSerializer(serializers.ModelSerializer):
    #Reserva = reserva_Serializer()
    class Meta:
        model = User
        fields = ['id','nombre', 'apellido', 'password', 'telefono', 'tipo_doc_id', 'num_doc_id']