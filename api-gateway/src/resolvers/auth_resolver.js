const usersResolver = {
    Query: {
        getUserById: (_, { num_doc_id }, { dataSources, userIdToken }) => {
            if (num_doc_id == userIdToken.num_doc_id)
                return dataSources.authAPI.getUser(num_doc_id)
            else
                return null
        },
    },
    Mutation: {
        signUpUser: async (_, { userInput }, { dataSources }) => 
        {
            const accountInput = {
                num_doc_id: userInput.num_doc_id,               
                telefono: userInput.telefono,
                tipo_doc_id: userInput.tipo_doc_id,
                password: userInput.password,
                nombre: userInput.nombre,
                apellido: userInput.apellido
            }
            return await dataSources.authAPI.createUser(accountInput);
        },
        updateUser: async (_, { num_doc_id, userInput}, { dataSources , userIdToken  }) => {
            if (num_doc_id == userIdToken.num_doc_id)
                return await dataSources.authAPI.updateUser(num_doc_id,userInput);
            else
                return null
        },
        logIn: (_, { credentials }, { dataSources }) =>
            dataSources.authAPI.authRequest(credentials),
        refreshToken: (_, { refresh }, { dataSources }) =>
            dataSources.authAPI.refreshToken(refresh),
    }
};
module.exports = usersResolver;