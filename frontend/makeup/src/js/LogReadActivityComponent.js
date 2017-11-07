'use strict';

const React = require('react');
const BookTitleHeaderComponent = require('./BookTitleHeaderComponent').BookTitleHeaderComponent;

const ApiClient = require('./api-client').ApiClient;
const RouteService = require('./routes').RouteService;

const dateformat = require('dateformat');

class LogReadActivityComponent extends React.Component {

    constructor(props) {
        super(props);
        this.id = props.match.params.id;
        this.state = { 
            book: {}, 
            beginPage: '', endPage: '', 
            date: new Date(), 
            beginTime: new Date(), endTime: new Date() 
        };

        this.updateBeginPage = this.updateBeginPage.bind(this);
        this.updateEndPage = this.updateEndPage.bind(this);
        this.updateDate = this.updateDate.bind(this);
        this.updateBeginTime = this.updateBeginTime.bind(this);
        this.updateEndTime = this.updateEndTime.bind(this);
        this.logReadActivity = this.logReadActivity.bind(this);
    }

    getFormattedDate() {

    }

    componentDidMount() {
        ApiClient.getBook(this.id, book => {
            console.log('book=', book);
            this.setState({ book: book });
        });
    }

    updateBeginPage(beginPage) {
        this.setState({ beginPage: beginPage });
    }

    updateEndPage(endPage) {
        this.setState({ endPage: endPage });
    }

    updateDate(date) {
        this.setState({ date: date });
    }

    updateBeginTime(beginTime) {
        this.setState({ beginTime: beginTime });
    }

    updateEndTime(endTime) {
        this.setState({ endTime: endTime });
    }

    logReadActivity() {
        const data = this.buildReadActivity();
        ApiClient.postReadActivity(data, () => {
            RouteService.navigateOverviewBookRoute(this.props.history, this.id);
        });
    }

    buildReadActivity() {
        const mBegin = this.state.beginTime;
        const mEnd = this.state.endTime;

        return {
            bookId: this.state.book.id,
            beginPage: this.state.beginPage,
            endPage: this.state.endPage,
            beginTime: this.formatIso(this.state.date, this.state.beginTime),
            endTime: this.formatIso(this.state.date, this.state.endTime)
        };
    }

    formatIso(date, time) {
        return `${dateformat(date, 'yyyy-mm-dd')}T${time.format('HH:mm:ss.SSS')}`;
    }

    render() {
        const book = this.state.book;
        return (
            <div className="container-fluid">
                <div className="row">
                    <main className="col-sm-10 col-sm-offset-1 pt-3">
                        <BookTitleHeaderComponent title={book.title} />
                        <form>
                            <PageInputComponent id="begin-page" label="Begin Page" page={this.state.beginPage} updateState={this.updateBeginPage} />
                            <PageInputComponent id="end-page" label="End Page" page={this.state.endPage} updateState={this.updateEndPage} />
                            <DatePickerComponent id="date" label="Date" date={this.state.date} updateState={this.updateDate}  />
                            <TimePickerComponent id="begin-time" label="Begin Time" time={this.state.beginTime} updateState={this.updateBeginTime}  />
                            <TimePickerComponent id="end-time" label="End Time" time={this.state.endTime} updateState={this.updateEndTime} />
                            <LogReadActivityButton onClick={this.logReadActivity} />
                        </form>
                    </main>
                </div>
            </div>
        );
    }
}

class PageInputComponent extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        const page = e.target.value ? parseInt(e.target.value) : '';
        this.props.updateState(page);
    }

    render() {
        return (
            <div className="form-group row">
                <label htmlFor={`${this.props.id}-input`} className="col-sm-2">{this.props.label}:</label>
                <div className="col-sm-4">
                    <input className="form-control" type="number" id={`${this.props.id}-input`} placeholder="1" 
                           value={this.props.page} onChange={this.handleChange}/>
                </div>
            </div>
        );
    }
}

class DatePickerComponent extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        const date = Date.parse(e.target.value);
        this.props.updateState(date);
    }

    formatDate(date) {
        const year = date.getFullYear();
        const month = date.getMonth() < 10 ? '0' + date.getMonth() : date.getMonth();
        const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
        return `${year}-${month}-${day}`;
    }

    render() {
        return (
            <div className="form-group row">
                <label htmlFor={`${this.props.id}-input`} className="col-sm-2 col-form-label">{this.props.label}:</label>
                <div className="col-sm-4">
                    <div className="input-group date" data-provide="datepicker" data-date-format="yyyy-mm-dd">
                        <input type="text" className="form-control" id={`${this.props.id}-input`}
                               value={this.formatDate(this.props.date)} onChange={this.handleChange} />
                        <div className="input-group-addon">
                            <span className="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

class TimePickerComponent extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        const that = this;
        const id = `#${this.props.id}-input`;
        $(() => {
            $(id).datetimepicker({
                format: 'LT'
            });
            $(id).on('dp.change', e => {
                this.handleChange(e.date);
            });
            
        });
    }

    handleChange(date) {
        const time = moment(date.format('HH:mm:ss.SSS'), 'HH:mm:ss.SSS');
        this.props.updateState(time);
    }

    render() {
        return (
            <div className="form-group row">
                <label htmlFor={`${this.props.id}-input`} className="col-sm-2 col-form-label">{this.props.label}:</label>
                <div className="col-sm-4">
                    <div className='input-group date' id={`${this.props.id}-input`}>
                        <input type='text' className="form-control" />
                        <span className="input-group-addon">
                            <span className="glyphicon glyphicon-time"></span>
                        </span>
                    </div>
                </div>
            </div>
        );
    }
}

class LogReadActivityButton extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="form-group row">
                <div className="col-sm-2"></div>
                <div className="col-sm-10">
                    <a id="log-react-button" href="#" role="button" className="btn btn-primary" onClick={this.props.onClick}>
                        Log Read Activity
                    </a>
                </div>
            </div>
        );
    }
}

module.exports = {
    LogReadActivityComponent: LogReadActivityComponent
}