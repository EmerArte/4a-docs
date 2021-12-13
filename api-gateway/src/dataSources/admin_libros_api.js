const { RESTDataSource } = require('apollo-datasource-rest');
const serverConfig = require('../server');
class AdminLibreria extends RESTDataSource {
    constructor() {
        super();
        this.baseURL = serverConfig.account_api_url;
    }
    // LIBROS
    async newLibros(libro) {
        libro = new Object(JSON.parse(JSON.stringify(libro)));
        return await this.post('/libros', libro);
    }
    async getLibrosById(titulo) {
        return await this.get(`/libros/${titulo}`);
    }
    async getAllLibros() {
        return await this.get('/libros/getall');
    }
    async deleteLibroById(id) {
        return await this.delete(`/libros/delete/${id}`);
    }
    // ALQUILER
    async newAlquiler(alquiler) {
        alquiler = new Object(JSON.parse(JSON.stringify(alquiler)));
        return await this.post('/alquiler', alquiler);
    }
    async getAlquilerByUserId(userid) {
        return await this.get(`/alquiler/${userid}`);
    }
    async deleteAlquilerById(id) {
        return await this.delete(`/alquiler/delete/${id}`);
    }
    async getAlquilerActivos() {
        return await this.get(`/alquiler/activos`);
    }
    async getAlquilerPendientes() {
        return await this.get('/alquiler/pendientes');
    }
}
module.exports = AdminLibreria;