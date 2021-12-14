const alquilerResolver = {
    Query: {
        alquilerByUserId: async (_, { userid }, { dataSources, userIdToken }) => {
            usernameToken = (await dataSources.authAPI.getUser(userIdToken))
            if (userid == usernameToken)
                return dataSources.adminLibreria.getAlquilerByUserId(id)
            else
                return null
        },
        getAlquilerActivos: async (_,__, { dataSources }) => {
            return dataSources.adminLibreria.getAlquilerActivos()
        },
        getAlquilerPendientes: async (_,__,{ dataSources }) => {
            return dataSources.adminLibreria.getAlquilerPendientes()
        }
    },
    Mutation: {
        createAlquiler: async (_, { alquiler }, { dataSources, userIdToken }) => {
            usernameToken = (await dataSources.authAPI.getUser(userIdToken))
            if (alquiler.usuario == usernameToken)
                return dataSources.adminLibreria.newAlquiler(alquiler)
            else
                return null
        }
       
    }
};
module.exports = alquilerResolver;