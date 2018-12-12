/**
 * @param title 显示标题
 * @param width 窗口宽度
 * @param height 窗口高度
 * @param url 要打开的url
 * @param scroll 是否出现滚动条
 */
function layerOpen(title, width, height, url, scroll) {
    var index = layer.open({
        type: 2,
        title: title,
        resize: false,
        maxmin: true,
        area: [width, height],
        skin: 'layui-layer-rim', //加上边框
        content: [webPath + "/" + url, scroll],
        success: function (layero, index) {
            layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                tips: 3,
                time: 3000, //5秒后自动关闭
            });
        }
    });
    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
    $(window).resize(function () {
        layui.layer.full(index);
    })
}

//删除弹窗询问提示
function delConfirm(vids, url, tableId, confirmtext, resulttext, myid) {
    if (vids == "") {
        msgErr('操作出现异常!');
        return;
    }
    layer.confirm(confirmtext, {
        icon: 3
    }, function (index) {
        $.ajax({
            type: "POST",
            url: webPath + "/" + url,
            data: {
                ids: vids
            },
            dataType: "json",
            success: function (result) {
                var code = result.errorCode;
                var msg = result.message;
                if (code == "0") {
                    //删除行beg
                    var idsArr = vids.split(",");
                    var nvids = [];
                    for (var i = 0; i < idsArr.length; i++) {
                        nvids.push(idsArr[i])
                    }
                    $('#' + tableId).bootstrapTable('remove', {
                        field: myid,
                        values: nvids
                    });
                    $("#" + tableId).bootstrapTable('refresh');
                    //删除行end
                    msgOk(resulttext);
                    // layer.alert('删除成功!',{icon:1});
                } else {
                    msgErr(msg);
                }
            },
            error: function () {
                msgErr('操作出现异常!');
            }
        });
        layer.close(index);
    });
}

/**
 * 弹窗询问提示(不删除该条记录)
 * @param param
 * @param url
 * @param confirmText
 * @param resultText
 * @param tableId
 */
function askConfirm(param, url, confirmText, resultText,tableId) {
    if (param == "") {
        msgErr('操作出现异常!');
        return;
    }
    layer.confirm(confirmText, {
        icon: 3
    }, function (index) {
        $.ajax({
            type: "POST",
            url: webPath + "/" + url,
            data: {
                param: param
            },
            dataType: "json",
            success: function (result) {
                var code = result.errorCode;
                var msg = result.message;
                if (code == "0") {
                    msgOk(resultText);
                    $("#" + tableId).bootstrapTable('refresh');
                } else {
                    msgErr(msg);
                }
            },
            error: function () {
                msgErr('操作出现异常!');
            }
        });
        layer.close(index);
    });
}

