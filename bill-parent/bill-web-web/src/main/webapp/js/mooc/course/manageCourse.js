require.config({          
	baseUrl: '/',           
	paths: {
		"dialog": "js/lib/layer/layer",        
		"jqtransform": "js/lib/jqTransform/jqtransform",
		"headerSlideDown": "js/common/headerslideDown",
"webuploader": "js/lib/webuploader/webuploader",
	}          
});


require(['jqtransform','js/admin/common/adminAlertBox','headerSlideDown'], function(jqtransform,adminAlertBox,aaaa) {

	$('.manageCourse').on('click',function(){
		var moocId=$(this).attr('value');
		window.location.href="/mooc/course/courseManage.html?moocId="+moocId;
	});
	
});