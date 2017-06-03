module.exports = {
    toChunks: (arr, n) => {
        var res = []
        while (arr.length > 0) {
            res.push(arr.splice(0, n));
        }
        return res;
    }
};