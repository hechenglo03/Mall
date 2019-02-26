var id = null;//shang
//显示按钮的注册事件

$("#show").click(function () {
    var imgurl = $(".imgurl").val()
    if(imgurl == ""){
        alert("没有选定图片的URL");
    }else{
        $("#imgurl").attr("src",imgurl);
    }
});


//表单验证
$("form").bootstrapValidator({

    live: 'disabled',
    //表单域配置
    fields: {
        title:{
            validators:{
                notEmpty: {message: '请输入商品的标题'},
                strlength:{
                    min: 2,
                    max: 80,
                    message: '用户名长度必须在6到30之间'
                }
            }
        },
        summary:{
            validators:{
                notEmpty: {message: '请输入商品的摘要'},
                strlength:{
                    min: 2,
                    max: 140,
                    message: '用户名长度必须在6到30之间'
                }
            }
        },
        maincontent:{
            validators:{
                notEmpty: {message: '请输入商品的正文'},
                strlength:{
                    min: 2,
                    max: 1000,
                    message: '用户名长度必须在2到1000之间'
                }
            }
        },
        price:{
            validators:{
                notEmpty: {message: '请输入商品的正文'},
                strlength:{
                    min: 2,
                    max: 1000,
                    message: '用户名长度必须在2到1000之间'
                }
            }
        },
        file:{
            validators:{
                notEmpty:{message:'请上传文件'},
                callback:{
                    message:"文件没有上传",
                    callback:function (value,validator,$field) {
                        if($("input[type='radio']:checked").val() == "file"){
                            console.log($("#imgurl").attr("src"));
                            if($("#imgurl").attr("src") == undefined)
                                return false;
                        }
                        return true;
                    }
                }
            }
        }
    }
})

//提交按钮的事件
$("#action").click(function () {
        if ($("form").data("bootstrapValidator").validate().isValid()) {
            var title = $('input[name = "title"]').val();
            var summary = $('input[name = "summary"]').val();

            var SelectedInput = $('input[name = "file"]:enabled');

            var pic;

            //依据上传方式不同决定图片的地址
            if (SelectedInput.attr("type") == "text") {
                pic = SelectedInput.val();
            } else {
                pic = $("#imgurl").attr("src");
            }
            var way = $('input[name ="picture"]:checked').val();
            var content = $('#maincontent').val();
            var price = $('input[name = "price"]').val();
            alert(pic);

            $.ajax({
                url: "/editor/add",
                type: "POST",
                data: {
                    "id":id,
                    "title": title,
                    "summary": summary,
                    "pic": pic,
                    "way": way,
                    "content": content,
                    "price": price
                },
                dataType: "json",
                success: function (data) {
                    //成功的录入商品
                    if (data.code == 1) {
                        $.confirm({
                            title: "商品录入结果",
                            content: "成功录入商品",
                            buttons: {
                                返回首页: function () {
                                    window.location.href = "/home";
                                },
                                继续编辑: function () {//在表单中设置一个清空的input，清空所有input
                                    window.location.reload();//重新加载
                                }
                            }
                        })
                    }
                }
            })
        }
    });

 $(":radio").click(function () {
     var value= $('input[name="picture"]:checked').val();
     if(value == "url"){
         $(".file").addClass("miss");
         $(".file input").attr("disabled",true);//表单验证需要
         $(".url").removeClass("miss");
         $(".url input").attr("disabled",false);
     }else{
         $(".url").addClass("miss");
         $(".url input").attr("disabled",true);
         $(".file").removeClass("miss");
         $(".file input").attr("disabled",false);
     }
 })

//上传图片按钮事件
$("#upload").click(function () {
    var file = $('input[type = "file"]').prop('files');
    //没有上传文件
    if(file.length == 0){
        alert("没有选择图片");
        return;
    }
    var data = new FormData();
    data.append("file",file[0]);
    // $.post("/editor/picdir",ldata,function (data) {
    //     if(data['code'] == 1){
    //         $("#imgurl").attr("src",data['object']);
    //     }
    // },"json");

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


})