//监听表单提交（弹窗表单，报错后可关闭响应弹窗,并且刷新列表）
function formSubmit(tableId, btn_submit, form, url) {
    var index1;
    $("#" + btn_submit).click(function () {
        $("#" + form).validate({
            submitHandler: function () {
                $.ajax({
                    type: "POST",
                    cache: false,//缓存
                    url: webPath + "/" + url,
                    data: $('#' + form).serialize(),//向后台发送的数据
                    dataType: "json",
                    beforeSend: function () {
                        index1 = parent.layer.msg('请稍等,正在提交中...', {
                            icon: 16,
                            shade: [0.5]
                        });
                    },
                    success: function (result) {
                        var code = result.errorCode;
                        var msg = result.message;
                        if (code == "0") {
                            parent.layer.msg(msg, {
                                shadeClose: true, //开启遮罩关闭
                                icon: 1,
                                time: 1000 //1秒关闭（如果不配置，默认是3秒）
                            }, function (i) {
                                parent.layer.close(i);
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
                                parent.$("#" + tableId).bootstrapTable('refresh');
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
            }

        })
    })
}


//监听表单提交（弹窗表单，报错后可关闭响应弹窗）
function formSubmitNoRefresh(btn_submit, form, url) {
    var index1;
    $("#" + btn_submit).click(function () {
        $("#" + form).validate({
            submitHandler: function () {
                $.ajax({
                    type: "POST",
                    cache: false,//缓存
                    url: webPath + "/" + url,
                    data: $('#' + form).serialize(),//向后台发送的数据
                    dataType: "json",
                    beforeSend: function () {
                        index1 = parent.layer.msg('请稍等,正在提交中...', {
                            icon: 16,
                            shade: [0.5]
                        });
                    },
                    success: function (result) {
                        var code = result.errorCode;
                        var msg = result.message;
                        if (code == "0") {
                            parent.layer.msg(msg, {
                                shadeClose: true, //开启遮罩关闭
                                icon: 1,
                                time: 1000 //1秒关闭（如果不配置，默认是3秒）
                            }, function (i) {
                                parent.layer.close(i);
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
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
            }

        })
    })
}

//监听表单提交（单页面监听）
function singleFormSubmit(btn_submit, form, url) {
    $("#" + btn_submit).click(function () {
        $("#" + form).validate({
            submitHandler: function () {
                $.ajax({
                    type: "POST",
                    cache: false,//缓存
                    url: webPath + "/" + url,
                    data: $('#' + form).serialize(),//向后台发送的数据
                    dataType: "json",
                    beforeSend: function () {
                        index1 = layer.msg('请稍等,正在提交中...', {
                            icon: 16,
                            shade: [0.5]
                        });
                    },
                    success: function (result) {
                        var code = result.errorCode;
                        var msg = result.message;
                        if (code == "0") {
                            layer.msg(msg, {
                                shadeClose: true, //开启遮罩关闭
                                icon: 1,
                                time: 1000 //1秒关闭（如果不配置，默认是3秒）
                            }, function (i) {
                                layer.close(i);
                            });
                        } else {
                            msgErr(msg);
                        }
                    },
                    error: function () {
                        msgErr('操作出现异常!');
                    }
                });
            }

        })
    })
}


//监听表单提交（单页面监听）
function requestPost(body, url, callback) {
    $.ajax({
        type: "POST",
        cache: false,//缓存
        url: webPath + "/" + url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(body),//向后台发送的数据
        dataType: "json",
        beforeSend: function () {
            index1 = layer.msg('请稍等,正在提交中...', {
                icon: 16,
                shade: [0.5]
            });
        },
        success: function (result) {
            var code = result.errorCode;
            var msg = result.message;
            if (code === 0) {
                layer.msg(msg, {
                    shadeClose: true, //开启遮罩关闭
                    icon: 1,
                    time: 1000 //1秒关闭（如果不配置，默认是3秒）
                }, function (i) {
                    layer.close(i);
                });
                if (callback && typeof(callback) === "function") {
                    callback(result);
                }
            } else {
                msgErr(msg);
            }
        },
        error: function () {
            msgErr('操作出现异常!');
        }
    });
}


function formToJson(data) {
    data = decodeURIComponent(data);
    data = data.replace(/&/g, "','");
    data = data.replace(/=/g, "':'");
    data = "({'" + data + "'})";
    return eval(data);
}

/**
 * 打开新的table页
 * @param titleParam table页标题
 * @param uriParam 要打开的table页uri
 * @param idParam 标识table页
 */
function openNewTable(titleParam, uriParam, idParam) {
    layui.use(['element'], function () {
        var id = idParam;
        var uri = uriParam;

        var $ = layui.jquery;
        for (var i = 0; i < $('.x-iframe', window.parent.document).length; i++) {
            if ($('.x-iframe', window.parent.document).eq(i).attr('tab-id') == id) {
                parent.element.tabChange('xbs_tab', id);
                $('.x-iframe', window.parent.document).eq(i).attr('src', uriParam);
                return;
            }
        }

        parent.element.tabAdd('xbs_tab', {
            title: titleParam,
            content: '<iframe tab-id="' + id + '" frameborder="0" src="' + uri + '" scrolling="yes" class="x-iframe"></iframe>',
            id: id
        });
        parent.element.tabChange('xbs_tab', id);
    });
}