<template>
  <v-container grid-list-xs>
    <v-card outlined max-width="700" class="mx-auto" v-model="card">
      <div class="display-1 font-weight-black text-center my-5">Registrarse</div>
      <v-divider class="mb-12"></v-divider>
      <div class="formulario">
        <v-form v-on:submit.prevent="registro">
          <v-row class="mx-3">
            <v-col cols="4">
              <v-select
                :items="items"
                label="Tipo de documento"
                outlined
                v-model="tipo_doc_id"
              ></v-select>
            </v-col>
            <v-col cols="8">
              <v-text-field
                label="Número de documento"
                outlined
                type="number"
                v-model="num_doc_id"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Nombre"
                outlined
                type="text"
                v-model="nombre"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Apellidos"
                outlined
                type="text"
                v-model="apellido"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Teléfono"
                outlined
                type="number"
                v-model="num_doc_id"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="password"
                label="Contraseña"
                :type="show1 ? 'text' : 'password'"
                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append="show1 = !show1"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
          <v-divider class="mt-12"></v-divider>
          <v-row class="ma-3">
            <v-col cols="12">
              <v-btn color="primary" type="submit" block> Registrarse </v-btn>
            </v-col>
          </v-row>
        </v-form>
      </div>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      card:null,
      items: ['C.C','C.E', 'T.I'],
      show1: false,
      tipo_doc_id: "",
      nombre: "",
      apellido: "",
      num_doc_id: null,
      password: "",
    };
  },
  methods:{
    registro: async function () {
      this.card = true;
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation ($userInput: SignUpInput!) {
              signUpUser(userInput: $userInput) {
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
            credentials: {
              num_doc_id: parseInt(this.num_doc_id, 10),
              password: this.password,
              tipo_doc_id: this.tipo_doc_id,
              telefono: parseInt(this.telefono, 10),
              nombre: this.nombre,
              apellido: this.apellido,
            },
          },
        })
        .then((result) => {
          let userData = {
            tipo_doc_id: result.data.signUpUser.tipo_doc_id,
            telefono: result.data.signUpUser.telefono,
            nombre: result.data.signUpUser.nombre,
            apellido: result.data.signUpUser.apellido,
            num_doc_id: result.data.signUpUser.num_doc_id,
          };
          console.log(userData);
          this.Swal.fire({
            position: "Center",
            icon: "success",
            title: userData.nombre + " Te has registrado correctamente",
            showConfirmButton: false,
            timer: 1500,
          });
          this.$router.push("/login");
        })
        .catch((error) => {
          this.$swal.fire({
            title: "Error!",
            text: "Error al crear el usuario",
            icon: "error",
            confirmButtonText: "Try Again",
          });
        });
      this.card = false;
    },
  }
};
</script>

<style>
</style>