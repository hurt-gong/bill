require.config({        
	paths: {
		"dialog": "../lib/layer/layer",        
		"jqtransform": "../lib/jqTransform/jqtransform",
		"My97DatePicker": "../lib/My97DatePicker/WdatePicker",　
		"headerSlideDown": "../common/headerslideDown",          
	}          
});


require(['jqtransform','My97DatePicker','./common/adminAlertBox','headerSlideDown'], function(jqtransform,m97,adminAlertBox,aaaa) {
	
$(".adminBody form").jqTransform();
	 // a.alert1()
	 require(['/js/lib/My97DatePicker/WdatePicker.js'],function(){
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
	 $(".mysql button").on('click',function(){
	 	//$(this).addClass('btnStyle').parent().siblings().find('button').removeClass('btnStyle');
	 	var val=$(this).text();
	 	if(val=='上架'){
	 		$(this).removeClass('btnStyle');
	 		$(this).text('下架');
	 		
	 	}else if(val=='下架'){
	 		$(this).text('上架');
	 		$(this).addClass('btnStyle');
	 		$('div.sqlBox').show();
	 	}
	 	$('#close').click(function(){
	 		$('div.sqlBox').hide();
	 		$('.mysql button').eq($(this).index()).removeClass('btnStyle')

	 	});
	 })

	 $('.timeChange a').on('click',function(){
	 	$(this).addClass('change').siblings().removeClass('change');
	 })
})
})