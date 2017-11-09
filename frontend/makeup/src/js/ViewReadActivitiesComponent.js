'use strict';

const React = require('react');
const BookTitleHeaderComponent = require('./BookTitleHeaderComponent').BookTitleHeaderComponent;
const ReadActivitiesTableComponent = require('./ReadActivitiesTableComponent').ReadActivitiesTableComponent;

const ApiClient = require('./api-client').ApiClient;

class ViewReadActivitiesComponent extends React.Component {

    constructor(props) {
        super(props);
        this.id = props.match.params.id;
        this.state = { book: {}, readActivities: [] };
        this.onReadActivityRemoved = this.onReadActivityRemoved.bind(this);
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

    onReadActivityRemoved(id) {
        const readActivities = _.filter(this.state.readActivities, readActivity => readActivity.id != id);
        this.setState({ readActivities: readActivities });
    }

    render() {
        const book = this.state.book;
        const readActivities = this.state.readActivities;

        return (
            <div className="container">
                <div className="row">
                    <BookTitleHeaderComponent title={book.title} />
                    <ReadActivitiesTableComponent readActivities={readActivities} onReadActivityRemoved={this.onReadActivityRemoved} />
                </div>
            </div>
        );
    }
}

module.exports = {
    ViewReadActivitiesComponent: ViewReadActivitiesComponent
}