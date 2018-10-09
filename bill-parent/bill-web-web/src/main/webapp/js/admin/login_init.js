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
	$('button.submitBtn').on('click',function(){
		 var param={};
		 param.username=$('input[name="username"]').val();
		 param.password=$('input[name="password"]').val();
		 param.ReturnURL=$('input[name="ReturnURL"]').val();
		 param.checkcode=$('input[name="checkcode"]').val();
		 param.token=$('input[name="token"]').val();
		 
		if(param.username.length<4){
			$('.longinCon em').text('请输入完整用户名');
			return;
		}else if(param.password.length<4){
			$('.longinCon em').text('请输入正确密码位数');
			return;
		}else if(param.checkcode.length!=4){
			$('.longinCon em').text('请输入完整验证码');
			return;
		}
		$.ajax({
			url:'/account/ajaxlogin.html',
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){
				if(data.success){
					$('.longinCon em').attr('style','color:green');
					$('.longinCon em').text('登录成功，正在跳转..');
					location.href= param.ReturnURL;
				}else{
					$('.longinCon em').text(data.message);
					$('.verify img').attr('src','/captcha/checkcode.html?token='+param.token+'&t'+(new Date().getTime()));
				}
			},
			error:function(res){
				$('.longinCon em').text('请求失败,请刷新页面重试');
			}
		});
	 });
});