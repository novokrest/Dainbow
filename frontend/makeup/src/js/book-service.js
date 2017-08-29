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
        return Math.floor(this._readPagesCount / this._totalPagesCount * 100);
    }
};

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
                const readPagesCount = this._calculateReadPagesCount(bookId);
                result[bookId] = new BookReadProgress(bookId, readPagesCount, book.totalPagesCount);
                return result;
            },
            {}
        );
    }

    _calculateReadPagesCount(bookId) {
        const activities = _.sortBy(
            _.filter(this._readActivities, activity => activity.bookId === bookId), 
            activity => activity.beginPage
        );
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

module.exports = {
    BookService: BookService
}