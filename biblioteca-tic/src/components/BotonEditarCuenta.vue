<template>
  <div class="text-center mx-1">
    <v-dialog v-model="dialog" max-width="600px">
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
            <v-col cols="12">
              <v-text-field
                v-model="telefono"
                label="Teléfono"
                type="number"
                required
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
              <v-btn block color="primary" @click="guardar">
                Guardar Cambios</v-btn
              >
            </v-col>
            <v-col cols="12" md="4">
              <v-btn block color="blue" text @click="dialog = !dialog">
                Cancelar
              </v-btn>
            </v-col>
          </v-row>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import gql from "graphql-tag";
import jwt_decode from "jwt-decode";
export default {
  data: () => ({
    user_id: 0,
    dialog: false,
    items: ["C.C", "C.E", "T.I"],
  }),
  props: {
    nombre: String,
    apellido: String,
    contrasena: String,
    telefono: Number,
    num_doc: Number,
    tipodedoc: String,
  },
  methods: {
    guardar() {
      this.user_id = jwt_decode(localStorage.getItem("refresh")).user_id;
      this.$apollo
        .mutate({
          mutation: gql`
            mutation ($numDocId: Float!, $userInput: SignUpInput) {
              updateUser(num_doc_id: $numDocId, userInput: $userInput) {
                num_doc_id
                password
                nombre
                apellido
                telefono
                tipo_doc_id
              }
            }
          `,
          variables: {
            numDocId:  this.user_id,
            userInput:{
              num_doc_id: parseInt(this.num_doc),
              password: this.contrasena,
              nombre: this.nombre,
              apellido: this.apellido,
              telefono: parseInt(this.telefono),
              tipo_doc_id: this.tipodedoc
            
            }
          },
        })
        .then((result) => {
           this.$swal.fire({
            position: "Center",
            icon: "success",
            title: "Datos actualizados correctamente",
            showConfirmButton: false,
            timer: 1500,
          })
          this.$router.go()
        })
        .catch((err) => {
         this.$swal.fire({
            position: "Center",
            icon: "Error",
            title: "Error! ",
            showConfirmButton: false,
            timer: 1500,
          })
        });

      this.dialog = !this.dialog;
    },
  },
};
</script>

<style>
.v-dialog {
  overflow-y: hidden !important;
}
</style>