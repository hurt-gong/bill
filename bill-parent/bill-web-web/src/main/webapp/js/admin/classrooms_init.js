require.config({　　　　
	paths: {　　　　　　
		"dialog": "../lib/layer/layer",　　　　
		"jqtransform": "../lib/jqTransform/jqtransform",
		"My97DatePicker": "../lib/My97DatePicker/WdatePicker",
		"headerSlideDown": "../common/headerslideDown",　　　　　
　　　　
	}　　
});

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
     });
require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	/* $(".adminBody form,.screenBar form").jqTransform();
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
	 })*/
	$('.topNav li').on('click',function(){
		$(this).addClass('navStyle').siblings().removeClass('navStyle');
	});
	$('.change>a').on('click',function(){
		$(this).addClass('essenceStyle').siblings().removeClass('essenceStyle');
	});
	/*$('.floorSpan a').on('click',function(){
		$(this).parent().parent().animate({'paddingBottom':490},500);
	});*/
	$('.roomBottom>h4').on('click',function(){
		$(this).addClass('publish_btn').siblings('h4').removeClass('publish_btn');
		$('.roomBottom>div').eq($(this).index()).css('display','block').siblings('div').css('display','none');
	})
	$('.publish_type p>span').find('label').on('click',function(){
		$(this).addClass('labelChecked').parent().siblings().find('label').removeClass('labelChecked');
	 });
	$('.publish_viewStaff p>span').find('label').on('click',function(){
		$(this).addClass('labelChecked').parent().siblings().find('label').removeClass('labelChecked');
	 });
	$('#set_checked').on('click',function(){
		if($(this).siblings("input").is(':checked')){
          $(this).removeClass('greade_checked');
          $(this).siblings("input").removeProp('checked')
        }
        else{
          $(this).addClass('greade_checked');
          $(this).siblings("input").prop('checked','checked')
        }
		
	})
	$('.tieziall').on('click',function(){
		var $allL = $('.post').find('ul').height();
		// var $allL = $('.post').find('ul>li').length*$('.post').find('ul>li').height()+21;
		if($allL==260){
			var $allLi = $('.post').find('ul>li').length*($('.post').find('ul>li').height()+1)+20;
			$('.post').find('ul').stop().animate({height:$allLi+"px"},1000);
			$(this).html('收起');
		}else{
			$('.post').find('ul').stop().animate({height:"260px"},1000);
			$(this).html('展开全部');
		}
		
	});
	$('.memberall').on('click',function(){
		var $oDl = $('.post').find('.peoManage');
		var $allL = $oDl.height();

		if($allL==194){
			var $allLi = 0;
			if($oDl.find('dl').length%3==0){
				
				$allLi = (($oDl.find('dl').length-$oDl.find('dl').length%3)/3)*($oDl.find('dl').height()+8);
			}else{
				$allLi = (($oDl.find('dl').length-$oDl.find('dl').length%3)/3+1)*($oDl.find('dl').height()+8);
			}
			$(this).html('收起');
			$oDl.stop().animate({height:$allLi+"px"},1000);
		}else{
			$oDl.stop().animate({height:"194px"},1000);
			$(this).html('展开全部');
		}
		
	})
});



