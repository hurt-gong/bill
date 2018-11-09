require.config({          
	baseUrl: '/',           
	paths: {
		"dialog": "js/lib/layer/layer",        
		"jqtransform": "js/lib/jqTransform/jqtransform",
		"headerSlideDown": "js/common/headerslideDown",
"webuploader": "js/lib/webuploader/webuploader",
	}          
});


require(['jqtransform','js/admin/common/adminAlertBox','headerSlideDown','dialog'], function(jqtransform,adminAlertBox,aaaa,dialog) {

	//点击学段事件
	$(".phase").on('click',function(){
		$("#phaseId").val($(this).attr('value'));
		$("#gradeId").val("");
		$("#subjectId").val("");
		$("#key1Id").val("");
		$("#moocPage").submit();
	});	

	//点击年级事件
	$('.grade').on('click',function(){
		$("#gradeId").val($(this).attr('value'));
		$("#subjectId").val("");
		$("#key1Id").val("");
		$("#moocPage").submit();
	});


	//点击学科事件
	$('body').on('click','.subject',function(){
		 $("#subjectId").val($(this).attr('value'));
		 $("#key1Id").val("");
		 $("#moocPage").submit();
	});


	//点击按章节查询事件
	$("body").on('click',".section",function(){
		$("#objType").val('1');
		$("#key1Id").attr("value",null);
		$("#moocPage").submit();
	});


	//点击知识点下的一级目录查询微课程
	$('body').on('click','.knowledge',function(){
		 var key1Id=$(this).attr('value');
		 $("#key1Id").val(key1Id);
		$("#moocPage").submit();
	});


	//点击按章节下的一级目录查询微课程
	$('body').on('click','.section',function(){
		 var key1Id=$(this).attr('value');
		 $("#key1Id").val(key1Id);
		$("#moocPage").submit();
	});


	//点击查询知识点事件
	$('body').on('click','.knowledge1',function(){
			 var objType=$(this).attr('value');
			 $("#objType").val(objType);
			 $("#key1Id").val("");
			 $("#moocPage").submit();
	});


	//定义一个flag，点击最近更新flag=1，点播最多flag=2，评星最高flag=3

	//最近更新
	$('body').on('click','#newupdate',function(){
		// $("#newupdate").attr('class','aaaa');
		// $("#checkmost").attr('class','');
		// $("#scoremost").attr('class','');
		$("#flag").val("1");
		$("#moocPage").submit();
	});

	//点播最多
	$("body").on('click','#checkmost',function(){
		// $("#checkmost").attr('class','aaaa');
		// $("#newupdate").attr('class','');
		// $("#scoremost").attr('class','');
		$("#flag").val("2");
		$("#moocPage").submit();
	});

	//评星最高
	$("body").on('click','#scoremost',function(){
		// $("#scoremost").attr('class','aaaa');
		// $("#newupdate").attr('class','');
		// $("#checkmost").attr('class','');
		 $("#flag").val("3");
		 $("#moocPage").submit();
	});
});