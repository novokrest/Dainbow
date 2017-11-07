const client = require('./client');
const _ = require('lodash');

class ApiClient {

    static default() {
        return new ApiClient('http://localhost:8091/api/v1/read');
    }

    constructor(apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * callback: (book, readActivities) => { }
     */
    getBooksAndHistory(callback) {
        this.getBooks(books => {
            this.getHistory(readActivities => {
                callback(books, readActivities);
            });
        });
    }

    getBooks(callback) {
        this._getUrl('/books', response => {
            var books = response.entity._embedded.books;
            callback(books);
        });
    }

    getBook(id, callback) {
        this._getUrl(`/books/${id}`, response => {
            const book = response.entity;
            callback(book);
        });
    }

    postBook(book, callback) {
        this._postUrl('/books', book, callback);
    }

    getHistory(callback) {
        this._getUrl('/history', response => {
            var readActivities = response.entity._embedded.history;
            callback(readActivities);
        });
    }

    getBookHistory(id, callback) {
        this._getUrl(`/history/search/book?bookId=${id}`, response => {
            var readActivities = response.entity._embedded.history;
            callback(readActivities);
        });
    }

    postReadActivity(readActivity, callback) {
        console.log('Post read activity:', readActivity);
        this._postUrl('/history', readActivity, response => {
            console.log('Post read activity response:', response);
            callback(response.entity);
        });
    }

    _postUrl(path, entity, callback) {
        client({ method: 'POST', path: this.absoluteUrl(path), entity: entity }).done(response => {
            callback(response);
        });
    }

    _getUrl(path, callback) {
        client({ method: 'GET', path: this.absoluteUrl(path) }).done(response => {
            callback(response);
        });
    }

    absoluteUrl(path) {
        return this.apiUrl + path;
    }
}

module.exports = {
    ApiClient: ApiClient.default()
}