<template>
  <v-container grid-list-md>
    <v-card outlined class="mb-4">
      <v-card-title primary-title> Bienvenido {{ name }} </v-card-title>
    </v-card>
    <v-card outlined>
      <v-card-title primary-title> Libros Disponibles </v-card-title>
      <v-card-text>
        <v-row>
          <v-col sm="12" md="6" lg="4" v-for="libro in libros" :key="libro.id">
            <v-card class="mx-auto my-3">
              <v-img height="250" contain :src="libro.imagen"></v-img>

              <v-card-title>{{ libro.titulo }}</v-card-title>

              <v-card-text>
                <v-row align="center" class="mx-0">
                  <v-rating
                    :value="libro.puntuacion"
                    color="amber"
                    dense
                    half-increments
                    readonly
                    size="14"
                  ></v-rating>

                  <div class="grey--text ms-4">{{ libro.puntuacion }}</div>
                </v-row>

                <div class="my-4 text-subtitle-1">{{ libro.autor }}</div>

                <div class="h-40 px-3 text-justify overflow-hidden">
                  {{ libro.descripcion }}
                </div>
              </v-card-text>

              <v-divider class="mx-4"></v-divider>
              <v-card-actions>
                <v-btn
                  color="primary"
                  text
                  @click="alquilar(libro.id)"
                  :disabled="libro.disponible == 'false'"
                >
                  Alquilar
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import gql from "graphql-tag";
import jwt_decode from "jwt-decode";
export default {
  name: "Home",
  data() {
    return {
      name: "",
      user_id: 0,
      libros: [],
    };
  },
  components: {},
  methods: {
    generateId(){
      return 'AL'+Date.now()
    },
    alquilar: async function (libroid) {
      this.$apollo
        .mutate({
          mutation: gql`
            mutation ($alquiler: AlquilerInput!) {
              createAlquiler(alquiler: $alquiler) {
                id
                usuario
                libro
                fecha_de_inicio
                fecha_de_fin
                activo
              }
            }
          `,
          variables: {
            alquiler: {
              id: this.generateId(),
              usuario: ""+this.user_id,
              libro: libroid,
            },
          },
        })
        .then((result) => {
           this.$swal.fire({
            title: "Exito!",
            text: "El alquiler "+ result.data.createAlquiler.id + " se ha creado correctamente",
            icon: "success",
            showConfirmButton: false,
            timer: 3000,
          });
        })
        .catch((error) => {
          this.$swal.fire({
            title: "Error!",
            text: "Error no se pudo cargar la información",
            icon: "error",
            confirmButtonText: "Try Again",
          });
        });
    },
  },
  mounted() {
    this.user_id = jwt_decode(localStorage.getItem("refresh")).user_id;
    this.$apollo
      .query({
        query: gql`
          query {
            allLibros {
              id
              titulo
              descripcion
              disponible
              autor
              imagen
              puntuacion
            }
          }
        `,
      })
      .then((result) => {
        this.libros = result.data.allLibros;
      })
      .catch((error) => {
        this.$swal.fire({
          title: "Error!",
          text: "Error no se pudo cargar la información",
          icon: "error",
          confirmButtonText: "Try Again",
        });
      });
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
        this.name = result.data.getUserById.nombre;
        console.log(result);
      })
      .catch((error) => {
        this.$swal.fire({
          title: "Error!",
          text: "Error no se pudo cargar la información",
          icon: "error",
          confirmButtonText: "Try Again",
        });
      });
  },
};
</script>
<style>
.wi-100 {
  width: 100% !important;
}
.wi-70 {
  width: 70% !important;
}
.wi-80 {
  width: 80% !important;
}
.h-40 {
  height: 110px !important;
}
</style>
