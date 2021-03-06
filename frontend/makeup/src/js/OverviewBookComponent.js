'use strict';

const React = require('react');
const Link = require('react-router-dom').Link;
const BookProgressComponent = require('./BookProgressComponent').BookProgressComponent;
const BookTitleHeaderComponent = require('./BookTitleHeaderComponent').BookTitleHeaderComponent;

const ApiClient = require('./api-client').ApiClient;
const RouteService = require('./routes').RouteService;
const BookPageService = require('./book-service').BookPageService;
const BookReadProgress = require('./book-service').BookReadProgress;

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
        const bookOverview = this.buildBookOverview();
        return (
            <div className="container">
                <div className="row">
                    <div className="col-sm-3 col-md-2 sidebar">
                        <ul className="nav nav-sidebar">
                            <li className="active">
                                <a href="#">Overview <span className="sr-only">(current)</span></a>
                            </li>
                        </ul>
                        <ul className="nav nav-sidebar">
                            <li>
                                <Link to={RouteService.getViewReadActivitiesRoute(book.id)}>
                                    View Read Activities
                                </Link>
                            </li>
                            <li>
                                <Link to={RouteService.getLogReadActivityRoute(book.id)}>
                                    Log Read Activity
                                </Link>
                            </li>
                        </ul>
                    </div>
                    <div className="col-sm-9 col-md-10 main">
                        <BookTitleHeaderComponent title={book.title} />
                        {bookOverview}
                        {pageCardTable}
                    </div>
                </div>
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
        const cardClass = isPageRead ? "btn-primary" : "btn-outline-primary";
        return (
            <button type="button" className={`btn ${cardClass} btn-block`}>
                <div className="text-center">
                    {pageNumber}
                </div>
            </button>
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

    buildBookOverview() {
        const book = this.state.book;
        const pagesService = new BookPageService(book, this.state.readActivities);
        const readProgress = pagesService.calculateReadProgress();
        return (
            <div className="text-center overview-book-placeholder">
                <div>
                    <p>Author: {book.author}</p>
                </div>
                <div>
                    <p>Total Pages: {book.totalPagesCount}</p>
                </div>
                <div>
                    <p>Read Pages: {readProgress.readPagesCount}</p>
                </div>
                <div>
                    <BookProgressComponent readProgress={readProgress}/>
                </div>
                <div className="top-buffer">
                    <Link to={RouteService.getLogReadActivityRoute(book.id)} 
                          className="overview-log-btn btn btn-primary">
                        Log Read Activity
                    </Link>
                </div>
            </div>
        );
    }
}

module.exports = {
    OverviewBookComponent: OverviewBookComponent
};