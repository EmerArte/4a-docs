<template>
<div class="text-center mx-1">
    <v-dialog
      v-model="dialog"
      max-width="600px"
      >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="black"
          dark
          outlined
          elevation="0"
          v-bind="attrs"
          v-on="on"
        >
          Editar Cuenta
        </v-btn>
      </template>
      <v-card>
      <v-card-text>
        <h1 class="text-center pb-6">Crear cuenta</h1>
        <v-row>
          <v-col cols="12" md="3">
            <v-combobox
              v-model="tipodedoc"
              :items="items"
              outlined
              label="Tipo de documento"
            ></v-combobox>
          </v-col>
          <v-col cols="12" md="9">
            <v-text-field
              v-model="num_doc"
              label="Número de documento"
              required
              outlined
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field
              v-model="nombre"
              label="Nombre"
              required
              outlined
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field
              v-model="apellido"
              label="Apellido"
              required
              outlined
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="8">
            <v-text-field
              v-model="telefono"
              label="Teléfono"
              type="number"
              required
              outlined
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="4">
            <v-text-field
              v-model="edad"
              label="Edad"
              type="number"
              required
              outlined
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              v-model="email"
              label="Correo"
              required
              type="email"
              outlined
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              v-model="contrasena"
              label="Contraseña"
              type="password"
              required
              outlined
            ></v-text-field>
          </v-col>
        </v-row>
      </v-card-text>
      <v-card-actions>
        <v-row>
          <v-col cols="12" md="8">
            <v-btn block color="yellow" @click="guardar">
              Guardar Cambios</v-btn
            >
          </v-col>
          <v-col cols="12" md="4">
            <v-btn block color="blue" text @click="dialog=!dialog"> Cancelar </v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
    </v-dialog>
  </div>
</template>

<script>
  export default {
    data: () => ({
      dialog: false,
      items: ["C.C", "C.E", "T.I"],
    }),
    props:{
      nombre:String,
      apellido:String,
      edad:Number,
      email:String,
      contrasena:String,
      telefono:Number,
      num_doc:Number,
      tipodedoc:String,

    },
    methods:{
      guardar(){
          this.dialog=!this.dialog
      }
    }
  }
</script>

<style>
.v-dialog{
  overflow-y: hidden !important
}
</style>