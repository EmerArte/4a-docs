const usersResolver = {
    Query: {
        userDetailByNum_doc_id: (_, { userNum_doc_id }, { dataSources, userIdToken }) => {
            if (userNum_doc_id == userIdToken)
                return dataSources.authAPI.getUser(userNum_doc_id)
            else
                return null
        },
    },
    Mutation: {
        signUpUser: async (_, { userInput }, { dataSources }) => {
            const accountInput = {
                num_doc_id: userInput.num_doc_id,               
                telefono: userInput.telefono,
                tipo_doc_id: userInput.tipo_doc_id,
                
                
            }
            await dataSources.accountAPI.createAccount(accountInput);
            const authInput = {
                num_doc_id: userInput.num_doc_id,
                password: userInput.password,
                nombre: userInput.nombre,
                apellido: userInput.apellido,
            }
            return await dataSources.authAPI.createUser(authInput);
        },
        logIn: (_, { credentials }, { dataSources }) =>
            dataSources.authAPI.authRequest(credentials),
        refreshToken: (_, { refresh }, { dataSources }) =>
            dataSources.authAPI.refreshToken(refresh),
    }
};
module.exports = usersResolver;