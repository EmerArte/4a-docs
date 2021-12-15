<template>
  <v-app-bar app color="primary" flat dark id="nav">
    <v-toolbar-title>Biblioteca</v-toolbar-title>

    <v-spacer></v-spacer>
    <div v-if="this.$store.state.isLoggin">
      <div v-if="admin">
        <v-btn text> Dashboard </v-btn>
      </div>
      <div v-else>
        <v-btn text to="/"> home </v-btn>
        <v-btn text to="/perfil"> Perfil </v-btn>
        <v-btn text @click="logout"> Logout </v-btn>
      </div>
    </div>
    <div v-else>
      <v-btn text to="/"> home </v-btn>
      <v-btn text to="/login"> Login </v-btn>
      <v-btn text to="/signup"> SignUp </v-btn>
      <v-btn text to="/about"> Nosotros </v-btn>
    </div>
  </v-app-bar>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  data() {
    return {
      logged: false,
      admin: false,
    };
  },
 methods:{
    ...mapActions(["logearse"]),
    logout(){
        this.logearse("asd")
        this.$router.push("/login")
    }
  }
};
</script>

<style>
</style>