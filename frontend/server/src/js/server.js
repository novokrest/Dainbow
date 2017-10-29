const configuration = require('react-global-configuration');
const config = require('config');
const express = require('express');
const jsonfile = require('jsonfile');
const path = require('path');
const process = require('process');
const fs = require('fs');

const app = express();

const logRouter = express.Router();
logRouter.use(function (req, res, next) {
    console.log(`${req.method} ${req.url} ${req.path}`);
    next();
});
app.use('/', logRouter);

if (config.stubMode) {
    const apiRouter = express.Router();
    apiRouter.use('/books', function (req, res, next) {
        var books = jsonfile.readFileSync(path.join(__dirname, '../resources/stub', 'books.json'));
        res.json(books);
    });
    apiRouter.use(function (req, res, next) {
        res.json({});
    });
    app.use('/api/auto', apiRouter);
}

const rootUrl = config.server.rootUrl;
const publicRootPath = path.join(process.cwd(), 'public');
app.use(rootUrl, express.static(publicRootPath));

const port = config.server.port;
app.listen(port, function () {
    console.log(`Express server is running: port=${port}, url=${rootUrl}`);
});