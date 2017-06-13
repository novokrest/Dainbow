'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const utils = require('./utils');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {books: [], readProgresses: {}};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/auto/books'}).done(response => {
            var books = response.entity._embedded.books;
            client({method: 'GET', path: '/api/auto/progress'}).done(response => {
                var readProgresses = response.entity._embedded.progress.reduce((res, readProgress) => {
                    console.log('App: client: before', res, readProgress);
                    res[readProgress.bookId] = readProgress;
                    return res;
                }, {});
                console.log('App: client: readProgresses', readProgresses, response.entity._embedded.progress);
                this.setState({books: books, readProgresses: readProgresses});
            });

        });

    }

    render() {
        console.log('App: readProgresses', this.state.readProgresses, this.state.books);
        return (
            <Books books={this.state.books} readProgresses={this.state.readProgresses}/>
        );
    }
}

class Books extends React.Component {

    constructor(props) {
        super(props)
        this.state = { booksPerRow: 3, columnClass: 'col-xs-4 col-md-4' }
    }

    render() {
        console.log('Books: readProgresses', this.props.readProgresses);
        if (this.props.books.length > 0) {
            var book1 = this.props.books[0];
            var progress1 = this.props.readProgresses[book1.id];
            console.log('Books: book1: ', book1, progress1);

        }
        var books = this.props.books.map(book =>
            <div key={book._links.self.href} className={this.state.columnClass}>
                <Book book={book} readProgress={this.props.readProgresses[book.id]}/>
            </div>
        );
        var rowsCount = Math.floor(books.length / this.state.booksPerRow) + (books.length % this.state.booksPerRow ? 1 : 0);
        console.log('DEV', "Books", 'rows =', rowsCount);

        var chunkedBooks = utils.toChunks(books, this.state.booksPerRow);
        console.log('DEV', 'Books', 'chunked books =', chunkedBooks)

        var rows = chunkedBooks.map((chunk, i) =>
            <div key={i} className="book-row row top-buffer bottom-buffer">
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

    constructor(props) {
        super(props);
        this.state = { defaultCoverImgSrc: "http://freedesignfile.com/upload/2015/06/Brochure-and-book-cover-creative-vector-06.jpg" }
    }

    render() {
        console.log('Book: readProgress', this.props.readProgress);
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
                        {this.renderProgressBar()}
                        <a href="#" className="book-more btn btn-primary">More</a>
                    </div>
                </div>
            </div>
        );
    }

    renderProgressBar() {
        var totalPagesCount = this.props.book.pagesCount,
            readPagesCount = this.props.readProgress ? this.props.readProgress.readPagesCount : 0;

        var readPercent = Math.floor(readPagesCount / totalPagesCount * 100);
        var progressBarType = readPercent < 25 ? 'progress-bar-danger'
                            : readPercent < 50 ? 'progress-bar-warning'
                            : readPercent < 75 ? 'progress-bar-info'
                            : 'progress-bar-success';
        return (
            <div className="book-progress progress">
                <div className={'progress-bar ' + progressBarType}
                     role="progressbar"
                     aria-valuenow={readPercent} aria-valuemin="0" aria-valuemax="100"
                     style={{ width: readPercent + '%' }}>
                    {readPagesCount}/{totalPagesCount}
                </div>
            </div>
        );
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('app')
);