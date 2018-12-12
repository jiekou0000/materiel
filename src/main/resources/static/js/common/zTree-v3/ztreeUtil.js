function initZtreeData(tableId, setting, url,defaultId) {
    var index1;
    $.ajax({
        type: "POST",
        url: webPath + "/" + url,
        dataType: "json",
        beforeSend: function () {
            index1 = parent.layer.msg('请稍等,数据正在加载中...', {
                icon: 16,
                shade: [0.5]
            });
        },
        success: function (result) {
            if (result.status != 1004) {
                var zTree = $.fn.zTree.init($("#" + tableId), setting, result);
                var menuIds = $("#"+defaultId).val().split(',');
                for(var i = 0; i < menuIds.length; i++) {
                    var node = zTree.getNodeByParam("id", menuIds[i]);
                    if(node != null) {
                        zTree.checkNode(node, true)
                    }
                }
                parent.layer.close(index1);
            }
        },
        error: function () {
            msgErr('操作出现异常!');
        }
    });
}

function delZtreeConfirm(vids, url, treeid, nodes) {
    if (vids == "") {
        msgErr('操作出现异常!');
        return;
    }
    layer.confirm(DELETE_CONFIRM_TEXT, {
        icon: 3
    }, function (index) {
        $.ajax({
            type: "POST",
            url: webPath + "/" + url,
            data: {
                vids: vids
            },
            dataType: "json",
            success: function (result) {
                var status = result.status;
                var msg = result.msg;
                if (status == 1) {
                    removeNode(treeid, nodes);
                    layer.msg(msg, {
                        shadeClose: true, //开启遮罩关闭
                        icon: 1,
                        time: 1000 //1秒关闭（如果不配置，默认是3秒）
                    }, function (i) {
                    });
                } else {
                    msgErr(result.msg);
                }
            },
            error: function () {
                msgErr('操作出现异常!');
            }
        });
        layer.close(index);
    });
}


function removeNode(treeid, nodes) {
    var zTree = parent.$.fn.zTree.getZTreeObj(treeid);
    zTree.removeNode(nodes[0]);
}

/**
 * 获取选中的节点
 * @param treeid
 * @returns {*}
 */
function getSelectNode(treeid) {
    var zTree = parent.$.fn.zTree.getZTreeObj(treeid);
    var nodes = zTree.getSelectedNodes();
    return nodes;
}

/**
 * 刷新整个树
 * @param treeid
 */
function refreshRoot(treeid) {
    var zTree = parent.$.fn.zTree.getZTreeObj(treeid);
    zTree.reAsyncChildNodes(null, "refresh");
}

/**
 * 刷新当前节点
 */
function refreshNode(treeid) {
    /*根据 treeId 获取 zTree 对象*/
    var zTree = parent.$.fn.zTree.getZTreeObj(treeid),
        type = "refresh",
        silent = false,
    /*获取 zTree 当前被选中的节点数据集合*/
        nodes = zTree.getSelectedNodes();
    if (nodes == null || nodes == "") {
        zTree.reAsyncChildNodes(null, "refresh");
    } else {
        nodes[0].isParent = true;//把属性变成true，让这个节点被认为是根节点
        /*强行异步加载父节点的子节点。[setting.async.enable = true 时有效]*/
        zTree.reAsyncChildNodes(nodes[0], type, silent);
    }

}

/**
 * 刷新当前选择节点的父节点
 */
function refreshParentNode(treeid) {
    var zTree = $.fn.zTree.getZTreeObj(treeid),
        type = "refresh",
        silent = false,
        nodes = zTree.getSelectedNodes();
    /*根据 zTree 的唯一标识 tId 快速获取节点 JSON 数据对象*/
    var parentNode = zTree.getNodeByTId(nodes[0].parentTId);
    /*选中指定节点*/
    zTree.selectNode(parentNode);
    zTree.reAsyncChildNodes(parentNode, type, silent);
}


//tableTree监听表单提交
function formzTreeSubmit(tableId, btn_submit, form, url, method) {
    var index1;
    $("#" + btn_submit).click(function () {
        if ($('#' + form).form('validate')) {
            $.ajax({
                type: "POST",
                cache: false,//缓存
                url: webPath + "/" + url,
                data: $('#' + form).serialize(),//向后台发送的数据
                dataType: "json",
                beforeSend: function () {
                    index1 = parent.layer.load(1, {
                        shade: [0.5]
                    });
                },
                success: function (result) {
                    var status = result.status;
                    var msg = result.msg;
                    if (status == "1") {
                        parent.layer.msg(msg, {
                            shadeClose: true, //开启遮罩关闭
                            icon: 1,
                            time: 1000 //1秒关闭（如果不配置，默认是3秒）
                        }, function (i) {
                            parent.layer.close(i);
                            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                            parent.layer.close(index);
                            if ("update" == method) {
                                refreshRoot("treeGrid");
                            } else {
                                refreshNode("treeGrid");
                            }
                        });
                    } else {
                        msgErr(msg);
                    }
                    parent.layer.close(index1);
                },
                error: function () {
                    msgErr('操作出现异常!');
                }
            });
        } else {
            msgErr('请正确填写每一项!');
        }
    })
}