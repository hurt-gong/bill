require.config({        
	paths: {
		"dialog": "../lib/layer/layer",        
		"jqtransform": "../lib/jqTransform/jqtransform",
		"headerSlideDown": "../common/headerslideDown",        
	}          
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	 $(".screenBar form").jqTransform();
	 // a.alert1()

})