'use strict';

const React = require('react');
const ApiClient = require('./api-client').ApiClient;
const BookPageService = require('./book-service').BookPageService;

const toChunks = require('./utils').toChunks;

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
        this.bookPageService = new BookPageService(book, this.state.readActivities);
        const pageCards = this.generatePageCards();
        const pageCardTable = this.buildPageCardTable(pageCards);
        return (
            <div className="container-fluid">
                <div className="row">
                    <main className="col-md-10 offset-md-2 pt-3">
                        <h1 className="overview-h1">
                            {book.title}
                        </h1>
                        <section className="row text-center overview-book-placeholder">
                            <div className="empty"></div>
                        </section>
                        {pageCardTable}
                    </main>
                </div>
                {/* <div className="form-group row">
                    <label htmlFor="book-title" className="col-2 col-form-label">Title</label>
                    <div className="col-10">
                        <input id="book-title" className="form-control" type="text" value={book.title} />
                    </div>
                </div> */}
            </div>
        );
    }

    generatePageCards() {
        const book = this.state.book;
        return _.map(
            _.range(1, 1 + book.totalPagesCount),
            pageNumber => this.generatePageCard(pageNumber)
        );
    }

    generatePageCard(pageNumber) {
        const isPageRead = this.isPageRead(pageNumber);
        const cardClass = isPageRead ? "card-inverse card-info" : "card-outline-info";
        return (
            <div className={`card ${cardClass} text-center`}>
                <div>
                    <blockquote className="card-blockquote">
                        {pageNumber}
                    </blockquote>
                </div>
            </div>
        );
    }

    isPageRead(pageNumber) {
        return this.bookPageService.isPageRead(pageNumber);
    }

    buildPageCardTable(pageCards) {
        const tdPageCard = _.map(pageCards, (pageCard, i) => (
            <td key={i}>{pageCard}</td>
        ));
        const tdChunks = toChunks(tdPageCard, 10);
        const rows = _.map(tdChunks, (chunk, i) => (
            <tr key={i}>{chunk}</tr>
        ));
        return (
            <div className="table-responsive">
                <table className="table">
                    <tbody>
                        {rows}
                    </tbody>
                </table>
            </div>
        );
    }
}

module.exports = {
    OverviewBookComponent: OverviewBookComponent
};