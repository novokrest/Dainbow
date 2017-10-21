module.exports = {
    toChunks: (arr, chunkSize) => {
        var res = [];
        for (var i = 0; i < arr.length; i += chunkSize) {
            res.push(arr.slice(i, i + chunkSize));
        }
        return res;
    }
};