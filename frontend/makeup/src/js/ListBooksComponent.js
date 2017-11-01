'use strict';

const configuration = require('react-global-configuration');
const React = require('react');
const Link = require('react-router-dom').Link;
const toChunks = require('./utils').toChunks;

const RouteService = require('./routes').RouteService;
const ApiClient = require('./api-client').ApiClient;
const BookService = require('./book-service').BookService;

const BookProgressComponent = require('./BookProgressComponent').BookProgressComponent;

class ListBooksComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {books: [], readProgresses: {}};
    }

    componentDidMount() {
        ApiClient.getBooksAndHistory(
            (books, readActivities) => {
                const bookService = new BookService(books, readActivities);
                const readProgresses = bookService.calculateReadProgresses();
                this.setState({ books: books, readProgresses: readProgresses });
            }
        );
    }

    render() {
        return (
            <div>
                <section className="jumbotron text-center">
                    <div className="center">
                        <h1 className="jumbotron-heading">BOOKS</h1>
                        <p className="lead text-muted">
                            Here are listed the books which were already read, 
                            which are in the reading process and which will be read
                        </p>
                        <p>
                            <a className="btn btn-primary" href="#">Add</a>
                            <a className="btn btn-secondary" href="#">Remove</a>
                        </p>
                    </div>
                </section>
                <Books books={this.state.books} readProgresses={this.state.readProgresses}/>
            </div>
        );
    }
}

class Books extends React.Component {

    constructor(props) {
        super(props)
        this.state = { 
            booksPerRow: 3
        };
    }

    render() {
        const bookCards = _.map(this.props.books, (book, i) =>
            <div key={i} className="col-md-4 col-lg-4">
                <BookComponent book={book} readProgress={this.props.readProgresses[book.id]}/>
            </div>
        );

        const bookCardChunks = toChunks(bookCards, this.state.booksPerRow);
        const bookCardGroups = _.map(bookCardChunks, (bookCardChunk, i) => 
            <div key={i} className="row .row-eq-height top-buffer">
                {bookCardChunk}
            </div>
        );

        return (
            <div className="container">
                {bookCardGroups}
            </div>
        );
    }
}

class BookComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { defaultCoverImgSrc: "http://freedesignfile.com/upload/2015/06/Brochure-and-book-cover-creative-vector-06.jpg" }
    }

    render() {
        var book = this.props.book, readProgress = this.props.readProgress;
        var imgSrc = book.coverImage ? 'data:image/jpg;base64, ' + book.coverImage : this.state.defaultCoverImgSrc;
        return (
            <div >
                <div className='book-cover-holder'>
                    <a className="thumbnail" href="#">
                        <img className="book-cover center-block" width="100%" src={imgSrc} alt="cover" />
                    </a>
                </div>
                <div className="card-block">
                    <h4>{this.props.book.title}</h4>
                    <h5>{this.props.book.author}</h5>
                    <div className='book-card-footer'>
                        <BookProgressComponent readProgress={this.props.readProgress}/>
                        <Link to={RouteService.getOverviewBookRoute(book.id)} className="book-more btn btn-primary pull-right">More</Link>
                    </div>
                </div>
            </div>
        );
    }

    renderProgressBar(readProgress) {
        const readPercent = readProgress.readPercent;
        const progressBarType = readPercent < 25 ? 'progress-bar-danger'
                              : readPercent < 50 ? 'progress-bar-warning'
                              : readPercent < 75 ? 'progress-bar-info'
                              : 'progress-bar-success';
        return (
            <div>
                <div className="book-progress progress">
                    <div className={'progress-bar ' + progressBarType}
                        role="progressbar"
                        aria-valuenow={readPercent} aria-valuemin="0" aria-valuemax="100"
                        style={{ width: readPercent + '%' }}>
                        {readProgress.readPagesCount}/{readProgress.totalPagesCount}
                    </div>
                </div>
            </div>
        );
    }
}

module.exports = {
    ListBooksComponent: ListBooksComponent
};