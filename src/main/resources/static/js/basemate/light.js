// etcSure
function etcSure() {
  var etcNum = $("#etcNum").val();
  var etcStartTime = $("#etcStartTime").val();
  var etcDay = $("#etcDay").val();
  var etcRemark = $("#etcRemark").val();
  if (etcNum == "") {
    msgErr('所需数量不可为空!');
    return;
  }
  if (etcStartTime == "") {
    msgErr('开始使用时间不可为空!');
    return;
  }
  if (etcDay == "") {
    msgErr('预计使用天数不可为空!');
    return;
  }

  $.ajax({
    type: "post",
    url: "/base-mate/light/order",
    dataType: "json",
    data: {
      category: 10,
      type: 1001,
      num: etcNum,
      startTime: etcStartTime,
      day: etcDay,
      remark: etcRemark,
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
        window.location.href = "/tpl/login?uri=/base-mate/light";
      } else {
        msgErr('请求异常,请联系管理员!');
      }
    }
  });
}

// etcPend
function etcPend() {
  console.log("etcPend")
}


// downSure
function downSure() {
  console.log("downSure")
}

// downPend
function downPend() {
  console.log("downPend")
}