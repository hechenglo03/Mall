var usertype = "";

function Title() {

    $.ajaxSettings.async = false;//设置该请求为同步请求
//顶栏信息抓取
    $.get("/home/title", function (data) {
        //没有登录者
        if (data['code'] == 0) {
            $(".nickname").append("<a href='/login'>登录</a>");
            $(".status").append("<li><a href='/home'>首页</a></li>")
            usertype = "visitor"//全局设置用户类型为游客
        } else if (data['code'] == 1) {//买家登录
            $(".nickname").append("<a href='/home'>" + data['object']['nickname'] + "，你好</a>");
            $(".status").append("<li><a href=\"/user/loginout\">" + data["object"]["out"] + "</a></li>\n" +
                "                <li><a href=\"/balance/bill\">" + data["object"]["fin"] + "</a></li>\n" +
                "                <li><a href=\"/balance/car\">" + data["object"]["car"] + "</a></li>");
            usertype = "buyer";//定义全局变量设置为买家
            $(".selected").removeClass("adio")//若为买家，关闭复选框
        } else {//卖者登录
            $(".nickname").append("<a href='/home'>" + data['object']['nickname'] + "</a>")
            $(".status").append("<li><a href=\"/seller/loginout\">" + data['object']['out'] + "</a></li>\n" +
                "                <li><a href='/editor'>" + data['object']['publish'] + "</a></li>");
            usertype = "seller";
        }
    })

    $.ajaxSettings.async = true;

};