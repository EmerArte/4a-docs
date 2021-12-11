const { gql } = require('apollo-server');
const alquilerTypeDefs = gql `
type Alquiler {
    id: String!
    usuario: String!
    libro: String!
    fecha_de_inicio: Date!
    fecha_de_fin: Date!
    activo: Boolean!
}
input AlquilerInput {
    id: String!
    usuario: String!
    libro: String!
}
extend type Query {
    alquilerById(id: String!): [Alquiler]
}
extend type Mutation {
    createAlquiler(alquiler: AlquilerInput!): Alquiler
}
`;
module.exports = alquilerTypeDefs;
