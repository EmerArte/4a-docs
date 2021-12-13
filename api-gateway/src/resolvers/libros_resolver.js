const librosResolver = {
    Query: {
        librosByID: async (_, { titulo }, { dataSources}) => {
            return await dataSources.adminLibreria.getLibrosById(titulo)
        },
        allLibros: async (_,__,{dataSources}) => {
            return await dataSources.adminLibreria.getAllLibros()
        },
    },
    Mutation: {
        newLibros: async (_, { libro }, { dataSources }) => {
            console.log(dataSources)
            return await dataSources.adminLibreria.newLibros(libro);
        },
        deleteLibroById: async (_, { id }, { dataSources }) => {
            console.log(dataSources)
            return await dataSources.adminLibreria.deleteLibroById(id);
        },
    }
};
module.exports = librosResolver;
