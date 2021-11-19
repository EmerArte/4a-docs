from django.db import models
from django.contrib.auth.models import AbstractBaseUser, PermissionsMixin, BaseUserManager
from django.contrib.auth.hashers import make_password

class UserManager(BaseUserManager):
    def create_user(self, num_doc_id, password=None):
        """Creates and saves a user with the given username and password."""
        if not num_doc_id:
            raise ValueError('Users must have an username')
        user = self.model(num_doc_id=num_doc_id)
        user.set_password(password)
        user.save(using=self._db)
        return user
    def create_superuser(self, num_doc_id, password):
        """Creates and saves a superuser with the given username and password."""
        user = self.create_user(
        num_doc_id=num_doc_id,
        password=password,
        )
        user.is_admin = True
        user.save(using=self._db)
        return user

class User(AbstractBaseUser, PermissionsMixin):
    nombre = models.CharField('nombre', max_length = 30, null=False )
    apellido = models.CharField('apellido', max_length = 30, null=False)
    password = models.CharField('password', max_length = 256, null=False)
    telefono = models.BigIntegerField('telefono', unique=True)
    tipo_doc_id = models.CharField('tipo_doc_id', max_length = 30, null=False)
    num_doc_id = models.BigIntegerField('num_doc_id', primary_key=True)
    def save(self, **kwargs):
        some_salt = 'mMUj0DrIK6vgtdIYepkIxN'
        self.password = make_password(self.password, some_salt)
        super().save(**kwargs)
    objects = UserManager()
    USERNAME_FIELD = 'num_doc_id'