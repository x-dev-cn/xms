/**
 * Created by felix on 2016-07-09-0009.
 */
/**
 Custom module for you to write your own javascript functions
 **/
var custom = function () {

    var handlerGetPostData = function () {
        var data = {};
        var input = $('input[type="text"]').each(
            function () {
                if ($('input[name="' + $(this).attr('name') + '"]').length > 1 && !data[$(this).attr('name')]) {
                    $('input[name="' + $(this).attr('name') + '"]').each(function () {
                        if (!data[$(this).attr('name')]) {
                            data[$(this).attr('name')] = $(this).val();
                        }
                        else {
                            data[$(this).attr('name')] = data[$(this).attr('name')] + '|' + $(this).val();
                        }
                    });
                } else {
                    if (!data[$(this).attr('name')])
                        data[$(this).attr('name')] = $(this).val();
                }
            });

        var select = $('select').each(
            function () {
                data[$(this).attr('name')] = $(this).find('option:selected').val();
            });

        var textarea = $('textarea').each(
            function () {
                data[$(this).attr('name')] = $(this).val();
            });

        var radio = $('input[type="radio"]').each(
            function () {
                if ($(this).attr('checked') == "checked") {
                    data[$(this).attr('name')] = $(this).val();
                }
            });

        var checkbox_group = '';
        var checkbox_val;

        var checkbox = $('input[type="checkbox"]').each(
            function () {
                if ($(this).attr('checked') == "checked") {
                    if (checkbox_group == $(this).attr('name')) {
                        checkbox_val += ',' + $(this).val();
                    } else {
                        if (checkbox_val) {
                            data[checkbox_group] = checkbox_val;
                        }
                        checkbox_group = $(this).attr('name');
                        checkbox_val = $(this).val();
                    }
                }
            });

        if (checkbox_val) {
            data[checkbox_group] = checkbox_val;
        }

        return data;
    }

    var handlerSetPostData = function (json) {
        if (json) {
            $.each(json, function (key, value) {
                switch ($('input[name="' + key + '"]').attr('type')) {
                    case 'text':
                        $('input[name=' + key + ']').val(value);
                        $('input[name=' + key + ']').focus();
                        break;
                    case 'checkbox':
                        $('input[name="' + key + '"]').each(
                            function () {
                                var val = value.split(',');
                                for (i = 0; i < val.length; i++) {
                                    if ($(this).val() == val[i]) {
                                        $(this).attr('checked', "checked");
                                    }
                                }
                            });
                        break;
                    case 'radio':
                        $('input[name="' + key + '"]').each(
                            function () {
                                if ($(this).val() == value) {
                                    $(this).attr('checked', "checked");
                                }
                            });
                        break;
                    case 'hidden':
                        break;
                    default:
                        $('input[name=' + key + ']').val(value);
                        $('input[name=' + key + ']').focus();
                        break;
                }
                $('select[name="' + key + '"]').find('option[value="' + value + '"]').attr('selected', true);
                $('textarea[name="' + key + '"]').html(value);
                $('textarea[name=' + key + ']').focus();
            });

        }
    }

    // public functions
    return {

        //main function
        init: function () {
            //initialize here something.
        },
        showSuccessMsg: function (msg, title) {
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "positionClass": "toast-bottom-right",
                "onclick": null,
                "showDuration": "1000",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
            var $toast = toastr['success'](msg, title);
        },
        showInfoMsg: function (msg, title) {
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "positionClass": "toast-bottom-right",
                "onclick": null,
                "showDuration": "1000",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
            var $toast = toastr['info'](msg, title);
        },
        showWarningMsg: function (msg, title) {
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "positionClass": "toast-bottom-right",
                "onclick": null,
                "showDuration": "1000",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
            var $toast = toastr['warning'](msg, title);
        },
        showErrorMsg: function (msg, title) {
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "positionClass": "toast-bottom-right",
                "onclick": null,
                "showDuration": "1000",
                "hideDuration": "1000",
                "timeOut": "0",
                "extendedTimeOut": "0",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
            var $toast = toastr['error'](msg, title);
        },
        getFormData: function () {
            return handlerGetPostData()
        },
        setFormData: function (json) {
            return handlerSetPostData(json)
        }

    };

}();
