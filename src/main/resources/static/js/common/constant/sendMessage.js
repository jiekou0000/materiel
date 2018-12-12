/*通知消息*/
function queryMessageByMemberId() {
    $.ajax({
        type: "post",
        url: webPath + "/queryAllMessage",
        async: false, //作用是防止在ajax成功调用之前就调用$("#Pagination").pagination,这个时候数据个数还没有初始化
        dataType: "json",
        data: {
            sort: 'createTime',
            order: 'desc'
        },
        success: function (result) {
            var json = result.rows;//json数据
            var i = 0;
            var id = "";
            var title = "";
            var createTime = "";
            var content = "";
            var loginName = "";
            $.each(json, function (index, item) {
                i++;
                id = item.id;
                title = item.title;
                createTime = formatDateTime(item.createTime);
                loginName = item.loginName;
                content = content + '<tr id=' + id + '>' +
                    '                    <td width="10%">' +
                    '                        <a class="row_btn_blue">已读</a>' +
                    '                    </td>' +
                    '                    <td width="15%">' + createTime + '</td>' +
                    '                    <td width="15%">' + loginName + '</td>' +
                    '                    <td width="60%">' + title + '</td>' +
                    '                </tr>';
            })
            $("#dataShow").html(content);
        }
    });
}

//判断是否为数组且至少有一个元素;
function isArray(arr) {
    if (!$.isArray(arr)) {
        return false;
    } else if (arr.length < 1) {
        return false;
    } else {
        return true;
    }
}

$(document).ready(function () {
    queryMessageByMemberId();
    setInterval(function () {
        queryMessageByMemberId();
    }, 5000);
});

//更新已读
$(function () {
    $(document).on("click", "#dataShow tbody tr td a", function () {
        var tr = $(this).parent('td').parent('tr');
        var messageId = tr.attr("id");
        $.ajax({
            type: "post",
            url: webPath + "/updateMessageManagement",
            async: false,
            dataType: "json",
            data: {
                id: messageId
            },
            success: function (result) {
                var errorCode = result.errorCode;
                if (errorCode != 0) {
                    msgErr("网络错误,请刷新页面重试!");
                } else {
                    tr.remove();
                    if ($("#dataShow tbody tr").length < 1) {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    }
                }
            },
            error:
                function (res) {
                    msgErr("操作失败");
                }
        });
    })
})
;

//全部更新已读
$(function () {
    $(document).on("click", "#allRead", function () {
        var ids = "";
        $('#dataShow tbody tr').each(function (i) {
            ids = ids + $(this).attr("id") + ",";
        });
        ids = ids.substr(0, ids.length - 1);
        ids = ids + "";
        $.ajax({
            type: "post",
            url: webPath + "/updateAllMessage",
            async: false,
            traditional: true,
            dataType: "json",
            data: {
                ids: ids
            },
            success: function (result) {
                var errorCode = result.errorCode;
                if (errorCode != 0) {
                    msgErr("网络错误,请刷新页面重试!");
                } else {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }
            },
            error: function (res) {
                msgErr("操作失败");
            }
        });
    })
});
