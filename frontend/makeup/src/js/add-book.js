'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

const ApiClient = require('./api-client').ApiClient;

class AddBookForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = { title: '' };

        this.updateTitle = this.updateTitle.bind(this);
        this.onAddButtonClick = this.onAddButtonClick.bind(this);
    }

    updateTitle(title) {
        console.log('updateTitle', title);
        this.setState({ title: title });
    }

    onAddButtonClick() {
        console.log('onAddButtonClick()', this.state);
        ApiClient.postBook(this.state.book, () => {
            console.log('Request has been sent successfully');
            // TODO: implement proper navigation
            var currentUrl = window.location.href;
            window.location.href = currentUrl.substr(0, currentUrl.lastIndexOf('/'));
        });
    }

    render() {
        return (
            <form className="form-horizontal">
                <BookTitle title={this.state.title} updateState={this.updateTitle} />
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

class AddBookButton extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="form-group">
                <div className="col-sm-2"></div>
                <div className="col-sm-10">
                    <a id="book-add" 
                       href="#" role="button" className="btn btn-primary" 
                       onClick={this.props.onClick}>
                        Add Book
                    </a>
                </div>
            </div>
        );
    }
}

ReactDOM.render(
    <AddBookForm />,
    document.getElementById('addBookForm')
);

$(function() {
    var readPagesIntervalRegex = /^\d+\s*(-\s*\d+)?$/g,
        readPagesBeginIntervalRegex = /^\d+\s*(-\s*)?$/g;
    $('#book-read-pages').keypress(function(e) {
        var val = $(this).val() + e.key;
        var readPagesIntervals = val.split(',').map(function(interval) {
            return interval.trim();
        });
        if (!readPagesIntervals.length) {
            return;
        }
        var lastInterval = readPagesIntervals[readPagesIntervals.length - 1];
        if (lastInterval && !lastInterval.match(readPagesIntervalRegex) && !lastInterval.match(readPagesBeginIntervalRegex)) {
            e.preventDefault();
            return;
        }
        var leadIntervals = readPagesIntervals.slice(0, -1);
        var incorrectIntervals = leadIntervals.filter(function(interval) {
            var res = !interval.match(readPagesIntervalRegex);
            return res;
        })
        if (incorrectIntervals.length) {
            e.preventDefault();
        }
    });

    $('#book-add').click(function(e) {
        var title = $('#book-title').val();
        var author = $('#book-author').val();
        var totalPagesCount = $('#book-total-pages-count').val();
        var readPages = $('#book-read-pages').val();

        var readActivities = extractReadActivities(readPages);
        var readPagesCount = calculateReadPagesCount(readActivities);

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
        var singlePageRegex = /^\d+$/g,
            pageIntervalRegex = /^\d+\s*-\s*\d+$/g;
        return opaque.split(',')
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

    function calculateReadPagesCount(readActivities) {
        return readActivities.reduce((sum, readActivity) => sum + (readActivity.endPage - readActivity.beginPage + 1), 0);
    }
});