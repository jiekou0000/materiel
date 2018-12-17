// etcSure
function etcSure() {
  var num = $("#etcNum").val();
  var startTime = $("#etcStartTime").val();
  var day = $("#etcDay").val();
  if (num == "") {
    msgErr('所需数量不可为空!');
    return;
  }
  if (startTime == "") {
    msgErr('开始使用时间不可为空!');
    return;
  }
  if (day == "") {
    msgErr('预计使用天数不可为空!');
    return;
  }

  $.ajax({
    type: "post",
    url: "/base-mate/light/etc-order",
    dataType: "json",
    data: {
      type: 0,
      num: num,
      startTime: startTime,
      day: day,
      state: 1
    },
    success: function (result) {
      if (result.status == "SUCCESS") {
        msgOk("下单成功，可在个人订单记录中查询")
      } else {
        msgErr(result.error);
      }
    },
    error: function (res) {
      if (res.status == 401) {
        window.location.href = "/tpl/login?uri=/base-mate/light/etc-sure";
      } else {
        msgErr('请求异常,请联系管理员!');
      }
    }
  });
}

// etcUndeterm
function etcUndeterm() {
  console.log("etcUndeterm")
}


// downSure
function downSure() {
  console.log("downSure")
}

// downUndeterm
function downUndeterm() {
  console.log("downUndeterm")
}