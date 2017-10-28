'use strict';

const React = require('react');

class BookTitleHeaderComponent extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <h1 className="book-title-h1">
                {this.props.title}
            </h1>
        );
    }
}

module.exports = {
    BookTitleHeaderComponent: BookTitleHeaderComponent
}