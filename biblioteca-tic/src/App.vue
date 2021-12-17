<template>
  <v-app>
    <nav-bar></nav-bar>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import gql from "graphql-tag";
import { mapActions } from 'vuex';
export default {
  components: { NavBar },
  name: "App",

  data: () => ({
    //
  }),
  methods:{
    ...mapActions(["logearse"]),
  },
  mounted() {
    if(localStorage.getItem("refresh")){
this.$apollo
      .mutate({
        mutation: gql`
          mutation ($refresh: String!) {
            refreshToken(refresh: $refresh) {
              access
            }
          }
        `,
        variables: {
          refresh: localStorage.getItem("refresh")
        },
      })
      .then((result) => {
        localStorage.removeItem("access")
        localStorage.setItem("access",result.data.refreshToken.access)
        this.logearse("exito")
     })
      .catch((err) => {
        console.log(err);
        this.$router.push("/login");
      });
    }else{
      this.$router.push("/login");
    }
  },
};
</script>
<style scoped>
#app {
  background-color: #f3f3f3;
}
</style>