'use strict';

const React = require('react');

const ApiClient = require('./api-client').ApiClient;
const _ = require('lodash');

class ReadActivitiesTableComponent extends React.Component {

    constructor(props) {
        super(props);
        this.onReadActivityRemoved = props.onReadActivityRemoved;
    }

    removeReadActivity(id) {
        console.log('Remove: id=' + id);
        ApiClient.deleteReadActivity(id, () => {
            this.onReadActivityRemoved(id);
        });
    }

    render() {
        const readActivities = this.props.readActivities;
        const readActivityRows = _.map(readActivities, (readActivity, i) => (
            <tr key={i}>

                <td>{readActivity.id}</td>
                <td>{readActivity.beginPage}</td>
                <td>{readActivity.endPage}</td>
                <td>
                    <div className="text-center">
                        {readActivity.beginTime}
                    </div>
                </td>
                <td>{readActivity.endTime}</td>
                <td>
                    <button className="btn btn-danger" onClick={() => this.removeReadActivity(readActivity.id)}>Remove</button>
                </td>
            </tr>
        ));
        return (
            <div className="table-responsive">
                <table className="table table-striped text-center read-activity-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Begin Page</th>
                            <th>End Page</th>
                            <th>Begin Time</th>
                            <th>End Time</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {readActivityRows}
                    </tbody>
                </table>
          </div>
        );
    }
}

module.exports = {
    ReadActivitiesTableComponent: ReadActivitiesTableComponent
}