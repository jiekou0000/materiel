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