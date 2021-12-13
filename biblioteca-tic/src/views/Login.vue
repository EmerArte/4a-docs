<template>
  <v-container grid-list-xs>
    <v-card outlined max-width="700" class="mx-auto">
      <div class="display-1 font-weight-black text-center my-5">Login</div>
      <v-divider class="mb-12"></v-divider>
      <div class="formulario">
        <v-form v-on:submit.prevent="login">
          <v-row class="mx-3">
            <v-col cols="12">
              <v-text-field
                label="Número de documento"
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
            <v-col cols="6">
              <v-btn color="primary" type="submit" block> login </v-btn>
            </v-col>
            <v-col cols="6">
              <v-btn outlined color="primary" block> Registrarse </v-btn>
            </v-col>
          </v-row>
        </v-form>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import gql from "graphql-tag";
export default {
  data() {
    return {
      show1: false,
      num_doc_id: null,
      password: "",
    };
  },
  methods: {
    login: async function () {
      await this.$apollo
        .mutate({
          mutation: gql`
            mutation ($credentials: CredentialsInput!) {
              logIn(credentials: $credentials) {
                refresh
                access
              }
            }
          `,
          variables: {
            credentials: {
              num_doc_id: parseInt(this.num_doc_id, 10),
              password: this.password,
            },
          },
        })
        .then((result) => {
          let dataLogIn = {
            num_doc_id: this.num_doc_id,
            token_access: result.data.logIn.access,
            token_refresh: result.data.logIn.refresh,
          };
          this.$emit("completedLogIn", dataLogIn);
        })
        .catch((error) => {
           this.$swal.fire({
            title: "Error!",
            text: "Credenciales incorrectas",
            icon: "error",
            confirmButtonText: "Try Again",
          });
        });
    },
  },
};
</script>

<style>
</style>