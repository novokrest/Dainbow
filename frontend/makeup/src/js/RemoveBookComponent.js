'use strict';

const React = require('react');
const BookTitleHeaderComponent = require('./BookTitleHeaderComponent').BookTitleHeaderComponent;
const BookTableComponent = require('./BookTableComponent').BookTableComponent;

const ApiClient = require('./api-client').ApiClient;

class RemoveBookComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { books: [] };
        this.onBookRemoved = this.onBookRemoved.bind(this);
    }

    componentDidMount() {
        ApiClient.getBooks(books => {
            this.setState({ books: books });
        })
    }

    onBookRemoved(id) {
        const books = _.filter(this.state.books, book => book.id != id);
        this.setState({ books: books });
    }

    render() {
        const books = this.state.books;

        return (
            <div className="container">
                <div className="row">
                    <BookTitleHeaderComponent title="Remove Book" />
                    <BookTableComponent books={books} onBookRemoved={this.onBookRemoved} />
                </div>
            </div>
        );
    }
}

module.exports = {
    RemoveBookComponent: RemoveBookComponent
};