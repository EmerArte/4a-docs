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
input LibroInput {
    id: String!
    titulo: String!
    descripcion: String!
    disponible: String!
    autor: String!
    imagen: String!
    puntuacion: Float!
}
type Libroget {
    id: String
    titulo: String!
    descripcion: String!
    disponible: String!
    autor: String!
    imagen: String!
    puntuacion: Float!
}
extend type Query {
    librosByID(titulo: String!): Libroget
    allLibros: [Libros]

}
extend type Mutation {
    newLibros(libro: LibroInput!): Libros
    deleteLibroById(id: String!): String
}
`;
module.exports = librosTypeDefs;