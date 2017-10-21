'use strict';

const React = require('react');
const ApiClient = require('./api-client').ApiClient;

class OverviewBookComponent extends React.Component {

    constructor(props) {
        super(props);
        this.id = props.match.params.id;
        this.state = { book: {}, readActivities: [] };
    }

    componentDidMount() {
        ApiClient.getBook(this.id, book => {
            console.log('Book:', book);
            this.setState({ book: book });
        });
        ApiClient.getBookHistory(this.id, readActivities => {
            console.log('ReadActivities: book={}, readActivities={}', this.id, readActivities);
            this.setState({ readActivities: readActivities });
        });
    }

    render() {
        const book = this.state.book;
        return (
            <div className="container">
                <div className="form-group row">
                    <label htmlFor="book-title" className="col-2 col-form-label">Title</label>
                    <div className="col-10">
                        <input id="book-title" className="form-control" type="text" value={book.title} />
                    </div>
                </div>
            </div>
        );
    }
}

module.exports = {
    OverviewBookComponent: OverviewBookComponent
};