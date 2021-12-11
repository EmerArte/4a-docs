//Se llama al typedef (esquema) de cada submodulo
const librosTypeDefs = require('./libros_type_defs');
const alquilerTypeDefs = require('./alquiler_type_defs');
const authTypeDefs = require('./auth_type_defs');
//Se unen
const schemasArrays = [authTypeDefs, librosTypeDefs, alquilerTypeDefs];
//Se exportan
module.exports = schemasArrays;