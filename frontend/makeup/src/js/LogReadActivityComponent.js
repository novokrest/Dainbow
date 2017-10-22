'use strict';

const React = require('react');
const BookTitleHeaderComponent = require('./BookTitleHeaderComponent').BookTitleHeaderComponent;

const ApiClient = require('./api-client').ApiClient;

class LogReadActivityComponent extends React.Component {

    constructor(props) {
        super(props);
        this.id = props.match.params.id;
        this.state = { book: {} };
    }

    componentDidMount() {
        ApiClient.getBook(this.id, book => {
            this.setState({ book: book });
        });
    }

    render() {
        const book = this.state.book;
        const nowDt = new Date();
        const formattedNowDate = `${nowDt.getFullYear()}-${nowDt.getMonth() + 1}-${nowDt.getDate()}`;
        const formattedNowTime = `${nowDt.getHours()}:${nowDt.getMinutes()}:${nowDt.getSeconds()}`;
        return (
            <div className="container-fluid">
                <div className="row">
                    <main className="col-md-10 offset-md-2 pt-3">
                        <BookTitleHeaderComponent title={book.title} />
                        <form>
                            <div className="form-group row">
                                <label htmlFor="begin-page-input" className="col-2 col-form-label">Begin Page:</label>
                                <div className="col-10">
                                    <input className="form-control" type="number" id="begin-page-input" placeholder="ex. 37" />
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="end-page-input" className="col-2 col-form-label">End Page:</label>
                                <div className="col-10">
                                    <input className="form-control" type="number" id="end-page-input" placeholder="ex. 55" />
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="date-input" className="col-2 col-form-label">Date:</label>
                                <div className="col-10">
                                    <input className="form-control" type="date" value={formattedNowDate} id="date-input"/>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="begin-time-input" className="col-2 col-form-label">Time</label>
                                <div className="col-10">
                                    <input className="form-control" type="time" value={formattedNowTime} id="begin-time-input"/>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="end-time-input" className="col-2 col-form-label">Time</label>
                                <div className="col-10">
                                    <input className="form-control" type="time" value={formattedNowTime} id="end-time-input"/>
                                </div>
                            </div>
                        </form>
                    </main>
                </div>
            </div>
        );
    }
}

module.exports = {
    LogReadActivityComponent: LogReadActivityComponent
}