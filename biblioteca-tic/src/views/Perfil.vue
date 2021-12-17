<template>
  <div class="my-4">
    <v-container class="wi-40">
      <v-card elevation="2">
        <v-row>
          <v-col cols="4">
            <v-card-title>Mi cuenta</v-card-title>
          </v-col>
          <v-col cols="8">
            <v-card-actions class="d-flex justify-end">
              <boton-eliminar-cuenta></boton-eliminar-cuenta>
              <boton-editar-cuenta
                :nombre="nombre"
                :apellido="apellido"
                :num_doc="num_id"
                :telefono="telefono"
                :tipodedoc="tipo_doc"
              ></boton-editar-cuenta>
            </v-card-actions>
          </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-card-text>
          <v-row>
            <v-col cols="6">
              <p class="text-h6 font-weight-bold pa-0 ma-0">Nombre:</p>
            </v-col>
            <v-col cols="6">
              <p class="text-h6 pa-0 ma-0">{{ nombre }}</p>
            </v-col>
            <v-col cols="6">
              <p class="text-h6 font-weight-bold pa-0 ma-0">Apellido:</p>
            </v-col>
            <v-col cols="6">
              <p class="text-h6 pa-0 ma-0">{{ apellido }}</p>
            </v-col>
            <v-col cols="6">
              <p class="text-h6 font-weight-bold pa-0 ma-0">
                Num de Identificación:
              </p>
            </v-col>
            <v-col cols="6">
              <p class="text-h6 pa-0 ma-0">{{ num_id }}</p>
            </v-col>
            <v-col cols="6">
              <p class="text-h6 font-weight-bold pa-0 ma-0">Teléfono:</p>
            </v-col>
            <v-col cols="6">
              <p class="text-h6 pa-0 ma-0">{{ telefono }}</p>
            </v-col>
          </v-row>
        </v-card-text>
        <v-divider></v-divider>
      </v-card>
    </v-container>
    <v-container class="wi-40">
      <v-card elevation="2">
        <v-card-title>Mis Reservas</v-card-title>
        <v-card-text>
          <v-data-table
            :headers="headers"
            :items="alquilers"
            :items-per-page="5"
            class="elevation-1"
          ></v-data-table>
        </v-card-text>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import BotonEliminarCuenta from "../components/BotonEliminarCuenta.vue";
import BotonEditarCuenta from "../components/BotonEditarCuenta.vue";
import jwt_decode from "jwt-decode";
import gql from "graphql-tag";
export default {
  name: "Home",
  components: { BotonEliminarCuenta, BotonEditarCuenta },
  data() {
    return {
      headers: [
        {
          text: "Id",
          align: "start",
          sortable: false,
          value: "id",
        },
        {
          text: "Id del Libro",
          align: "start",
          sortable: false,
          value: "libro",
        },
        {
          text: "Fecha de inicio",
          align: "start",
          sortable: false,
          value: "fecha_de_inicio",
        },
        {
          text: "Fecha de finalización",
          align: "start",
          sortable: false,
          value: "fecha_de_fin",
        },
      ],
      alquilers:[],
      vector: [],
      user_id: 0,
      nombre: "",
      apellido: "",
      num_id: 0,
      telefono: 0,
      contraseña: "",
      tipo_doc: "",
    };
  },
  mounted() {
    this.user_id = jwt_decode(localStorage.getItem("refresh")).user_id;
    this.$apollo
      .query({
        query: gql`
          query ($numDocId: Float!) {
            getUserById(num_doc_id: $numDocId) {
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
          numDocId: this.user_id,
        },
      })
      .then((result) => {
        this.nombre = result.data.getUserById.nombre;
        this.apellido = result.data.getUserById.apellido;
        this.num_id = result.data.getUserById.num_doc_id;
        this.telefono = result.data.getUserById.telefono;
        this.tipo_doc = result.data.getUserById.tipo_doc_id;
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
        this.$router.push("/login");
      });
    this.user_id = jwt_decode(localStorage.getItem("refresh")).user_id;
    this.$apollo
      .query({
        query: gql`
          query ($userid: String!) {
            getAlquilerByUserId(userid: $userid) {
              id
              usuario
              fecha_de_inicio
              libro
              fecha_de_fin
              activo
            }
          }
        `,
        variables: {
          userid: ''+this.user_id,
        },
      })
      .then((result) => {
        this.alquilers =  result.data.getAlquilerByUserId
        console.log(result);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style>
</style>