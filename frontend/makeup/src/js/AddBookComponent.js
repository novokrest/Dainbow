'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const RouteService = require('./routes').RouteService;
const ApiClient = require('./api-client').ApiClient;

const singlePageRegex = /^\d+$/g;
const pageIntervalRegex = /^\d+\s*-\s*\d+$/g;

class AddBookComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { title: '', author: '', totalPages: '', readPages: '' };

        this.updateTitle = this.updateTitle.bind(this);
        this.updateAuthor = this.updateAuthor.bind(this);
        this.updateTotalPages = this.updateTotalPages.bind(this);
        this.updateReadPages = this.updateReadPages.bind(this);
        this.onAddButtonClick = this.onAddButtonClick.bind(this);
    }

    updateTitle(title) {
        this.setState({ title: title }, () => console.log('Title was updated:', this.state));
    }

    updateAuthor(author) {
        this.setState({ author: author }, () => console.log('Author was updated:', this.state));
    }

    updateTotalPages(totalPages) {
        this.setState({ totalPages: totalPages }, () => console.log('Total pages was updated:', this.state));
    }

    updateReadPages(readPages) {
        this.setState({ readPages: readPages }, () => console.log('Read pages was updated:', this.state));
    }

    onAddButtonClick() {
        console.log('onAddButtonClick()', this.state);
        const book = this.buildSendingBookData();
        console.log('Book data to send:', book);
        ApiClient.postBook(book, () => {
            console.log('Request has been sent successfully');
            // TODO: implement proper navigation
            RouteService.navigateToBooksRoute(this.props.history);
        });
    }

    buildSendingBookData() {
        const readActivities = this.extractReadActivities();
        const readPagesCount = this.calculateReadPagesCount(readActivities);

        const data = {
            title: this.state.title,
            author: this.state.author,
            totalPagesCount: this.state.totalPagesCount,
            readProgress: { readPagesCount: readPagesCount },
            readHistories: readActivities
        };

        return data;
    }

    extractReadActivities() {
        return this.state.readPages.split(',')
            .map(intervalOrPage => intervalOrPage.trim())
            .filter(intervalOrPage => intervalOrPage.length > 0)
            .map(intervalOrPage => {
                if (intervalOrPage.match(singlePageRegex)) {
                    var page = parseInt(intervalOrPage);
                    return { beginPage: page, endPage: page }
                }
                
                var intervalPages = intervalOrPage.split('-').map(page => parseInt(page.trim()));
                return { beginPage: intervalPages[0], endPage: intervalPages[1] };
            });
    }

    calculateReadPagesCount(readActivities) {
        return readActivities.reduce(
            (sum, readActivity) => sum + (readActivity.endPage - readActivity.beginPage + 1), 
            0
        );
    }

    render() {
        return (
            <form className="form-horizontal">
                <BookTitle title={this.state.title} updateState={this.updateTitle} />
                <BookAuthor author={this.state.author} updateState={this.updateAuthor} />
                <BookTotalPages totalPages={this.state.totalPages} updateState={this.updateTotalPages} />
                <BookReadPages readPages={this.state.readPages} updateState={this.updateReadPages} />
                <AddBookButton onClick={this.onAddButtonClick} />
            </form>
        );
    }
}

class BookTitle extends React.Component {
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        this.props.updateState(e.target.value);
    }

    render() {
        return (
            <div className="form-group">
                <label htmlFor="book-title" className="col-sm-2 control-label">
                    Title
                </label>
                <div className="col-sm-10">
                    <input id="book-title" type="text" className="form-control"
                        value={this.props.title} onChange={this.handleChange}
                        placeholder="e.g. C# in Depth, Third Edition"
                        aria-describedby="book-title-description" />
                    <small id="book-title-description" className="form-text text-muted">
                        The book you will start or continue to read
                    </small>
                </div>
            </div>
        );
    }
}

class BookAuthor extends React.Component {
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        this.props.updateState(e.target.value);
    }

    render() {
        return (
            <div className="form-group">
            <label htmlFor="book-author" className="col-sm-2 control-label">
                Author
            </label>
            <div className="col-sm-10">
                <input id="book-author" className="form-control" type="text"
                    value={this.props.author} onChange={this.handleChange}
                    placeholder="e.g. Jon Skeet"
                    aria-describedby="book-author-description" />
                <small id="book-author-description" className="form-text text-muted">
                    The person who wrote the book
                </small>
            </div>
        </div>
        );
    }
}

