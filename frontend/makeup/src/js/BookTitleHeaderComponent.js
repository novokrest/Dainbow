'use strict';

const React = require('react');

class BookTitleHeaderComponent extends React.Component {

    constructor(props) {
        super(props);
        this.title = this.props.title;
    }

    render() {
        return (
            <h1 className="book-title-h1">
                {this.title}
            </h1>
        );
    }
}

module.exports = {
    BookTitleHeaderComponent: BookTitleHeaderComponent
}