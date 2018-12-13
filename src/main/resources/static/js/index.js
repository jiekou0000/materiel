// 轮播
var swiper = new Swiper('.swiper-container', {
    pagination: '.swiper-pagination',
    paginationClickable: true,
    spaceBetween: 0,
    autoplay: 2500
});

$(function () {
    // 隐藏显示
    $('#pz-type li').click(function () {
        $('.fund-center .fund-img').hide().eq($('#pz-type li').index(this)).show();
    })

});