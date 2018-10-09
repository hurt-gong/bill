define(function(){
	//console.log(123123123)
	
	
/*$(function(){
    $("ul li a").click(function(){
		var Ah = $(this).parent("li").height()*($(this).parent("li").index());
		$("#scr").stop().animate({scrollTop:Ah},10);
    })
	
	$('div.userArea').on('mouseenter',function(){
		//alert(11111);
		var boxHeight=$('div.userArea').height();
		if(boxHeight==65){
			$('div.userArea').stop().animate({'height':144},500);
		}
		
	});
	$('div.userArea').on('mouseleave',function(){
		var boxHeight=$('div.userArea').height();
		if(boxHeight==144){
			//$('div.userArea').stop().animate({'height':144},500);
			$(this).stop().animate({'height':65},500);
		}
			
	});
})*/
$('div.userArea').hover(function(){
		$(this).stop().animate({'height':144},500);	
	},function(){
		$(this).stop().animate({'height':65},500);
	})
})