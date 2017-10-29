const path = require('path');
const fs = require('fs');

const mkdirSync =  (dirPath) => {
    try {
        fs.mkdirSync(dirPath);
    } catch (err) {
        if (err.code !== 'EEXIST') throw err
    }
};

const mkdirpSync = function (dirPath) {
    const dirsToCreate = [];
    var success = false;
    do {
        try {
            mkdirSync(dirPath);
            success = true;
        } catch (err) {
            if (err.code !== 'ENOENT') {
                throw err;
            }
            dirsToCreate.push(dirPath);
            dirPath = path.dirname(dirPath);
        }
    } while (!success);

    for (let i = dirsToCreate.length - 1; i != -1; --i) {
        let creatingDir = dirsToCreate[i];
        mkdirSync(creatingDir);
    }
};

module.exports = {
    mkdirSync: mkdirSync,
    mkdirpSync: mkdirpSync,
};