'use strict';

const configuration = require('react-global-configuration');
const React = require('react');
const Link = require('react-router-dom').Link;
const utils = require('./utils');

const RouteService = require('./routes').RouteService;
const ApiClient = require('./api-client').ApiClient;
const BookService = require('./book-service').BookService;

class ListBooksComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {books: [], readProgresses: {}};
    }

    componentDidMount() {
        ApiClient.getBooksAndHistory(
            (books, readActivities) => {
                console.log('books', books.length)
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
            booksPerRow: 3, 
            columnClass: ''// 'col-xs-4 col-md-4' 
        };
    }

    render() {
        var books = _.map(this.props.books, book =>
            <div key={book._links.self.href} className={this.state.columnClass}>
                <Book book={book} readProgress={this.props.readProgresses[book.id]}/>
            </div>
        );

        console.log('render:', books.length);

        var rowsCount = Math.floor(books.length / this.state.booksPerRow) + (books.length % this.state.booksPerRow ? 1 : 0);
        // var chunkedBooks = utils.toChunks(books, this.state.booksPerRow);

        // var rows = chunkedBooks.map((chunk, i) =>
        //     <div key={i} className="book-row row top-buffer bottom-buffer">
        //         {chunk}
        //     </div>
        // );

        const bookCards = _.map(books, (book, i) => 
            <div key={i} className="card">
                {book}
            </div>
        );

        console.log('books.length:', books.length);
        console.log('bookCards:', bookCards.length);

        return (
            <div className="container">
                <div className="row">
                    {bookCards}
                </div>
            </div>
        );
    }
}

class Book extends React.Component {

    constructor(props) {
        super(props);
        this.state = { defaultCoverImgSrc: "http://freedesignfile.com/upload/2015/06/Brochure-and-book-cover-creative-vector-06.jpg" }
    }

    render() {
        var book = this.props.book, readProgress = this.props.readProgress;
        var imgSrc = book.coverImage ? 'data:image/jpg;base64, ' + book.coverImage : this.state.defaultCoverImgSrc;
        return (
            <div className="book card">
                <div className='book-cover-holder'>
                    <a className="thumbnail" href="#">
                        <img className="book-cover card-img-top" width="100%" src={imgSrc} alt="cover" />
                    </a>
                </div>
                <div className="card-block">
                    <h4 className="card-title">{this.props.book.title}</h4>
                    <h5 className="card-title">{this.props.book.author}</h5>
                    <div className='book-card-footer'>
                        {this.renderProgressBar(this.props.readProgress)}
                        <Link to={RouteService.getOverviewBookRoute(book.id)} className="book-more btn btn-primary">More</Link>
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