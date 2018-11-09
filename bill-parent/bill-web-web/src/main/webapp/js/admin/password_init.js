require.config({
	baseUrl: '/',        
	paths: {
		"dialog": "js/lib/layer/layer",        
		"jqtransform": "js/lib/jqTransform/jqtransform",
		"headerSlideDown": "js/common/headerslideDown"
	}          
});
require(['/js/lib/avatareditor/0.0.1/fullAvatarEditor.js','/js/lib/avatareditor/0.0.1/swfobject.js'], function() {
	//控件参数参考：http://www.fullavatareditor.com/api.html#usage
swfobject.addDomLoadEvent(function () {
    var swf = new fullAvatarEditor(['*/FullAvatarEditor.swf'],['*/expressInstall.swf'],"swfContainer", {
id: 'swf',
upload_url: '/Home/UploadAction',
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
avatar_sizes: "100*100|50*50|32*32",//100*100|50*50|32*32,表示一组或多组头像的尺寸。其间用"|"号分隔。
    },302,400,function (msg) {
switch (msg.code) {
    // case 1: alert("页面成功加载了组件！"); break;
    // case 2: alert("已成功加载默认指定的图片到编辑面板。"); break;
    case 3:
if (msg.type == 0) {
    alert("摄像头已准备就绪且用户已允许使用。");
}
else if (msg.type == 1) {
    alert("摄像头已准备就绪但用户未允许使用！");
}
else {
    alert("摄像头被占用！");
}
break;
    case 5:
if (msg.type == 0) {
    if (msg.content.sourceUrl) {
alert("原图片已成功保存至服务器，url为：\n" + msg.content.sourceUrl);
    }
    alert("头像已成功保存至服务器，url为：\n" + msg.content.avatarUrls.join("\n"));
}
break;
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
