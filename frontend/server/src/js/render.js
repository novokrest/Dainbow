const configuration = require('react-global-configuration');
const config = require('config');
const fs = require('fs');

configuration.set(config);

const renderConfigs = (path) => 
    fs.writeFileSync(
        path, 
        `window.__INITIAL_CONFIG__ = ${configuration.serialize()};`
    );

module.exports = {
    renderConfigs: renderConfigs
};