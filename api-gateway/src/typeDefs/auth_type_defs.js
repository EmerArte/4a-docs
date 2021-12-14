const { gql } = require('apollo-server');
const authTypeDefs = gql`
type Tokens {
    refresh: String!
    access: String!
}
type Access {
    access: String!
}
input CredentialsInput {
    num_doc_id: Float!
    password: String!
}
input SignUpInput {
    num_doc_id: Int!
    password: String!
    nombre: String!
    apellido: String!
    telefono: Float!
    tipo_doc_id: String!
}
type UserDetail {   
    num_doc_id: Int!
    password: String!
    nombre: String!
    apellido: String!
    telefono: Float!
    tipo_doc_id: String!
}
type Mutation {
    signUpUser(userInput :SignUpInput!): UserDetail!
    logIn(credentials: CredentialsInput!): Tokens!
    refreshToken(refresh: String!): Access!
    updateUser(num_doc_id: Float!, userInput :SignUpInput): UserDetail!
}
type Query {
    getUserById(num_doc_id: Float!): UserDetail 
}
`;
module.exports = authTypeDefs;