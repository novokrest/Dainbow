'use strict';

const React = require('react');

const ApiClient = require('./api-client').ApiClient;

class BookTableComponent extends React.Component {

    constructor(props) {
        super(props);
        this.onBookRemoved = props.onBookRemoved;
    }

    removeBook(id) {
        ApiClient.deleteBook(id, id => {
            this.onBookRemoved(id);
        });
    }

    render() {
        const books = this.props.books;
        const bookRows = _.map(books, (book, i) => (
            <tr key={i}>

                <td>{book.id}</td>
                <td>{book.title}</td>
                <td>{book.author}</td>
                <td>{book.totalPagesCount}</td>
                <td>
                    <button className="btn btn-danger" onClick={() => this.removeBook(book.id)}>Remove</button>
                </td>
            </tr>
        ));
        return (
            <div className="table-responsive">
                <table className="table table-striped text-center read-activity-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Total Pages Count</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {bookRows}
                    </tbody>
                </table>
          </div>
        );
    }
}

module.exports = {
    BookTableComponent: BookTableComponent
}