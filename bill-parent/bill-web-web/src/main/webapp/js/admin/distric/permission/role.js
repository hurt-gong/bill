require.config({        
	paths: {
		"dialog": "../../../lib/layer/layer",        
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown",        　
	}          
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	  $(".adminBody form").jqTransform();
	 // a.alert1()
	 $(".clickLi").click(function(){
	 	window.location.href="/manage/distric/permission/user/roleList.html?roleId="+$(this).attr("myId")
	 });

	 if($('.roleGroup').find('li.liHover').size()>0){
	 	$('.adminTopBar>span').removeClass('hidden');
	 }

	 $("#btn").click(function(){
		 	var val=$(this).text();
		 	if(val=='配置权限'){
		 		$(this).text('取消配置');
		 		$(".check").find('a.jqTransformCheckbox').css('opacity','1');
		 		$('.role button').show();
		 		$('.role_bg').show();
		 		$(".role_bg1").hide();
		 	}else if(val=='取消配置'){
		 		$(this).text('配置权限');
		 		$(".check").find('a.jqTransformCheckbox').css('opacity','0');
		 		$('.role button').hide();
				$('.role_bg').hide();
		 	}
		 	
		 });
	 $('.firstLi a').addClass('jqTransformChecked');
	 $('.roleGroup span').click(function(){
	 	var L=$(this).nextAll().length;
	 	var H=$(this).next().outerHeight(true);
	 	//console.log(L*H);
	 	$(this).parent().stop().animate({'height':L*H+50},500).siblings().stop().animate({'height':50},500);
	 	$(this).addClass('animate').parent().siblings().find('span').removeClass('animate');
	 })
	$('.roleGroup li').find('p').on('click',function(){
		$('.roleGroup li p').removeClass('pStyle');
 		$(this).addClass('pStyle');
 	})
 	$(".sure").click(function(){
 		$("#addPer").attr('onSubmit','true');
 	});

 	 function subform(){

 	}


 	$(".cancel").click(function(){
 		var roleId=$(".roleGroup ul .liHover").attr("myid");
 		window.location.href="/manage/distric/permission/user/roleList.html?roleId="+roleId
 	});

 	/*$("input[name='perCode']").click(function(){
 		 $("[name='perCode']").each(function(){
 		 	if($(this).attr("checked")){
 		 		alert($(this).val());
 		 	}
 		 });
 	});*/



 			//全选
	$(document).on('click','.onerole .oneleft',function(){
		if($(this).find('a').hasClass('jqTransformChecked')){
			$('.onerole li .jqTransformCheckbox').removeClass('jqTransformChecked');
			$('.onerole li input[name="perCode"]').attr("checked",false);
		}else{
			$('.onerole li .jqTransformCheckbox').addClass('jqTransformChecked');
			$('.onerole li input[name="perCode"]').prop('checked','checked');
		}
	})

	$(document).on('click','.onerole li',function(){
		if($(this).find('.jqTransformChecked').hasClass('jqTransformChecked')){
			//取消选中
			//如果兄弟有样式
			if ($(this).siblings().find('.jqTransformChecked').size()>0) {
				$(this).parents('.onerole').find('.oneleft a').addClass('jqTransformChecked');
				$(this).parents('.onerole').find('.oneleft input[name="perCode"]').prop('checked','checked');
			}else{
				
				$(this).parents('.onerole').find('.oneleft a').removeClass('jqTransformChecked')
				$(this).parents('.onerole').find('.oneleft input[name="perCode"]').attr("checked",false);
			}
			
		}else{
			//选中
			$(this).parents('.onerole').find('.oneleft a').addClass('jqTransformChecked');
			$(this).parents('.onerole').find('.oneleft input[name="perCode"]').prop('checked','checked');
		}
	})



});


