(function ($) {
    $.fn.sidebarMenu = function (vstyle,options) {
        options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
        var target = $(this);
        if (options.data) {
            init(vstyle,target, options.data);
        }
        else {
            if (!options.url) return;
            $.getJSON(options.url, options.param, function (data) {
                init(vstyle,target, data);
            });
        }
       var url = window.location.href;
       var menu = target.find("[href='" + url + "']");
        menu.parent().addClass('active');
        menu.parent().parentsUntil('.nav-list', 'li').addClass('active').addClass('open');
        function init(vstyle,target, data) {
            $.each(data, function (i, item) {
                var li = $('<li></li>');
                    var a = $('<a></a>');
                    var iconCls = item.iconCls;
                    var icon = $("<i class='iconfont'>&#xe723;</i>");
                    if(iconCls != '' && iconCls !=null){
                        icon = $('<i class="iconfont">'+iconCls+'</i>');
                    }
                    var text = $('<cite id='+item.id+'></cite>');
                    text.text(item.text);
                    a.append(icon);
                    a.append(text);
                if (item.children&&item.children.length>0) {
                        a.attr('href', 'javascript:;');
                        var arrow = $('<i>&#xe697;</i>');
                        arrow.addClass('iconfont').addClass('nav_right');
                        a.append(arrow);
                        li.append(a);
                        var menus = $('<ul></ul>');
                        menus.addClass('sub-menu');
                        init(vstyle,menus, item.children);
                        li.append(menus);
                    } else {
                        var newlink = item.url;
                        a.attr('_href', webPath+"/"+newlink);
                        li.append(a);
                    }
                    target.append(li);
                    //菜单结束
               /* }*/
            });
        }
    };

    $.fn.sidebarMenu.defaults = {
        url: null,
        param: null,
        data: null
    };
})(jQuery);