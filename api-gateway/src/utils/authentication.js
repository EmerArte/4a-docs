const { ApolloError } = require('apollo-server');
const serverConfig = require('../server');
const fetch = require('node-fetch');
const authentication = async ({ req }) => {
    const token = req.headers.authorization || '';
    if (token == '')
        return { userIdToken: null }
    else {
        try {
            let requestOptions = {
                method: 'POST', headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ token }), redirect: 'follow'
            };
            let response = await fetch(
                `${serverConfig.auth_api_url}/verifyToken/`,
                requestOptions)
            if (response.status != 200) {
                throw new ApolloError(`SESION INACTIVA` + response.status)
            }
            return { userIdToken: (await response.json()) };
        }
        catch (error) {
            throw new ApolloError(`TOKEN ERROR: ${error}`);
        }
    }
}
module.exports = authentication;