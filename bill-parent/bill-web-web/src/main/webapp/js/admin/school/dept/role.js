require.config({　　　　
	paths: {　　　　　　
		"dialog": "../../../lib/layer/layer",　　　　
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown",
	}　　
});


require(['jqtransform','headerSlideDown','dialog'], function(jqtransform,aaaa,dialog) {
	 $(".screenBar form").jqTransform();
	 
	 //添加角色弹出
	 $('.addBtn').on('click',function(){
	 	$('.addAlert input').val('');
	 	$('.addAlert').show();
	 });
	 //修改角色弹出
	 $('body').on('click','.edit',function(){
	 	$('.updateAlert').attr('attrId',$(this).siblings('span').attr('id'));
	 	$('.updateAlert input').val($(this).siblings('span').text());
	 	$('.updateAlert').show();
	 });
	 //角色选中事件
	 $('body').on('click','.cul li span',function(){
	 	window.location.href = '/manage/school/role/list.html?id='+$(this).attr('id');
	 });
	 //删除角色名称
	 $('body').on('click','.cul li .del',function(){
	 	//alert($(this).siblings('span').attr('id'));
	 	window.location.href= '/manage/school/role/del.html?id='+$(this).siblings('span').attr('id');
	 });

	 //配置人员 弹出
	 $('.dBtn').on('click',function(){
	 	var id = $('.cul .active span').attr('id'); 	
		var param = {};
		param.deptId = id;
		if(id) {
			$.ajax({
				url:'/manage/school/dept/users.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(msg){
					if(msg.success){
						$(".adminBody").append(msg.data);
						$('.manageBox').show();
					}else{
					}
				},
				error:function(){
				}
			});
		}
	 });


	 //删除角色人员
	 $('body').on('click','.bul .del',function(){
	 	var id = $(this).parent().attr("attrid");
	 	param = {};
	 	param.id = id;
	 	var that = $(this);
	 	$.ajax({
				url:'/manage/school/dept/delroleteacher.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(date){
					if(date.success){
						that.parent().remove();
						dialog.msg('删除成功', {time: 3000, icon:1});
					}else{
						alert('请求失败');
					}
				},
				error:function(){
					alert('请求失败');
				}
			});
	 });



	 //添加角色确认
	 $('.addAlert .sure').on('click',function(){
	 	var name = $('.addAlert input').val();
	 	if(!name){
			dialog.tips('请输入角色', '.addAlert input', {
			  tips: [2, '#3595CC'],
			  time: 2000
			});
			return;
		}
	 	var param = {};
	 	param.name = name;
	 	if(name){
			$.ajax({
				url:'/manage/school/role/add.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(data){
					if(data.success){
						var content = '<li class="cf "><span id='+data.data.id+'>'+data.data.name+'</span><a href="javascript:void(0)" class="del">删除</a><a href="javascript:void(0)" class="edit">修改</a></li>';
						$('.cul').append(content);
						dialog.msg('添加成功', {time: 3000, icon:1});
					}else{
					}
				},
				error:function(){
				}
			});
		}else{
			
		}
        $(this).parent().hide();	
	 });
	 //修改角色 确定
	 $('.updateAlert .sure').on('click',function(){
	 	var name = $('.updateAlert input').val();
	 	var param = {};
	 	param.id = $('.updateAlert').attr('attrId');
	 	param.name = name;

	 	if(name){
			$.ajax({
				url:'/manage/school/role/update.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(date){
					if(date.success){
						if(date.data){
							$('#'+param.id+'').text(param.name);
							dialog.msg('修改成功', {time: 3000, icon:1});
						}
					}else{
					}
				},
				error:function(){
				}
			});
		}else{
			alert('请输入名称!');
		}
        $(this).parent().hide();	
	 });

	 //添加确认 点击事件
	$('body').on('click','.sureUp',function(){
		var deptId = $(".manageBox").attr('attrId');
		var ids = $("input[name='userIds']:checked");
		var array=[];
		ids.each(function(i,value){
			array.push($(value).val());
		});
		var param = {};
		param.deptId = deptId;
		param.userIds = array;
		var that = $(this);
		$.ajax({
			url:'/manage/school/dept/updateUsers.html',
			type:"post",
			data:param,
			dataType:"json",
			success:function(msg){
				if(msg.success){
					that.parent().hide();
					$('.manageBox').remove();
					$('.bul').html(msg.data);
				}else{
					alert('提示');
				}
			},
			error:function(){
				alert('请求失败');
			}
		});
	});

	$('body').on('click','.closeAlert',function(){
	 	$(this).parent().parent().hide();
	 	$('.manageBox').remove();
	 }); 

	 $('body').on('click','.cel',function(){
	 	$(this).parent().hide();
	 	$('.manageBox').remove();
	 }); 

})