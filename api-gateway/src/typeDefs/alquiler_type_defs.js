const { gql } = require('apollo-server');
const alquilerTypeDefs = gql `
type Alquiler {
    id: String!
    usuario: String!
    libro: String!
    fecha_de_inicio: String!
    fecha_de_fin: String!
    activo: Boolean!
}
input AlquilerInput {
    id: String!
    usuario: String!
    libro: String!
}
extend type Query {
    alquilerByUserId(userid: String!): [Alquiler]
    getAlquilerActivos: [Alquiler]
    getAlquilerPendientes: [Alquiler]
}
extend type Mutation {
    createAlquiler(alquiler: AlquilerInput!): Alquiler
    deleteAlquilerById(id: String!): String
}
`;
module.exports = alquilerTypeDefs;
