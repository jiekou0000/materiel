function bind(pageIndex) {
  var total = 0;
  var rows = 5;
  $.ajax({
    type: "post",
    url: "/user/order/record",
    async: false, // 作用是防止在ajax成功调用之前就调用$("#Pagination").pagination,这个时候数据个数还没有初始化
    dataType: "json",
    data: "&rows=" + rows + "&page=" + (pageIndex) + "&state=0",//传递页面索引
    // 发送请求前，显示加载动画
    beforeSend: function () {
      $("#divload").show();
      $("#datas #Pagination").hide()
    },
    // 请求完毕后，隐藏加载动画
    complete: function () {
      $("#divload").hide();
      $("#datas #Pagination").show()
    },
    success: function (result) {
      if (result.status == "SUCCESS") {
        var json = result.data.content; // json数据
        total = result.data.totalElements; // 记录总数
        var i = 0;
        var temp = '';
        if (total == 0) {
          $("#pagination").hide();
          temp += '\t<div class="record-center">\n' +
              '<p style="color: red">无待定订单记录</p>\n' +
              '</div>';
          $("#dataShow").html(temp); // 将创建的新行附加在DIV中
          return;
        }

        temp = '<div style="background: #F3F3F4">\n';
        $.each(json, function (index, item) {
          i++;
          temp += '<div class="record-center">\n' +
              '          <p>订单号：<span class="color0">' + item.id + '</span>' + '</p>\n' +
              '                    <table border="0" cellpadding="0" cellspacing="0" class="record-table">\n' +
              '                        <tr>\n' +
              '                            <th>所需物料：</th>\n' +
              '                            <td>' + formatMateType(item.type) + '</td>\n' +
              '                            <th>所需数量：</th>\n' +
              '                            <td >' + item.num + '</td>\n' +
              '                        </tr>\n' +
              '                        <tr>\n' +
              '                            <th>使用时间：</th>\n' +
              '                            <td>' + item.startTime + '</td>\n' +
              '                            <th>使用天数：</th>\n' +
              '                            <td>' + item.day + '</td>\n' +
              '                        </tr>\n' +
              '                        <tr>\n' +
              '                            <th>下单时间：</th>\n' +
              '                            <td>' + formatDateTime(item.createTime) + '</td>\n' +
              '                            <th>备注：</th>\n' +
              '                            <td>' + item.remark + '</td>\n' +
              '                        </tr>\n' +
              '                    </table>\n' +
              '                    <div class="detail-group">\n' +
              '                        <span><a onclick="sureOrder(' + item.id + ')">改为确定</a></span>' +
              '                        <span><a onclick="cancelOrder(' + item.id + ')">撤销订单</a></span>' +
              '                    </div>' +
              '     </div>';
        });
        temp += '</div>';
        $("#dataShow").html(temp); // 将创建的新行附加在DIV中
      } else {
        msgErr(result.error);
      }
    },
    error: function (res) {
      if (res.status == 401) {
        window.location.href = "/tpl/login?uri=/user/order/pend";
      } else {
        msgErr('请求异常,请联系管理员!');
      }
    }
  });

  if (total != 0) {
    //调用分页函数，将分页插件绑定到id为Pagination的div上
    $("#pagination").pagination(total, { //recordCount在后台定义的一个公有变量，通过从数据库查询记录进行赋值，返回记录的总数
      callback: pageSelectCallback, //点击分页时，调用的回调函数
      prev_text: '« 上一页', //显示上一页按钮的文本
      next_text: '下一页 »', //显示下一页按钮的文本
      first_text: "首页",
      last_text: "尾页",
      items_per_page: rows, //每页显示的项数
      num_display_entries: 4, //分页插件中间显示的按钮数目
      current_page: pageIndex, //当前页索引
      num_edge_entries: 4 //分页插件左右两边显示的按钮数目
    });
  }
}

// 改为确定
function sureOrder(orderId) {
  $.ajax({
    type: "post",
    url: "/user/order/change-sate",
    dataType: "json",
    data: "&orderId=" + orderId + "&state=1",
    success: function (result) {
      if (result.status == "SUCCESS") {
        location.reload();
      } else {
        msgErr(result.error);
      }
    },
    error: function (res) {
      if (res.status == 401) {
        window.location.href = "/tpl/login?uri=/user/order/pend";
      } else {
        msgErr('请求异常,请联系管理员!');
      }
    }
  });
}

// 撤销订单
function cancelOrder(orderId) {
  $.ajax({
    type: "post",
    url: "/user/order/change-sate",
    dataType: "json",
    data: "&orderId=" + orderId + "&state=2",
    success: function (result) {
      if (result.status == "SUCCESS") {
        location.reload();
      } else {
        msgErr(result.error);
      }
    },
    error: function (res) {
      if (res.status == 401) {
        window.location.href = "/tpl/login?uri=/user/order/pend";
      } else {
        msgErr('请求异常,请联系管理员!');
      }
    }
  });
}


/**
 * 点击分页时调用的函数，page_id为当前页的索引
 * @param  page_id
 * @param  jq
 */
function pageSelectCallback(page_id, jq) {
  bind(page_id);
}