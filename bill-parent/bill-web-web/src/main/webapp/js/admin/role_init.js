require.config({        
	paths: {
		"dialog": "../lib/layer/layer",        
		"jqtransform": "../lib/jqTransform/jqtransform",
		"headerSlideDown": "../common/headerslideDown",        　
	}          
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	 $(".adminBody form").jqTransform();
	 // a.alert1()

	 $("#btn").click(function(){
	 	var val=$(this).text();
	 	if(val=='配置权限'){
	 		$(this).text('保存配置');
	 		$(".check").find('a.jqTransformCheckbox').css('opacity','1');
	 		$('.role button').show();
	 	}else if(val=='保存配置'){
	 		$(this).text('配置权限');
	 		$(".check").find('a.jqTransformCheckbox').css('opacity','0');
	 		$('.role button').hide();
	 	}
	 	
	 });
	 $('.firstLi a').addClass('jqTransformChecked');
	 $('.roleGroup span').click(function(){
	 	$(this).parent().stop().animate({'height':160},500).siblings().stop().animate({'height':50},500);
	 	$(this).addClass('animate').parent().siblings().find('span').removeClass('animate');
	 })

});


