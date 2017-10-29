const configuration = require('react-global-configuration');
const config = require('config');
const path = require('path');
const fs = require('fs');
const mkdirpSync = require('../common/utils').mkdirpSync;

configuration.set(config);

const renderConfigs = (configPath) => {
    const configDirPath = path.dirname(configPath);
    mkdirpSync(path.dirname(configPath));
    fs.writeFileSync(
        configPath, 
        `window.__INITIAL_CONFIG__ = ${configuration.serialize()};`
    );
    console.log('config was created: configPath=', configPath);
}

module.exports = renderConfigs;