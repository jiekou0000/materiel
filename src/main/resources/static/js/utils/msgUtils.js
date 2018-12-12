/**
 * loading成功后跳转
 * @param msg
 * @param url
 */
function loadingSuccessLocation(msg,url){
    layer.msg(msg, {
        icon: 1,//提示的样式
        time: 2000, //2秒关闭（如果不配置，默认是3秒）//设置后不需要自己写定时关闭了，单位是毫秒
        end:function(){
            location.href=url;
        }
    });
}


//错误弹窗提示
function msgErr(msg){
    parent.layer.alert(msg, {icon: 2});
}

//正确提示
function msgOk(msg){
    parent.layer.msg(msg, {time: 2000, icon:6});
}
