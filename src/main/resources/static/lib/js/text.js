//(function (doc, win) {
//  var docEl = doc.documentElement,
//      resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize', recalc = function () {
//  var clientWidth = docEl.clientWidth;
//      if (!clientWidth) return;
//          clientWidth = (clientWidth > 768) ? 768 : clientWidth; docEl.style.fontSize = 100 * (clientWidth / 375) + 'px';
//      };
//      if (!doc.addEventListener) return; win.addEventListener(resizeEvt, recalc, false);
//  recalc();
//})(document, window);

//点击返回
$(function(){
	$('.header-wrap a.left').click(function(){
		window.history.back();
	})
});
//首页
$(function(){
    $('.four-funding .fund-list li').click(function(){
        $(this).addClass('active').siblings().removeClass('active');
    })
});
//推广
$(function(){
	$('.yaoqbtn').click(function(){
		$('.dialog-wrap').addClass('trade0');
	});
    $('.share-center').click(function(){
		$('.dialog-wrap').removeClass('trade0');
	});
});
//我的推广
$(function(){
    $('.popularize-list li').click(function(){
    	$(this).addClass('active').siblings().removeClass('active');
    	$('.mypoplurize-center').hide().eq($(this).index()).show();
    })
});
//盈利提取
$ (function (){
	$ ('.layui-btn-primary').click (function (){
	    $ ('.tips').fadeIn().delay(3000).fadeOut();
	});
}); 
//方案详情
$(function(){
	$('.program-list li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.program-container').hide().eq($(this).index()).show();
	})
});
//关于我们
$(function(){
	$('.aboutUs-conatiner .about-list li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.aboutus-wrap').hide().eq($(this).index()).show();
	})
});







