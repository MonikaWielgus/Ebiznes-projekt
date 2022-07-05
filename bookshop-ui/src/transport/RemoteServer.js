export class RemoteServer {

    async getHeaders() {
        return {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        };
    }

    async getBooks() {
        const url = `http://localhost:8080/books`;
        const response = await fetch(url, {
            headers: await this.getHeaders()
        });
        return response.json();
    }

    async getBookWithCategory(id) {
        const url = `http://localhost:8080/books/${id}/withCategory`;
        const response = await fetch(url, {
            headers: await this.getHeaders()
        });
        return response.json();
    }

    async getCategories() {
        const url = `http://localhost:8080/categories`;
        const response = await fetch(url, {
            headers: await this.getHeaders()
        });
        return response.json();
    }

    async getBooksByCategoryId(id) {
        const url = `http://localhost:8080/categories/${id}/books`;
        const response = await fetch(url, {
            headers: await this.getHeaders()
        });
        return response.json();
    }

    async getProductsFromCart(id) {
        const url = `http://localhost:8080/cart/${id}`;
        const response = await fetch(url, {
            headers: await this.getHeaders()
        });
        return response.json();
    }

    async addProductToCart(clientId, productId) {
        const url = `http://localhost:8080/cart/${clientId}`;
        return await fetch(url, {
            headers: await this.getHeaders(),
            method: 'post',
            body: productId
        });
    }
}