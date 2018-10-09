require.config({　　　　
	paths: {　　　　　　
		"dialog": "../lib/layer/layer",　　　　
		"jqtransform": "../lib/jqTransform/jqtransform",
		"My97DatePicker": "../lib/My97DatePicker/WdatePicker",
		"headerSlideDown": "../common/headerslideDown",　　　　　
　　　　
	}　　
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	 $(".adminBody form,.screenBar form").jqTransform();
	 require(['http://edu.bjhd.gov.cn/js/lib/My97DatePicker/WdatePicker.js'],function(){
		 $("#time").on("click",function(){
		 		WdatePicker({
		 		el:'time',
		 	})
		 	
		 	
		 });
		 $("#time1").on("click",function(){
		 	WdatePicker({
		 		el:'time1',
		 	})
		 })
	 })

});



