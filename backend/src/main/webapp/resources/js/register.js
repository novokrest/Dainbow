$(function() {
    $('input[name="coverImage"]').change(function() {
        if (this.files && this.files[0]) {
            var $this = $(this);
            var reader = new FileReader();

            reader.onload = function(e) {
                var $img = $('div.cover-viewer').children('img'),
                    $textInput = $('div.cover-viewer').children('input[name="coverBase64"]');

                $img.attr('src', e.target.result);
                $textInput.val(e.target.result);
            };

            reader.readAsDataURL(this.files[0]);
        }
    });

    $('input[name="coverImage"]').change(function() {
        var $input = $(this),
            label = $input.val().replace(/\\/g, '/').replace(/.*\//, '');
        var $labelTextInput = $input.parents('.input-group').find(':text');
        $labelTextInput.val(label);
    });
});