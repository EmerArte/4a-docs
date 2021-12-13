const librosResolver = require('./libros_resolver');
const alquilerResolver = require('./alquiler_resolver');
const authResolver = require('./auth_resolver');
const lodash = require('lodash');
const resolvers = lodash.merge(librosResolver, alquilerResolver, authResolver);
module.exports = resolvers;