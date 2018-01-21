'use strict';

const path = require('path');
const webpack = require('webpack');
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
const CleanWebpackPlugin = require('clean-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const NODE_DEV = process.env.NODE_ENV;
const makeupCss = path.join(__dirname, 'makeup/src/css');
const distDirPath = path.join(__dirname, 'public/dist');
const genDirPath = path.join(__dirname, 'public/gen');

//TODO: use as GULP task
const renderConfigs = require('./config/render-configs');
const configsPath = path.join(genDirPath, 'js/config.js');
renderConfigs(configsPath);

const isProd = process.env.NODE_ENV === 'production'

module.exports = {

    devtool: isProd ? false : '#source-map',
    cache: true,

    watch: !isProd,
    watchOptions: {
        aggregateTimeout: 100,
        ignored: /node_modules/
    },
    
    context: path.join(__dirname, 'makeup/src'),
    entry: {
        app: './js/app.js',
        vendors: './js/vendors.js',
        LogReactActivityPage: './js/LogReactActivityPage.js'
    },

    output: {
        path: distDirPath,
        filename: 'js/[name].js'
    },

    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                exclude: /(node_modules)/,
            },
            {
                test: /^$/, 
                loader: 'eslint-loader', 
                exclude: /(node_modules)/
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
            // { from: 'node_modules/jquery/dist/jquery.min.js', to: 'js/jquery.min.js' },

            // { from: 'node_modules/moment/min/moment.min.js', to: 'js/moment.min.js'},

            // { from: 'node_modules/bootstrap/js/collapse.js', to: 'js/collapse.js'},

            // { from: 'node_modules/bootstrap/js/transition.js', to: 'js/transition.js'},
            
            { from: 'node_modules/bootstrap/dist/css/bootstrap.min.css', to: 'css/bootstrap.min.css' },
            // { from: 'node_modules/bootstrap/dist/js/bootstrap.min.js', to: 'js/bootstrap.min.js' },

            { from: 'node_modules/bootstrap/dist/fonts', to: 'fonts'},
            
            { from: 'node_modules/bootstrap-datepicker/dist/css/bootstrap-datepicker3.min.css', to: 'css/bootstrap-datepicker3.min.css' },
            // { from: 'node_modules/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js', to: 'js/bootstrap-datepicker.min.js' },

            // { from: 'node_modules/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js', to: 'js/bootstrap-datetimepicker.min.js'},
            { from: 'node_modules/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css', to: 'css/bootstrap-datetimepicker.min.css'},

            { from: 'node_modules/tether/dist/css/tether.min.css', to: 'css/tether.min.css' },
            // { from: 'node_modules/tether/dist/js/tether.min.js', to: 'js/tether.min.js' },

            { from: 'makeup/src/js/pull-down.js', to: 'js/pull-down.js' },

            //TODO: use require('*.css') and loaders
            { from: path.join(makeupCss, 'main.css'), to: 'css/main.css' },
            { from: path.join(makeupCss, 'overview.css'), to: 'css/overview.css' },
        ], {
            context: __dirname
        }),

        new BundleAnalyzerPlugin({
            analyzerMode: 'static',
            openAnalyzer: false
        }),

        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendors',
            filename: 'js/vendors.js',
            minChunks(module, count) {
                var context = module.context;
                return context && context.indexOf('node_modules') >= 0;
            },
        }),

        new HtmlWebpackPlugin({
            template: 'html/index.html',
            filename: 'index.html',
            inject: 'body',
            chunks: ['app', 'vendors']
        })
    ]
};
