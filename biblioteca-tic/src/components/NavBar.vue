<template>
  <v-app-bar app color="primary" flat dark id="nav">
    <v-toolbar-title>Biblioteca</v-toolbar-title>

    <v-spacer></v-spacer>
    <div v-if="logged">
        <div v-if="admin">
        <v-btn text> Dashboard </v-btn>
        </div>
        <div v-else>
        <v-btn text> home </v-btn>
        <v-btn text> Perfil </v-btn>
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
export default {
    data(){
        return{
            logged:false,
            admin:false

        }
    }
};
</script>

<style>
</style>