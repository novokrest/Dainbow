'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const utils = require('./utils');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {books: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/auto/books'}).done(response => {
            this.setState({books: response.entity._embedded.books});
        });
    }

    render() {
        return (
            <Books books={this.state.books} />
        );
    }
}

class Books extends React.Component {

    constructor(props) {
        super(props)
        this.state = { booksPerRow: 3, columnClass: 'col-md-4' }
    }

    render() {
        var books = this.props.books.map(book =>
            <div className={this.state.columnClass}>
                <Book key={book._links.self.href} book={book}/>
            </div>
        );
        var rowsCount = Math.floor(books.length / this.state.booksPerRow) + (books.length % this.state.booksPerRow ? 1 : 0);
        console.log('DEV', "Books", 'rows =', rowsCount);

        var chunkedBooks = utils.toChunks(books, this.state.booksPerRow);
        console.log('DEV', 'Books', 'chunked books =', chunkedBooks)

        var rows = chunkedBooks.map(chunk =>
            <div className="row top-buffer">
                {chunk}
            </div>
        );
        return (
            <div className="books container-fluid ">
                {rows}
            </div>
        );
    }
}

class Book extends React.Component {

    render() {
        return (
            <div className="book card">
                <img className="card-img-top" width="100%"
                     src="http://freedesignfile.com/upload/2015/06/Brochure-and-book-cover-creative-vector-06.jpg"
                     alt="cover" />
                <div className="card-block">
                    <h4 className="card-title">{this.props.book.title}</h4>
                    <p className="card-text"></p>
                    <a href="#" className="btn btn-primary">More</a>
                </div>
            </div>
        );
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('app')
);