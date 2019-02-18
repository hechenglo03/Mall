
var usertype = "";


//顶栏信息抓取
$.get("/home/title",function (data) {
    //没有登录者
    if(data['code'] == 0){
        $(".nickname").append("<a href='/login'>登录</a>");
        $(".status").append("<li><a href='/home'>首页</a></li>")
        usertype = "visitor"//全局设置用户类型为游客
    }else if(data['code'] == 1){//买家登录
        $(".nickname").append("<span>"+data['object']['nickname']+"，你好</span>");
        $(".status").append("<li><a href=\"/user/loginout\">"+data["object"]["out"]+"</a></li>\n" +
            "                <li><a href=\"#\">"+data["object"]["fin"]+"</a></li>\n" +
            "                <li><a href=\"#\">"+data["object"]["car"]+"</a></li>");
        usertype = "user";//定义全局变量设置为买家
    }else{//卖者登录
        $(".nickname").append("<a href='#'>"+data['object']['nickname']+"</a>")
        $(".status").append("<li><a href=\"/seller/loginout\">"+data['object']['out']+"</a></li>\n" +
            "                <li><a href=\"#\">"+data['object']['publish']+"</a></li>")
        usertype = "seller";
        $(".selected").removeClass("adio")//若为卖者，关闭复选框
    }

    GetProduct(usertype,0,0);//刚刚开始启动获取数据
});

var allindex = 0;//全部商品索引
var notindex = 0;//未购买的商品索引
var flag = true;//表示全部商品
var Bought =  "<div class = \"icon\"><i class = \"fa  fa-2x fa-bell-o\"></i><span>已购买</span></div> ";

function GetProduct(usertype,bought,index) {
    $.get("/product/type/"+usertype,{"index":index,bought:bought},function (data) {
        if(data['object'].length <12){//当获取商品数量小于12，下翻页键取消功能
            $(".down").attr("disabled",true)
        }

        var imglist = data['object'];
        for(var index = 0 ; index < imglist.length;index++){
            var before = "<div class = \"col-md-3 text-center\">"+
                "<a class = \"width\" style='display: block' href='/product/index?id="+imglist[index]['id']+"'>";
            var after = "<img style=\"width:100px;height:150px\" src = "+imglist[index]['pic']+">"+
                "<p class = \"title\">"+imglist[index]['title']+"</p>"+
                "<p class = \"price\"><span>¥  </span>"+imglist[index]['price']+"</p>"+
                "</a>"+
                "</div>";
            var Sum = before;
            if( usertype == "user" && imglist[index]['sold'] < 0){
                Sum = Sum + bought;
            }
            Sum = Sum +after;
            $(".row").append(Sum);
        }
    },"json")
}

//往上翻页
$(".up").click(function () {
    var index ;
    if(flag){
        index = --allindex;
    }else{
        index = --notindex;
    }
    $(".width").remove();//本身带有元素节点
    GetProduct(usetype,bought,index);
    if(index == 0){
        $(".up").attr("disabled", true);
    }
    if($(".down").attr("disabled") == true){
        $(".down").attr("disabled",false);
    }
})

//往下翻页
$(".down").click(function () {
    var index ;
    if(flag){
        index = ++allindex;
    }else{
        index = ++notindex;
    }
    var bought = $('input[name="product"]:checked').val();
    $(".width").remove();
    GetProduct(usertype,bought,index);
    if($(".up").attr("disabled") == true){
        $(".up").attr("disabled",false)
    }
})

$(":radio").click(function () {
    //清空全部商品或者未购买商品的索引
    if(flag){
        allindex = 0;
    }else{
        notindex = 0;
    }
    flag = !flag;
    var bought = $('input[name="product"]:checked').val();
    $(".width").remove();
    GetProduct(usertype,bought,0);
})