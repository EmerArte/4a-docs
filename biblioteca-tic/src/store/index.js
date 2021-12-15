import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLoggin: false
  },
  mutations: {
    cerrar(state){
      localStorage.clear()
      state.isLoggin = false
    },
    exito(state){
      state.isLoggin = true
    },
  },
  actions: {
    logearse({commit}, respuesta){
      if(respuesta==="exito"){
        commit("exito")
      }else{
        commit("cerrar")
      }
    }
  },
  modules: {
  }
})
