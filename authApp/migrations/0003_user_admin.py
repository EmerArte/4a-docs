# Generated by Django 3.2.9 on 2021-12-12 00:48

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('authApp', '0002_alter_user_password'),
    ]

    operations = [
        migrations.AddField(
            model_name='user',
            name='admin',
            field=models.BooleanField(default=False, verbose_name='admin'),
        ),
    ]
