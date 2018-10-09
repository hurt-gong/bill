require.config({　　　　
	paths: {　　　　　　
		"dialog": "../lib/layer/layer",　　　　
		"jqtransform": "../lib/jqTransform/jqtransform",
		"headerSlideDown": "../common/headerslideDown",　
	}　　
});


require(['jqtransform','headerSlideDown','dialog'], function(jqtransform,aaaa,dialog) {

	

		$("#save").click(function(){
			var old=$("#old").val();
			var new1=$("#new").val();
			var surenew=$("#surenew").val();
				if(new1 != surenew){
					alert("密码输入不一致！");
					return;
				}
				$(".editpwdform").submit();
		});



	


		

});
