
  <template>
  <div class="text-center mx-1">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="red" outlined elevation="0" v-bind="attrs" v-on="on">
          Eliminar
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="text-h5 red lighten-2">
          Eliminar Cuenta
        </v-card-title>
        <v-card-text class="pt-2 text-h6">
          ¿Quiere eliminar su cuenta? Sus datos se perderán
        </v-card-text>
        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog = false"> Cancelar </v-btn>
          <v-btn color="red" text @click="eliminarCuenta"> Eliminar </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      
    };
  },
  methods: {
    eliminarCuenta() {
      this.dialog=!this.dialog
    },
  },
};
</script>

<style>
</style>