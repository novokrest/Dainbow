'use strict';

const React = require('react');
const BookTitleHeaderComponent = require('./BookTitleHeaderComponent').BookTitleHeaderComponent;

const ApiClient = require('./api-client').ApiClient;
const RouteService = require('./routes').RouteService;

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
        return {
            bookId: this.state.book.id,
            beginPage: this.state.beginPage,
            endPage: this.state.endPage,
            beginTime: this.state.beginTime,
            endTime: this.state.endTime
        };
    }

    render() {
        const book = this.state.book;
        const nowDt = new Date();
        const formattedNowDate = `${nowDt.getFullYear()}-${nowDt.getMonth() + 1}-${nowDt.getDate()}`;
        const formattedNowSeconds = nowDt.getSeconds() < 10 ? `0${nowDt.getSeconds()}` : nowDt.getSeconds().toString();
        const formattedNowTime = `${nowDt.getHours()}:${nowDt.getMinutes()}:${formattedNowSeconds}`;
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
        const id = `#${this.props.id}-input`;
        $(() => {
            $(id).datetimepicker({
                format: 'LT'
            });
            $(`${id} input`).change(e => {
                console.log('on:', e);
            });
            
        });
    }

    handleChange(e) {
        console.log('time=', e.target.value);
        const time = Date.parse(e.target.value);
        this.props.updateState(time);
    }

    formatTime(time) {
        if (!time) {
            return time;
        }
        const hours = time.getHours() < 10 ? '0' + time.getHours() : time.getHours();
        const minutes = time.getMinutes() < 10 ? '0' + time.getMinutes() : time.getMinutes();
        return `${hours}:${minutes}`;
        return time;
    }

    render() {
        return (
            <div className="form-group row">
                <label htmlFor={`${this.props.id}-input`} className="col-sm-2 col-form-label">{this.props.label}:</label>
                <div className="col-sm-4">
                    <div className='input-group date' id={`${this.props.id}-input`} value={this.formatTime(this.props.time)} onChange={this.handleChange}>
                        <input type='text' className="form-control" 
                               value={this.formatTime(this.props.time)} onChange={this.handleChange} />
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