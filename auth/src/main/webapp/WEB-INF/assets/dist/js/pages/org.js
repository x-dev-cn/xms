/**
 * Created by felix on 2016-06-26-0026.
 */
var org = function () {

    var handleTree = function (contextPath) {
        var json_data = [];
        $.ajax({
            url: contextPath + "/organization/tree",
            type: 'get',
            error: function () {
            },
            success: function (json) {
                json_data = JSON.parse(json);
                for (var i = 0; i < json_data.length; i++) {
                    json_data[i].text = json_data[i].name;
                    if (json_data[i].parent_id == 0) {
                        json_data[i].parent = '#';
                    } else {
                        json_data[i].parent = json_data[i].parent_id + '';
                    }
                }
                $("#jstree").jstree({
                    "core": {
                        "themes": {
                            "responsive": false,
                            //"stripes": true
                        },
                        // so that create works
                        "check_callback": true,
                        'data': json_data,
                        "cache": false,
                        "multiple": false
                    },
                    'contextmenu': {
                        'items': function (node) {
                            var tmp = $.jstree.defaults.contextmenu.items();
                            //delete tmp.create.action;
                            tmp.create.label = "新建";
                            tmp.rename.label = "重命名";
                            tmp.remove.label = "删除";
                            tmp.ccp.label = "编辑";
                            tmp.ccp.submenu.cut.label = "剪切";
                            tmp.ccp.submenu.copy.label = "复制";
                            tmp.ccp.submenu.paste.label = "粘贴";
                            delete tmp.ccp;
                            return tmp;
                        }
                    },
                    "types": {
                        "default": {
                            "icon": "fa fa-folder icon-state-warning icon-lg"
                        },
                        "file": {
                            "icon": "fa fa-file icon-state-warning icon-lg"
                        }
                    },
                    'sort': function (a, b) {
                        return this.get_type(a) === this.get_type(b) ? (this.get_text(a) > this.get_text(b) ? 1 : -1) : (this.get_type(a) >= this.get_type(b) ? 1 : -1);
                    },
                    "plugins": ["contextmenu", "dnd", "search", "state", "types", "wholerow"]
                })
                    .on('delete_node.jstree', function (e, data) {
                        $.ajax({
                            url: contextPath + "/organization/" + data.node.id,
                            type: 'DELETE',
                            error: function () {
                                data.instance.refresh();
                            },
                            success: function (json) {
                                json = JSON.parse(json);
                                if (json.result == 'success')
                                    data.instance.set_id(data.node, data.node.id);
                                else {
                                    data.instance.refresh();
                                }
                            }
                        });
                    })
                    .on('create_node.jstree', function (e, data) {
                        var parents = "";
                        for (var i = data.node.parents.length - 1; i >= 0; i--) {
                            if(data.node.parents[i] == '#'){
                                parents += '0/';
                            }else{
                                parents += data.node.parents[i] + '/';
                            }
                        }
                        $.ajax({
                            url: contextPath + "/organization/" + data.node.parent + "/appendChild",
                            type: 'post',
                            data: {
                                'parent_ids': parents,
                                'parent_id': data.node.parent,
                                'name': data.node.text,
                                'position' : data.position,
                                'available': 1
                            },
                            error: function () {
                                data.instance.refresh();
                            },
                            success: function (json) {
                                json = JSON.parse(json);
                                if (json.result == 'success') {
                                    data.instance.set_id(data.node, json.id);
                                }
                            }
                        });
                    })
                    .on('rename_node.jstree', function (e, data) {
                        var parents = "";
                        for (var i = data.node.parents.length - 1; i >= 0; i--) {
                            if(data.node.parents[i] == '#'){
                                parents += '0/';
                            }else{
                                parents += data.node.parents[i] + '/';
                            }
                        }
                        $.ajax({
                            url: contextPath + "/organization/" + data.node.id,
                            type: 'put',
                            data: {'id': data.node.id, 'name': data.node.text},
                            error: function () {
                                data.instance.refresh();
                            },
                            success: function (json) {
                                json = JSON.parse(json);
                                if (json.result != 'success')
                                    data.instance.set_text(data.node, data.node.text);
                                else
                                    data.instance.set_id(data.node, json.id);
                            }
                        });
                    })
                    .on('cut.jstree', function (e, data) {
                        console.info(11);
                        console.info(data);
                    })
                    .on('copy.jstree', function (e, data) {
                        console.info(22);
                        console.info(data);
                    })
                    .on('paste.jstree', function (e, data) {
                        console.info(33);
                        console.info(data);
                    })
                    .on('changed.jstree', function (e, data) {
                        // if (data.node) {
                        //     $("input[name='id']").val(data.node.id);
                        //     $("input[name='name']").val(data.node.text);
                        //     if (data.node.original.multiple == 1) {
                        //         $('#multiple1').attr("checked", 'checked');
                        //     } else {
                        //         $('#multiple2').attr("checked", 'checked');
                        //     }
                        // }
                    })
                    .on('move_node.jstree', function (e, data) {
                        console.info(data);
                        $.ajax({
                            url: contextPath + "/organization/" + data.node.id + '/move',
                            type: 'post',
                            data: {
                                'source_id': data.node.id,
                                'target_id': data.node.parent,
                                'position': data.position
                            },
                            error: function () {
                                data.instance.refresh();
                            },
                            success: function (json) {
                                json = JSON.parse(json);
                                if (json.result == 1) {
                                    data.instance.set_id(data.node, data.node.id);
                                }
                            }
                        });
                    });
            }
        });
    }

    return {
        //main function to initiate the module
        init: function (contextPath) {
            handleTree(contextPath);
        }

    };
}();