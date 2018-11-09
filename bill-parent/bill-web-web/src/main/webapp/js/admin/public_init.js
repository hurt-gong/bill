require.config({        
	paths: {
		"dialog": "../lib/layer/layer",        
		// "jqtransform": "../lib/jqTransform/jqtransform",
		"headerSlideDown": "../common/headerslideDown",        　
	}          
});


require(['headerSlideDown'], function(aaaa) {
	 // $(".adminBody form").jqTransform();
	 // a.alert1()

});

$('.changeLeft').find('label').on('click',function(){
	if($(this).siblings("input[type='checkbox']").is(':checked')){
		$(this).removeClass('asncheck');
		 $(this).siblings("input[type='checkbox']").removeProp('checked')
      	$('.mainCon').find('label').removeClass('asncheck');
      	$('.mainCon').find("input[type='checkbox']").removeProp('checked')
    }
    else{
      $(this).addClass('asncheck');
      $(this).siblings("input[type='checkbox']").prop('checked','checked');
      $('.mainCon').find('label').addClass('asncheck');
      $('.mainCon').find("input[type='checkbox']").prop('checked','checked');
    }
});
$('.mainCon').find('label').on('click',function(){
	$('.changeLeft').find('label').removeClass('asncheck').siblings("input[type='checkbox']").removeProp('checked');
	if($(this).siblings("input[type='checkbox']").is(':checked')){
		$(this).removeClass('asncheck');
		 $(this).siblings("input[type='checkbox']").removeProp('checked')
     }
    else{
      $(this).addClass('asncheck');
      $(this).siblings("input[type='checkbox']").prop('checked','checked');
    }
});

 