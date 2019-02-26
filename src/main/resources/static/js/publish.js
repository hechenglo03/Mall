
Title();//获取头部信息

//获取商品的相关信息
$.get("/editor/product",function (data) {
    if(data['code'] == 1){
        $("#title").attr("value",data['object']['title']);
        $("#summary").attr("value",data['object']['summary']);
        $("#maincontent").html(data['object']['content']);
        $("#price").attr("value",data['object']['price']);
        $("#imgurl").attr("src",data['object']['pic']);
        id = data['object']['id'];
    }
})


$(".editor").click(function () {
    $(".save").removeAttr("disabled");//取消保存键禁用
    $(".pic form").css("display","block");
    $(".editor").attr("disabled","disabled");
})

$(".save").click(function () {
    var pictype = $("input[type ='radio']:checked").val();
    if(pictype == "url"){
        if($(".url input").val() == ""){
            alert("没有输入图片的URL");
        }
        $("#imgurl").attr("src",$(".url input").val())
    }else{
        if($(".file input").val() == ""){
            alert("没有上传文件");
        }
        var file = $('input[type = "file"]').prop('files');
        var data = new FormData();
        data.append("file",file[0]);
        $.ajax({
            url: '/editor/picdir',
            type: 'POST',
            data: data,
            cache: false,
            processData: false,
            contentType: false,
            success:function (data) {
                if(data.code == 1){
                    $("#imgurl").attr("src",data["object"]);
                    $("input[type = 'file']").trigger("input");
                }else{
                    alert("上传失败");
                }

            }
        });
    }
    $(".pic form input").attr("disabled",true);
    $(".save").attr("disabled","disabled");
    $(".editor").removeAttr("disabled");

})

