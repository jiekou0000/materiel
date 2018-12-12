layui.use(['layer'], function () {
    var layer = layui.layer;
});

function postRequest(body, url, callback) {
    $.ajax({
        type: "POST",
        cache: false,
        url:url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(body),
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
                    time:3000 //3秒关闭（如果不配置，默认是3秒）
                }, function (i) {
                    layer.close(i);
                });
                if (callback && typeof(callback) === "function") {
                    callback(result);
                }
            } else {
                if(result.rows!=='' && result.rows!=null){
                    parent.layer.alert(msg, {icon: 2},function(index){
                        window.location.href=result.rows;
                    });
                }else {
                    msgErr(msg);
                }
            }
        },
        error: function () {
            msgErr('操作出现异常!');
        }
    });
}