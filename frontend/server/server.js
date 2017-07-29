const config = require('config');
const express = require('express');
const path = require('path');

const app = express();

const logRouter = express.Router();
logRouter.use(function (req, res, next) {
    console.log(`${req.method} ${req.url} ${req.path}`);
    next();
});
app.use('/', logRouter);

const rootUrl = config.server.rootUrl;
app.use(rootUrl, express.static(path.join(__dirname, '../public')));

const port = config.server.port;
app.listen(port, function () {
    console.log(`Express server is running: port=${port}, url=${rootUrl}`);
});