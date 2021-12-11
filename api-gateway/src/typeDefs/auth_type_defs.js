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
    num_doc_id: Long!
    password: String!
}
input SignUpInput {
    num_doc_id: Long!
    password: String!
    nombre: String!
    telefono: Long!
    tipo_doc_id: String!
}
type UserDetail {   
    num_doc_id: Long!
    password: String!
    nombre: String!
    apellido: String!
    telefono: Long!
    tipo_doc_id: String!
    

}
type Mutation {
    signUpUser(userInput :SignUpInput): Tokens!
    logIn(credentials: CredentialsInput!): Tokens!
    refreshToken(refresh: String!): Access!
}
type Query {
    userDetailByNum_doc_id(userNum_doc_id: Long!): UserDetail!
}
`;
module.exports = authTypeDefs;