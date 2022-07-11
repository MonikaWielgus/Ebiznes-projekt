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

    async getProductsFromCart() {
        const url = `http://localhost:8080/cart`;
        const response = await fetch(url, {
            credentials: "include",
            headers: await this.getHeaders()
        });
        return response;
    }

    async addProductToCart(productId) {
        const url = `http://localhost:8080/cart`;
        return await fetch(url, {
            headers: await this.getHeaders(),
            method: 'post',
            credentials: 'include',
            body: productId
        });
    }

    async removeProductFromCart(productId) {
        const url = `http://localhost:8080/cart`;
        return await fetch(url, {
            headers: await this.getHeaders(),
            credentials: 'include',
            method: 'delete',
            body: productId
        });
    }

    async removeAllFromProductsCart() {
        const url = `http://localhost:8080/cart/all`;
        return await fetch(url, {
            headers: await this.getHeaders(),
            method: 'delete',
            credentials: 'include'
        });
    }

    async replaceProduct(productId, amount) {
        const url = `http://localhost:8080/cart/${amount}`;
        return await fetch(url, {
            headers: await this.getHeaders(),
            method: 'post',
            credentials: 'include',
            body: productId
        });
    }
}