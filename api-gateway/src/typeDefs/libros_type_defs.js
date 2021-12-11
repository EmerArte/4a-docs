const { gql } = require('apollo-server');
const librosTypeDefs = gql `
type Libros {
    id: String!
    titulo: String!
    descripcion: String!
    disponible: String!
    autor: String!
    imagen: String!
    puntuacion: Float!    
}
extend type Query {
    librosByID(id: String!): Libros
}
`;
module.exports = librosTypeDefs;