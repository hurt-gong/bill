require.config({　　　　
	paths: {　　　　　　
		"dialog": "../lib/layer/layer",　　　　
		"jqtransform": "../lib/jqTransform/jqtransform",
		"headerSlideDown": "../common/headerslideDown",　　　　
	}　　
});


require(['jqtransform','./common/adminAlertBox','headerSlideDown'], function(jqtransform,adminAlertBox,aaaa) {
	
	 /*$(".adminBody form").jqTransform();
	 // a.alert1()
	 $('.fristChecked a').addClass('jqTransformChecked')
	 $('#btn').click(function(){
	 	$('.alertBox').show();
	 });
	 $("#close").click(function(){
	 	$('.alertBox').hide();
	 })*/

})