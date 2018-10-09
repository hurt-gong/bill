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
	 $('.tableBox input[type=button]').on('click',function(){
	 	$('.manageBox').show();
	 });
	 $('#sure').on('click',function(){
	 	$('.alertMange').show();
	 });
	 $('.closeAlert').on('click',function(){
	 	$(this).parent().parent().hide();
	 });

})