const TOTAL_PAGES_REGEX = /^\d*$/;

class BookTotalPages extends React.Component {
    
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        const value = e.target.value;
        console.log('value:', value);
        if (this.validate(value)) {
            const totalPages = parseInt(value, 10);
            console.log('totalPages:', totalPages);
            this.props.updateState(totalPages);
        }
    }

    validate(value) {
        return !!value.match(TOTAL_PAGES_REGEX);
    }

    render() {
        return (
            <div className="form-group">
                <label htmlFor="book-total-pages-count" className="col-sm-2 control-label">
                    Total Pages Count
                </label>
                <div className="col-sm-10">
                    <input id="book-total-pages-count" className="form-control" type="number" min="0"
                        value={this.props.totalPages} onChange={this.handleChange}
                        placeholder="e.g. 512"
                        aria-describedby="book-total-pages-count-description" />
                    <small id="book-total-pages-count-description" className="form-text text-muted">
                        How big or small is the book?
                    </small>
                </div>
            </div>
        );
    }
}

const readPagesIntervalRegex = /^\d+\s*(-\s*\d+)?$/g,
      readPagesBeginIntervalRegex = /^\d+\s*(-\s*)?$/g;

class BookReadPages extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        const value = e.target.value;
        if (this.validate(value)) {
            this.props.updateState(value);
        }
    }

    validate(val) {
        if (val.length == 0) {
            return true;
        }
        var readPagesIntervals = val.split(',').map(function(interval) {
            return interval.trim();
        });
        if (!readPagesIntervals.length) {
            return false;
        }
        var lastInterval = readPagesIntervals[readPagesIntervals.length - 1];
        if (lastInterval && !lastInterval.match(readPagesIntervalRegex) && !lastInterval.match(readPagesBeginIntervalRegex)) {
            return false;
        }
        var leadIntervals = readPagesIntervals.slice(0, -1);
        var incorrectIntervals = leadIntervals.filter(function(interval) {
            var res = !interval.match(readPagesIntervalRegex);
            return res;
        })
        if (incorrectIntervals.length > 0) {
            return false;
        }
        return true;
    }

    render() {
        return (
            <div className="form-group">
                <label htmlFor="book-read-pages" className="col-sm-2 control-label">
                    Read Pages
                </label>
                <div className="col-sm-10">
                    <input id="book-read-pages" className="form-control" type="text"
                        value={this.props.readPages} onChange={this.handleChange}
                        placeholder="e.g. 0-128, 256 - 311, 512, 1024 - 1025"
                        aria-describedby="book-read-pages-description" />
                    <small id="book-read-pages-description" className="form-text text-muted">
                        Specific pages or intervals of pages that you have already read
                    </small>
                </div>
            </div>
        );
    }
}

class AddBookButton extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="form-group">
                <div className="col-sm-2"></div>
                <div className="col-sm-10">
                    <a id="book-add" href="#" role="button" className="btn btn-primary" onClick={this.props.onClick}>
                        Add Book
                    </a>
                </div>
            </div>
        );
    }
}

$(function() {
    var readPagesIntervalRegex = /^\d+\s*(-\s*\d+)?$/g,
        readPagesBeginIntervalRegex = /^\d+\s*(-\s*)?$/g;
    $('#book-read-pages').keypress(function(e) {
        
    });

    $('#book-add').click(function(e) {
        var title = $('#book-title').val();
        var author = $('#book-author').val();
        var totalPagesCount = $('#book-total-pages-count').val();
        var readPages = $('#book-read-pages').val();

        

        var data = {
            title: title,
            author: author,
            totalPagesCount: totalPagesCount,
            readProgress: { readPagesCount: readPagesCount },
            readHistories: readActivities
        };

        console.log('Sending request:', data);

        $(this).blur();
    });

    function extractReadActivities(opaque) {
        
    }

    function calculateReadPagesCount(readActivities) {
        return readActivities.reduce((sum, readActivity) => sum + (readActivity.endPage - readActivity.beginPage + 1), 0);
    }
});

module.exports = {
    AddBookComponent: AddBookComponent
}