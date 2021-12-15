import Vue from 'vue'
import App from './App.vue'
import VueApollo from 'vue-apollo'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import VueSweetalert2 from 'vue-sweetalert2';
import { ApolloClient, createHttpLink, InMemoryCache } from '@apollo/client/core'
import { setContext } from 'apollo-link-context'
import 'sweetalert2/dist/sweetalert2.min.css';
Vue.use(VueApollo)
Vue.use(VueSweetalert2)


const httpLink =  createHttpLink({
  uri: 'https://api-gateway-biblioteca.herokuapp.com/'
})

const authLink = setContext((_,{headers})=>{
  return {
    headers:{
    ...headers,
    "Authorization": localStorage.getItem("access") || ""
  }
  }
})
const apolloClient = new ApolloClient({
  link: authLink.concat(httpLink),
  cache: new InMemoryCache()
})

const apolloProvider = new VueApollo({
  defaultClient: apolloClient
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  apolloProvider,
  render: h => h(App)
}).$mount('#app')
