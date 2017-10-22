'use strict';

const React = require('react');

class BookProgressComponent extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const readProgress = this.props.readProgress;
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
    BookProgressComponent: BookProgressComponent
};