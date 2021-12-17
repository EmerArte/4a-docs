const alquilerResolver = {
    Query: {
        getAlquilerByUserId: async (_, { userid }, { dataSources, userIdToken }) => {
            if (userid == userIdToken.num_doc_id)
                return dataSources.adminLibreria.alquilerByUserId(userid)
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
            if (alquiler.usuario == userIdToken.num_doc_id)
                return dataSources.adminLibreria.newAlquiler(alquiler)
            else
                return null
        }
       
    }
};
module.exports = alquilerResolver;