/**
 * Created by felix on 16/7/12.
 */

var vip_list = function () {
    var url = document.URL;
    if (url.lastIndexOf('/') != url.length - 1) {
        url += '/';
    }

    var actBaseUrl = "/uc/vip/";

    var handleDatatable = function () {

        if (!jQuery().DataTable) {
            return;
        }

        // var data = custom.getFormData();
        var dataTables = $('#listTable').DataTable({
            // "dom": '<"toolbar">frtip',
            "paginate": true, //翻页功能
            "lengthChange": true, //改变每页显示数据数量
            "filter": true, //过滤功能
            "sort": false, //排序功能
            "orderCellsTop": true,
            "info": true,//页脚信息
            "autoWidth": false,//自动宽度
            "language": {
                url: "//cdn.datatables.net/plug-ins/1.10.10/i18n/Chinese.json"
            },
            "processing": true,
            "serverSide": true,
            'stateSave': true,//刷新后停留在当前页
            "ajax": {
                "url": url,
                "data": function (d) {
                },
                "headers": {
                    "table": "true"
                }
            },
            "columns": [
                {"data": "realname"},
                {"data": "mobile"},
                {"data": "create_date"},
                {"data": "locked"},
                {"data": "id"}
            ],
            "lengthMenu": [
                [10, 20, 50],
                [10, 20, 50] // change per page values here
            ],
            "columnDefs": [
                {
                    "targets": 3,
                    "searchable": false,
                    "render": function (data, type, row) {
                        var display = '';
                        if (row.locked) {
                            display = '<lable class="text-yellow">封号<lable>';
                        } else {
                            display = '<lable class="text-light-blue">正常<lable>';
                        }
                        return display;
                    }
                },
                {
                    "targets": 4,
                    "searchable": false,
                    "render": function (data, type, row) {
                        var vdd = '';
                        if (row.locked) {
                            vdd += '  <a name="enable" class="btn btn-xs btn-success enable" data-toggle="modal" href="#"  rid=' + row.id + ' cid=' + row.column_id + '>解封</a>';
                        } else {
                            vdd += '  <a name="disable" class="btn btn-xs btn-warning disable" data-toggle="modal" href="#"  rid=' + row.id + ' cid=' + row.column_id + '>封号</a>';
                        }
                        return vdd;
                    }
                }
            ]
        });

        // apply the special class that used to restyle the default datatable
        var tmp = $.fn.dataTableExt.oStdClasses;
        // revert back to default
        $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
        $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
        $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

        $.fn.dataTable.ext.search.push(
            function (settings, data, dataIndex) {
                var min = parseInt($('#min').val(), 10);
                var max = parseInt($('#max').val(), 10);
                var age = parseFloat(data[3]) || 0; // use data for the age column

                if (( isNaN(min) && isNaN(max) ) ||
                    ( isNaN(min) && age <= max ) ||
                    ( min <= age && isNaN(max) ) ||
                    ( min <= age && age <= max )) {
                    return true;
                }
                return false;
            }
        );

        var tableContainer = $('#listTable').parents(".table-container");
        // get table wrapper
        var tableWrapper = $('#listTable').parents('.dataTables_wrapper');

        // build table group actions panel
        if ($('.table-actions-wrapper', tableContainer).size() === 1) {
            $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html()); // place the panel inside the wrapper
            $('.table-actions-wrapper', tableContainer).remove(); // remove the template container
        }

        $('#listTable').on('click', '.disable', function (e) {
            e.preventDefault();

            var disable_url = url + $(this).attr('rid') + '/disable';
            $.ajax({
                url: disable_url,
                type: 'post',
                error: function () {
                },
                success: function (json) {
                    json = JSON.parse(json);
                    if (json.success) {
                        dataTables.ajax.reload(null, false);
                        custom.showSuccessMsg('已封号。', '操作成功！')
                    } else {
                        custom.showErrorMsg('请稍后再试，如多次失败请联系系统管理员。', '操作失败！')
                    }
                }
            });
        });

        $('#listTable').on('click', '.enable', function (e) {
            e.preventDefault();

            var enable_url = url + $(this).attr('rid') + '/enable';

            $.ajax({
                url: enable_url,
                type: 'post',
                error: function () {
                },
                success: function (json) {
                    json = JSON.parse(json);
                    if (json.success) {
                        dataTables.ajax.reload(null, false);
                        custom.showSuccessMsg('已解封。', '操作成功！')
                    } else {
                        custom.showErrorMsg('请稍后再试，如多次失败请联系系统管理员。', '操作失败！')
                    }
                }
            });
        });

    }

    return {
        //main function to initiate the module
        init: function () {
            handleDatatable();
        }

    };
}();