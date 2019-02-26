
Title();//状态栏的抓取

GetProduct(usertype,0,0);//商品信息第一页抓取

var allindex = 0;//全部商品索引
var notindex = 0;//未购买的商品索引
var flag = true;//表示全部商品
var Bought =  "<div class = \"icon\"><i class = \"fa  fa-2x fa-bell-o\"></i><span>已购买</span></div> ";
var Sold =  "<div class = \"icon\"><i class = \"fa  fa-2x fa-buysellads\"></i><span>售出";
var Delete = "<button class = 'btn btn-primary btn-sm delete' data-id =";

function GetProduct(usertype,bought,index) {
    $.get("/product/type/"+usertype,{"index":index,bought:bought},function (data) {
        if(data['object'].length <12){//当获取商品数量小于12，下翻页键取消功能
            $(".down").attr("disabled",true)
        }

        var imglist = data['object'];
        for(var i = 0 ; i < imglist.length;i++){
            var before = "<div class = \"col-md-3 text-center cell\">"+
                "<a class = \"width\" style='display: block' href='/product/index?id="+imglist[i]['id']+"'>";
            var after = "<img style=\"width:100px;height:150px\" src = "+imglist[i]['pic']+">"+
                "<p class = \"title\">"+imglist[i]['title']+"</p>"+
                "<p class = \"price\"><span>¥  </span>"+imglist[i]['price']+"</p>"+
                "</a>"+
                "</div>";
            var Sum = before;
            if( usertype == "user" && imglist[i]['sold'] < 0){
                Sum = Sum + Bought;//用户为买者，已购买的商品需要添加上已购买的标志
            }

            if(usertype == "seller" && imglist[i]['sold'] <0){
                Sum = Sum + Sold+imglist[i]['sold']*-1+"</span></div>";//用户为卖者，已购买的商品需要显示已购买的数量
            }
            if(usertype == "seller" && imglist[i]['sold'] >= 0){
                Sum = Sum + Delete+imglist[i]['id']+ ">删除</button>";//用户为卖者，为没有购买的商品添加删除按钮
            }

            Sum = Sum +after;
            $(".row").append(Sum);
        }
        $(".index").html("第"+(index+1)+"页");
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
    var bought = $('input[name="product"]:checked').val();
    $(".cell").remove();//删除以前的表格
    GetProduct(usertype,bought,index);

    if(index == 0){
        $(".up").attr("disabled","disabled");//索引为0，禁用上翻键
    }
    if($(".down").attr("disabled") == "disabled"){
        $(".down").removeAttr("disabled");
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
    $(".cell").remove();
    GetProduct(usertype,bought,index);
    if($(".up").attr("disabled") == "disabled"){
        $(".up").removeAttr("disabled");
    }
})

//购买和未购买的复选框
$(":radio").click(function () {
    //清空全部商品或者未购买商品的索引
    if(flag){
        allindex = 0;
    }else{
        notindex = 0;
    }
    flag = !flag;
    var bought = $('input[name="product"]:checked').val();
    $(".cell").remove();//整个图片移除
    GetProduct(usertype,bought,0);
})

//商品的删除按钮事件,使用deletegate来防止js动态加载注册不上去事件
$('body').delegate(".delete","click",function (e) {
    e.stopPropagation();
    e.preventDefault();
    var id =$(this).attr("data-id");
    var father = $(e.target);
    $.confirm({
        title:"商品删除确定",
        content:"是否要删除商品",
        buttons:{
            "确定":function () {
                $.get("/product/delete",{id:id},function (data) {
                    if(data.code == 1){
                        location.reload();
                    }
                })
            },
            "没有决定":function () {

            }
        }


    })

})