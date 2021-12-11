const alquilerResolver = {
    Query: {
        alquilerById: async (_, { id }, { dataSources, userIdToken }) => {
            usernameToken = (await dataSources.authAPI.getUser(userIdToken)).id
            if (id == usernameToken)
                return dataSources.accountAPI.alquilerById(id)
            else
                return null
        }
    },
    Mutation: {
        createAlquiler: async (_, { alquiler }, { dataSources, userIdToken }) => {
            usernameToken = (await dataSources.authAPI.getUser(userIdToken)).alquiler
            if (alquiler.usuario == usernameToken)
                return dataSources.accountAPI.createAlquiler(alquiler)
            else
                return null
        }
    }
};
module.exports = alquilerResolver;