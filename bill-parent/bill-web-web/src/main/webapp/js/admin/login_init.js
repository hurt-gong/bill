require.config({
	paths: {
		"jqtransform": "../lib/jqTransform/jqtransform",
		"headerSlideDown": "../common/headerslideDown"

	}
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	 $(".adminBody form,.screenBar form").jqTransform();
	 $('#login').on('click',function(){
		$('.login_text').show();
	});
	$('button.reseatBtn').on('click',function(){
	 	$('.login_text').hide();
	 });
	$('.changepic').on('click',function(){
    	var token =$('input[name="token"]').val();
$('#code').attr("src", "/captcha/checkcode.html?token="+token+"&t="+(new Date()).getTime());
    });
	//$('button.submitBtn').on('click',function(){
		
	 //});
});


function loginSubmit(){
	 var param={};
	 param.username=$('input[name="username"]').val();
	 param.password=$('input[name="password"]').val();
	 param.ReturnURL=$('input[name="ReturnURL"]').val();
	 param.checkcode=$('input[name="checkcode"]').val();
	 param.token=$('input[name="token"]').val();
	 
	if(param.username.length<4){
		$('.form em').text('请输入完整用户名');
		return;
	}else if(param.password.length<4){
		$('.form em').text('请输入正确密码位数');
		return;
	}else if(param.checkcode.length!=4){
		$('.form em').text('请输入完整验证码');
		return;
	}
	$.ajax({
		url:'/account/ajaxlogin.html',
		type:"post",
		data:param,
		dataType:"json",
		success:function(data){
			if(data.success){
				$('.form em').attr('style','color:green');
				$('.form em').text('登录成功，正在跳转..');
				//alert(1);
				location.href= param.ReturnURL;
			}else{
				$('.form em').text(data.message);
				$('#code').attr("src", "/captcha/checkcode.html?token="+param.token+"&t="+(new Date()).getTime());
				//$('.verify img').attr('src','/captcha/checkcode.html?token='+param.token+'&t'+(new Date().getTime()));
			}
		},
		error:function(res){
			$('.form em').text('请求失败,请刷新页面重试');
		}
	});
}