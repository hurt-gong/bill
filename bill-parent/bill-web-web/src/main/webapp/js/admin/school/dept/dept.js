require.config({        
	paths: {
		"dialog": "../../../lib/layer/layer",        
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown",　
	}          
});


require(['jqtransform','headerSlideDown','dialog'], function(jqtransform,aaaa,dialog) {
	var optArea;
	 $(".screenBar form").jqTransform();
	 //添加部门弹出
	$('.adddept').on('click',function(){
		$('.alertdept input').val('');
		$('.alertdept').show();	
	});
	//添加岗位弹出
	$('.addchild').on('click',function(){
		$('.alertchild input').val('');
		$('.alertchild').show();	
	});
	//删除部门、岗位
	$('body').on('click','.deptdel',function(){
		$('.clearBox .sure').attr('value',$(this).parent().attr('id'));
		$('.delDept').show();	
		//window.location.href='/manage/distric/dept/del.html?id='+$(this).parent().attr('id');
	});

	$('body').on('click','.delDept .sure',function(){
		window.location.href='/manage/school/dept/del.html?id='+$(this).attr('value');
	});
	// /**删除用户*/
	//  $('body').on('click','.deptdel',function(){
	// 	$('.clearBox .sure').attr('value',$(this).parent('.operation').attr('value'));
	// 	$('.clearBox p').text('确认删除吗'+$(this).parents('tr').find('.uName').text()+'？');
	// 	optArea=$(this).parents('tr');
	//  	$('.clearBox').show();
	//  }); 

	//点击 部门、岗位跳转页面
	$('body').on('click','.cul li span',function(){
		window.location.href='/manage/school/dept/list.html?id='+$(this).parent().attr('id');
	});
	//修改部门弹出
	$('body').on('click','.dept .deptedit',function(){
		$('.alertupdate input').val($(this).siblings('span').text());
		$('.alertupdate h2').html('修改部门');
		$('.alertupdate p label').html('部门名称:');
		$('.alertupdate').attr('attrId',$(this).parent().attr('id'));
		$('.alertupdate').show();
	});
	//修改岗位弹出
	$('body').on('click','.child .deptedit',function(){
		$('.alertupdate input').val($(this).siblings('span').text());
		$('.alertupdate h2').html('修改岗位');
		$('.alertupdate p label').html('岗位名称:');
		$('.alertupdate').attr('attrId',$(this).parent().attr('id'));
		$('.alertupdate').show();
	});
	//修改部门、岗位
	$('body').on('click','.alertupdate .sure',function(){	
		param = {};
		param.name = $('.alertupdate input').val();
		param.id = $(this).parent().attr('attrId');
		if(!param.name){
			dialog.tips('不能为空', '.alertupdate input', {
			  tips: [2, '#3595CC'],
			  time: 2000
			});
			return;
		}
		$.ajax({
			url:'/manage/school/dept/update.html',
			type:"post",
			data:param,
			dataType:"json",
			success:function(date){
				if(date.success){
					$('#'+param.id+'').children('span').text(param.name);
					dialog.msg('修改成功', {time: 3000, icon:1});
				}else{
				}
			},
			error:function(){
			}
		});
$(this).parent().hide();
	});
	//点击 添加部门确定按钮
	$('.alertdept .sure').click(function(){
		var name = $('.alertdept input').val();
		if(!name){
			dialog.tips('请输入部门', '.alertdept input', {
			  tips: [2, '#3595CC'],
			  time: 2000
			});
			return;
		}
var param = {};
 		var val = $('.alertdept input').val()
		param.name = name;
		param.pid = 0;
 		if(val){
			$.ajax({
				url:'/manage/school/dept/add.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(date){
					if(date.success){
						var content = '<li class="cf" id="'+date.data.id+'"><span>'+name+'</span><a href="javascript:void(0)" class="del deptdel">删除</a><a href="javascript:void(0)" class="edit deptedit">修改</a></li>'
						$('.dept').append(content);
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

    //点击 添加岗位确定按钮
    $('.alertchild .sure').click(function(){
		var name = $('.alertchild input').val();
		if(!name){
			dialog.tips('请输入岗位', '.alertchild input', {
			  tips: [2, '#3595CC'],
			  time: 2000
			});
			return;
		}
		var pid = $('.dept .active').attr('id');
var param = {};
		param.name = name;
		param.pid = pid;
 		if(name){
			$.ajax({
				url:'/manage/school/dept/add.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(date){
					if(date.success){
						var content = '<li class="cf" id="'+date.data.id+'"><span>'+name+'</span><a href="javascript:void(0)" class="del deptdel">删除</a><a href="javascript:void(0)" class="edit deptedit">修改</a></li>'
						$('.child').append(content);
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
    //部门添加人员弹出
	$('.addUsers').on('click',function(){
		var childId = $('.child .active').attr('id');
		var param = {};
		param.deptId = childId;
		if(childId) {
			$.ajax({
				url:'/manage/distric/dept/users.html',
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
			url:'/manage/distric/dept/updateUsers.html',
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


	//删除岗位人员
	$('body').on('click','.postMemberRow .del',function(){
		var id = $(this).parent().attr("attrId");
		param = {};
		param.id = id;
		optArea = $(this);
		$('.clearBox .sure').attr('value',id);
		$('.delUser').show();

		
	});

	$('body').on('click','.delUser .sure',function(){
		var id = $(this).attr("value");
	 	param = {};
	 	param.id = id;
	 	var that = $(this);
	 	$.ajax({
				url:'/manage/distric/dept/delroleteacher.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(data){
					if(data.success){
						$(optArea).parent().remove();
						$('.delUser').hide();
						dialog.msg('删除成功', {time: 3000, icon:1});
					}else{
						alert(msg);
					}
				},
				error:function(){
					//alert('请求失败');
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


	/**提示层样式*/
	function tips(val,obj){
		dialog.tips(val, obj, {
			tips: [2, '#0082c8']
		});
	}

});



	//删除岗位人员
	//  $('body').on('click','.postMemberRow .del',function(){
	//  	var id = $(this).parent().attr("attrId");
	//  	param = {};
	//  	param.id = id;
	//  	var that = $(this);
	//  	$.ajax({
	// 			url:'/manage/distric/dept/delroleteacher.html',
	// 			type:"post",
	// 			data:param,
	// 			dataType:"json",
	// 			success:function(msg){
	// 				if(msg.success){
	// 					that.parent().remove();
	// 				}else{
	// 					alert('请求失败');
	// 				}
	// 			},
	// 			error:function(){
	// 				alert('请求失败');
	// 			}
	// 		});
	//  });
	// $('body').on('click','.closeAlert',function(){
	//  	$(this).parent().parent().hide();
	//  	$('.manageBox').remove();
	//  }); 
	//  $('body').on('click','.cel',function(){
	//  	$(this).parent().hide();
	//  	$('.manageBox').remove();
	//  }); 