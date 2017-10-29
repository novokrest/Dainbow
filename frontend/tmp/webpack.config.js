'use strict';

module.exports = {

    entry: './main.js',

    output: {
        path: __dirname + '/public',
        filename: '[name]-out.js'
    },

    module: {
        loaders: [
            {
                test: /\.js/,
                loader: 'ffff'
            }
        ]
    }
};