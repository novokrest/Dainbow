$(function() {
    $('div.panel-heading').click(function() {
        var $sign = $(this).find('span.sign');
        var $panelBody = $(this).siblings('.panel-body');
        if ($panelBody.is(':visible')) {
            $panelBody.hide(1000, function() {
                $sign.removeClass('hideSign');
                $sign.addClass('showSign');
            });
        }
        else {
            $sign.removeClass('showSign');
            $sign.addClass('hideSign');
            $panelBody.show(1000);
        }
    });
});