$(function() {
    var readPagesIntervalRegex = /^\d+\s*(-\s*\d+)?$/g,
        readPagesBeginIntervalRegex = /^\d+\s*(-\s*)?$/g;
    $('#book-read-pages').keypress(function(e) {
        var val = $(this).val() + e.key;
        var readPagesIntervals = val.split(',').map(function(interval) {
            return interval.trim();
        });
        if (!readPagesIntervals.length) {
            return;
        }
        var lastInterval = readPagesIntervals[readPagesIntervals.length - 1];
        if (lastInterval && !lastInterval.match(readPagesIntervalRegex) && !lastInterval.match(readPagesBeginIntervalRegex)) {
            e.preventDefault();
            return;
        }
        var leadIntervals = readPagesIntervals.slice(0, -1);
        var incorrectIntervals = leadIntervals.filter(function(interval) {
            var res = !interval.match(readPagesIntervalRegex);
            return res;
        })
        if (incorrectIntervals.length) {
            e.preventDefault();
        }
    });

    $('#book-add').click(function(e) {
        var title = $('#book-title').val();
        var author = $('#book-author').val();
        var totalPagesCount = $('#book-total-pages-count').val();
        var readPages = $('#book-read-pages').val();

        var readActivities = extractReadActivities(readPages);
        var readPagesCount = calculateReadPagesCount(readActivities);

        var data = {
            title: title,
            author: author,
            totalPagesCount: totalPagesCount,
            readProgress: { readPagesCount: readPagesCount },
            readHistories: readActivities
        };

        console.log('Sending request:', data);
        $.ajax({
            type: 'POST',
            url: '/api/auto/books',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function() {
                console.log('Request has been sent successfully');
                // TODO: implement proper navigation
                var currentUrl = window.location.href;
                window.location.href = currentUrl.substr(0, currentUrl.lastIndexOf('/'));
            },
            error: function() {
                console.log('Failed to send request');
            }
        });

        $(this).blur();
    });

    function extractReadActivities(opaque) {
        var singlePageRegex = /^\d+$/g,
            pageIntervalRegex = /^\d+\s*-\s*\d+$/g;
        return opaque.split(',')
            .map(intervalOrPage => intervalOrPage.trim())
            .filter(intervalOrPage => intervalOrPage.length > 0)
            .map(intervalOrPage => {
                if (intervalOrPage.match(singlePageRegex)) {
                    var page = parseInt(intervalOrPage);
                    return { beginPage: page, endPage: page }
                }
                
                var intervalPages = intervalOrPage.split('-').map(page => parseInt(page.trim()));
                return { beginPage: intervalPages[0], endPage: intervalPages[1] };
            });
    }

    function calculateReadPagesCount(readActivities) {
        return readActivities.reduce((sum, readActivity) => sum + (readActivity.endPage - readActivity.beginPage + 1), 0);
    }
});