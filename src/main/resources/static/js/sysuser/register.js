$(document).ready(function () {
  // 注册表单提交开始
  $("#btn_register").click(function () {
    var loginName = $('#loginName').val();
    var phoneNum = $('#phoneNum').val();
    var password = $('#password').val();
    var confirmPwd = $('#confirmPwd').val();
    if (loginName == "" || loginName == null) {
      msgErr('登录名不能为空!');
      return;
    }
    if (phoneNum == "" || phoneNum == null) {
      msgErr('手机号不能为空!');
      return;
    }
    if (password == "" || password == null) {
      msgErr('密码不能为空!');
      return;
    }
    if (confirmPwd == "" || confirmPwd == null) {
      msgErr('确认密码不能为空!');
      return;
    }
    if (confirmPwd !== password) {
      msgErr('两次密码输入不一致!');
      return;
    }

    $.ajax({
      type: "post",
      url: "/do-register",
      dataType: "json",
      data: {
        loginName: loginName,
        phoneNum: phoneNum,
        password: password
      },
      success: function (result) {
        if (result.status == "SUCCESS") {
          loadingSuccessLocation("注册成功", "/tpl/login");
        } else {
          msgErr(result.error);
        }
      },
      error: function () {
        msgErr('请求异常,请联系管理员!');
      }
    });
  });
  //注册表单提交结束
});