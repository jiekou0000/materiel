// etcSure
function etcSure() {
  console.log("etcSure");
  $.ajax({
    type: "post",
    url: "/sys-user/etc-sure",
    dataType: "json",
    data: {
    },
    success: function (result) {
      msgOk(result.toString());
    },
    error: function () {
      window.location.href = "/login";
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