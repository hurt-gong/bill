require.config({        
	paths: {
		"dialog": "../lib/layer/layer",        
		"jqtransform": "../lib/jqTransform/jqtransform",
		"headerSlideDown": "../common/headerslideDown",        
	}          
});


require(['jqtransform','./common/adminAlertBox','headerSlideDown'], function(jqtransform,adminAlertBox,aaaa) {
	 //$(".adminBody form").jqTransform();
	

})