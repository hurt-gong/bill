

require.config({
	baseUrl: 'http://edu.bjhd.gov.cn/',　　　　
	paths: {　　　　　　
		"dialog": "js/lib/layer/layer",　　　　
		"jqtransform": "js/lib/jqTransform/jqtransform",
		"headerSlideDown": "js/common/headerslideDown"
	}　　
});
require(['http://edu.bjhd.gov.cn/js/lib/avatareditor/0.0.1/fullAvatarEditor.js','http://edu.bjhd.gov.cn/js/lib/avatareditor/0.0.1/swfobject.js'], function() {
	//控件参数参考：http://www.fullavatareditor.com/api.html#usage
        swfobject.addDomLoadEvent(function () {
            var swf = new fullAvatarEditor(['http://edu.bjhd.gov.cn/js/lib/avatareditor/0.0.1/FullAvatarEditor.swf'],['http://edu.bjhd.gov.cn/js/lib/avatareditor/0.0.1/expressInstall.swf'],"swfContainer", {
                id: 'swf',
                upload_url: 'http://edu.bjhd.gov.cn/upload/file.html',
                 // src_url: "/samplePictures/Default.jpg",//默认加载的原图片的url
                src_upload: 2,//默认为0；是否上传原图片的选项，有以下值：0:不上传；1:上传；2 :显示复选框由用户选择
                isShowUploadResultIcon: false,//在上传完成时（无论成功和失败），是否显示表示上传结果的图标
                src_size: "2MB",//选择的本地图片文件所允许的最大值，必须带单位，如888Byte，88KB，8MB
                src_size_over_limit: "文件大小超出2MB，请重新选择图片。",//当选择的原图片文件的大小超出指定最大值时的提示文本。可使用占位符{0}表示选择的原图片文件的大小。
                src_box_width: "398",//原图编辑框的宽度
                src_box_height: "300",//原图编辑框的高度
                tab_visible: false,//是否显示选项卡*
                browse_box_width: "398",//图片选择框的宽度
                browse_box_height: "300",//图片选择框的高度
                avatar_sizes: "100*100",//100*100|50*50|32*32,表示一组或多组头像的尺寸。其间用"|"号分隔。
            },302,400,function (msg) {
                if(msg.code==5){
                    //上传头像成功后提交表单
                    if(msg.type == 1){
                        param = {};
                        param.avatarUrl = msg.content.url;
                        $.ajax({
                            url:'/ucenter/uploadAvatar.html',
                            type:"post",
                            data:param,
                            dataType:"json",
                            success:function(data){
                                if(data.success){
                                    $(".userArea .headerImg img").attr('src',msg.content.url);
                                    $(".passCon .passLeft img").attr('src',msg.content.url);
                                }else{
                                }
                            },
                            error:function(){
                            }
                        });
                        $(".userArea .headerImg img").attr('src',msg.content.url);
                    }else{
                        alert('头像上传失败，原因：指定的上传地址不存在或有问题！');
                    }
                }
            }
            );
            document.getElementById("upload").onclick = function () {
                swf.call("upload");
            };
        });
});

require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	$('#tab p').on('click',function(){
		$(this).addClass('tabStyle').siblings().removeClass('tabStyle');
		$('.passRight>div').eq($(this).index()).show().siblings().hide();
	});
});

