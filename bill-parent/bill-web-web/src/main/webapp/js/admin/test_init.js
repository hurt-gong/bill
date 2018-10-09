require.config({　　　　
	paths: {　　　　　　
		"dialog": "../lib/layer/layer",
		"headerSlideDown": "../common/headerslideDown",　　　　
	}　　
});


require(['dialog', './a','headerSlideDown'], function(dialog, a,aaaa) {
	layer.msg('Hello layer');
	 // a.alert1()

})