// etcSure
function etcSure() {
  console.log("etcSure");
  $.ajax({
    type: "post",
    url: "/base-mate/light/etc-sure",
    dataType: "json",
    data: {
      loginName: "bill",
      password: "111111"
    },
    success: function (result) {
      msgOk(result.toString());
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