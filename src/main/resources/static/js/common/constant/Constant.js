var webPath = "http://" + window.location.host + "/admin";//系统地址
var indexPath = "http://" + window.location.host;//前台系统地址
//错误屏蔽
window.onerror = SlyarErrors;

function SlyarErrors() {
    // return true;
}

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

function  roundUp(newValue) {
    return (Math.round(parseFloat(newValue) * 100)/100).toFixed(2);
}


/**
 * 跳转至"某个页面"页面
 */
function toJumpPage(url,title,code) {
    var uri = webPath + url;
    openNewTable(title, uri, code);
}

/**
 * 获取实时时间
 */
function showTime() {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth();
    var date = now.getDate();
    var hour = now.getHours();
    var minutes = now.getMinutes();
    var second = now.getSeconds();
    month < 10 ? month = '0' + month : month;
    month = month + 1;
    hour < 10 ? hour = '0' + hour : hour;
    minutes < 10 ? minutes = '0' + minutes : minutes;
    second < 10 ? second = '0' + second : second;
    var now_time = year + '年' + month + '月' + date + '日' + ' ' + hour + ':' + minutes + ':' + '' + second;
    document.getElementById('showtime').innerHTML = now_time;
}

//时间转成 yyyy-mm-dd HH:mm:ss 格式
function formatDateTime(inputTime) {
    if (inputTime == null || inputTime == '') {
        return '';
    }
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
}

//时间转成 yyyy-mm-dd 格式
function formatDate(inputTime) {
    if (inputTime == null || inputTime == '') {
        return '';
    }
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
}

//获取bootstrap table选中行数据
function getSelectValue(tableId) {
    var idsArr = $.map($("#" + tableId).bootstrapTable('getSelections'), function (row) {
        return row.id;
    });
    return idsArr;
}

// 物料类型格式化
function formatMateType(value, row) {
    if (value == 1001) {
        return "<span>帕灯</span>";
    } else if (value == 1) {
        return "<span style=color:green;>系统充值</span>";
    } else if (value == 2) {
        return "<span style=color:green;>系统赠送</span>";
    } else if (value == 3) {
        return "<span style=color:red;>提现</span>";
    } else if (value == 4) {
        return "<span style=color:red;>系统提现</span>";
    } else if (value == 5) {
        return "<span style=color:red;>系统赠送提现</span>";
    } else if (value == 6) {
        return "<span style=color:red;>冻结保证金</span>";
    } else if (value == 7) {
        return "<span style=color:green;>解冻保证金</span>";
    } else if (value == 8) {
        return "<span style=color:red;>支付管理费</span>";
    } else if (value == 9) {
        return "<span style=color:red;>追加实盘金</span>";
    } else if (value == 10) {
        return "<span style=color:red;>补亏损</span>";
    } else if (value == 11) {
        return "<span style=color:green;>盈利提取</span>";
    } else if (value == 12) {
        return "<span style=color:green;>配资结算</span>";
    } else if (value == 13) {
        return "<span style=color:green;>充值赠送管理费</span>";
    } else if (value == 14) {
        return "<span style=color:green;>注册赠送管理费</span>";
    } else if (value == 15) {
        return "<span style=color:green;>实名认证赠送管理费</span>";
    } else if (value == 16) {
        return "<span style=color:green;>绑定银行卡赠送管理费</span>";
    } else if (value == 17) {
        return "<span style=color:green;>首次充值赠送</span>";
    } else if (value == 18) {
        return "<span style=color:green;>首次配资赠送</span>";
    } else if (value == 19) {
        return "<span style=color:green;>首次追加赠送</span>";
    } else if (value == 20) {
        return "<span style=color:green;>首次补亏赠送</span>";
    } else if (value == 21) {
        return "<span style=color:green;>首次提盈赠送</span>";
    } else if (value == 22) {
        return "<span style=color:green;>推广佣金</span>";
    }else if (value == 23) {
        return "<span style=color:green;>系统余额加款</span>";
    }else if (value == 24) {
        return "<span style=color:red;>系统余额扣款</span>";
    }else if (value == 25) {
        return "<span style=color:green;>系统赠送加款</span>";
    }else if (value == 26) {
        return "<span style=color:red;>系统赠送扣款</span>";
    }
}

