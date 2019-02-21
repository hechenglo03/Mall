var product;

Title();//获取头部信息



$.get("/product/index",function (data) {
    if(data['code'] == 0){
        alert("没有该种商品");
        history.go(-1);
    }
    product = data;
},"json");


$.ready(function () {
    var data = product;
    if(data[code] == 1){
        $("#title").attr("value",data['object']['title']);
        $("#summary").attr("value",data['object']['summary']);
        $("#maincontent").attr("value",data['object']['maincontent']);
        $("#price").attr("value",data['object']['price']);
        if(data['object']['picurl'] !=""){
            $("#imgurl").attr("src",data['object']['picurl']);
        }else{
            $("#imgurl").attr("src",data['object']['picdir']);
        }
    }
})