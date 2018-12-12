// 按月配资
var month = JSON.parse(monthData);
var gang_gan_arr = [];
for (var i = 1; i <= maxLevelRate; i++) {
    if (parseFloat(month['jjx' + i]) > 0 || parseFloat(month['zsx' + i]) > 0) {
        gang_gan_arr.push(i);
    }
}
$("#m-min-bzj").text(month.bzjMin);
$("#m-max-ganggan").text(gang_gan_arr[gang_gan_arr.length - 1]);
$("#m-min-yx").text(month['yx' + gang_gan_arr[0]]);
$("#m-min-start").text(month.start);

// 按天配资
var day = JSON.parse(dayData);
for (var i = 1; i <= maxLevelRate; i++) {
    if (parseFloat(day['jjx' + i]) > 0 || parseFloat(day['zsx' + i]) > 0) {
        gang_gan_arr.push(i);
    }
}
$("#d-min-bzj").text(day.bzjMin);
$("#d-max-ganggan").text(gang_gan_arr[gang_gan_arr.length - 1]);
$("#d-min-yx").text(day['glf' + gang_gan_arr[0]]);
$("#d-min-start").text(day.start);

// 免息配资
var mx = JSON.parse(mxData);
if (mx.open !== 'yes') {
    $("#mx-li").hide();
} else {
    for (var i = 1; i <= maxLevelRate; i++) {
        if (parseFloat(mx['jjx' + i]) > 0 || parseFloat(mx['zsx' + i]) > 0) {
            gang_gan_arr.push(i);
        }
    }
    $("#mx-min-bzj").text(mx.bzjMin);
    $("#mx-max-ganggan").text(gang_gan_arr[gang_gan_arr.length - 1]);
    $("#mx-pzts").text(mx.pzts);
}

// 免费体验
var free = JSON.parse(freeData);
if (free.open !== 'yes') {
    $("#free-li").hide();
} else {
    $("#free-money").text(free.money);
    $("#free-bzj").text(free.deposit);
    $("#free-tyts").text(free.duration);
    $("#free-money-all").text(free.money);
}

// 轮播
var swiper = new Swiper('.swiper-container', {
    pagination: '.swiper-pagination',
    paginationClickable: true,
    spaceBetween: 0,
    autoplay: 2500
});

$(function () {
    // 公告滚动
    $('#demo1').scrollbox();

    // 配资隐藏显示
    $('#pz-type li').click(function () {
        $('.fund-center .fund-img').hide().eq($('#pz-type li').index(this)).show();
    })

});

function peizi() {
    var pzUrl = '';
    var index = $('#pz-type li[class=active]').index();
    switch (index) {
        case 0:
            pzUrl = '/wap/allocation/month.html';
            break;
        case 1:
            pzUrl = '/wap/allocation/day.html';
            break;
        case 2:
            pzUrl = '/wap/allocation/mx.html';
            break;
        case 3:
            pzUrl = '/wap/allocation/free.html';
            break;
    }
    window.location.href = pzUrl;
}