const _ = require('lodash');

class BookReadProgress {
    constructor(bookId, readPagesCount, totalPagesCount) {
        this._bookId = bookId;
        this._readPagesCount = readPagesCount;
        this._totalPagesCount = totalPagesCount;
    }

    get bookId() {
        return this._bookId;
    }

    get readPagesCount() {
        return this._readPagesCount;
    }

    get totalPagesCount() {
        return this._totalPagesCount;
    }

    get readPercent() {
        return this._totalPagesCount == 0 
            ? 0 
            : Math.floor(this._readPagesCount / this._totalPagesCount * 100);
    }
};

class BookPageService {
    constructor(book, readActivities) {
        this.book = book;
        this.readActivities = readActivities;
    }

    isPageRead(pageNumber) {
        return _.find(
            this.readActivities, 
            readActivity => pageNumber >= readActivity.beginPage && pageNumber <= readActivity.endPage
        ) != undefined;
    }

    calculateReadProgress() {
        const book = this.book;
        const readPagesCount = this._calculateReadPagesCount();
        return new BookReadProgress(book.id, readPagesCount, book.totalPagesCount);
    }

    _calculateReadPagesCount() {
        const activities = _.sortBy(this.readActivities, activity => activity.beginPage);
        const toInterval = activity => { return {begin: activity.beginPage, end: activity.endPage} };
        const nonIntersectIntervals = _.reduce(
            activities, 
            (intervals, activity) => {
                if (intervals.length == 0) {
                    return [toInterval(activity)]
                }
                const last = _.last(intervals);
                if (activity.beginPage > last.begin) {
                    return _.concat(intervals, toInterval(activity));
                }
                return _.concat(
                    _.slice(intervals, 0, intervals.length - 1),
                    { begin: activity.beginPage, end: Math.max(activity.endPage, last.end) }
                ); 
            }, 
            []
        );
        return _.reduce(
            nonIntersectIntervals, 
            (result, interval) => result + interval.end - interval.begin + 1,
            0
        );
    }
}

class BookService {

    constructor(books, readActivities) {
        this._books = books;
        this._readActivities = readActivities;
    }

    /**
     * Returns map [bookId -> BookReadProgress]
     */
    calculateReadProgresses() {
        return _.reduce(
            this._books, 
            (result, book) => {
                const bookId = book.id;
                result[bookId] = this._calculateReadProgress(book);
                return result;
            },
            {}
        );
    }

    _calculateReadProgress(book) {
        const activities = _.filter(this._readActivities, activity => activity.bookId === book.id);
        const service = new BookPageService(book, activities);
        return service.calculateReadProgress();
    }
}

module.exports = {
    BookReadProgress: BookReadProgress,
    BookService: BookService,
    BookPageService: BookPageService
}