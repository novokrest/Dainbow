'use strict';

const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

const NODE_DEV = process.env.NODE_ENV;
const makeupCss = path.join(__dirname, 'makeup/src/css');
const distDirPath = path.join(__dirname, 'public/dist');
const genDirPath = path.join(__dirname, 'public/gen');

//TODO: use as GULP task
const renderConfigs = require('./config/render-configs');
const configsPath = path.join(genDirPath, 'js/config.js');
renderConfigs(configsPath);

module.exports = {
    
    context: path.join(__dirname, 'makeup/src'),
    entry: {
        app: './js/app.js'
    },

    output: {
        path: distDirPath,
        filename: 'js/[name].js'
    },

    devtool: 'source-map',
    cache: true,

    watch: NODE_DEV == 'dev',
    watchOptions: {
        aggregateTimeout: 100,
        ignored: /node_modules/
    },

    module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                loader: 'babel',
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            },
            {
                test: /\.css$/,
                loader: 'file?name=[name].[ext]',
                options: {
                    context: __dirname,
                    outputPath: path.join(distDirPath, 'css'),
                    name: '[name].[ext]' 
                }
            }
        ]
    },

    plugins: [
        new CleanWebpackPlugin([distDirPath]),
        new CopyWebpackPlugin([
            { from: 'node_modules/jquery/dist/jquery.min.js', to: 'js/jquery.min.js' },
            
            { from: 'node_modules/bootstrap/dist/css/bootstrap.min.css', to: 'css/bootstrap.min.css' },
            { from: 'node_modules/bootstrap/dist/js/bootstrap.min.js', to: 'js/bootstrap.min.js' },
            
            { from: 'node_modules/bootstrap-datepicker/dist/css/bootstrap-datepicker3.min.css', to: 'css/bootstrap-datepicker3.min.css' },
            { from: 'node_modules/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js', to: 'js/bootstrap-datepicker.min.js' },

            { from: 'node_modules/tether/dist/css/tether.min.css', to: 'css/tether.min.css' },
            { from: 'node_modules/tether/dist/js/tether.min.js', to: 'js/tether.min.js' },

            //TODO: use require('*.css') and loaders
            { from: path.join(makeupCss, 'main.css'), to: 'css/main.css' },
            { from: path.join(makeupCss, 'overview.css'), to: 'css/overview.css' },
        ], {
            context: __dirname
        })
    ]
};