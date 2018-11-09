require.config({        
	paths: {
		"dialog": "../../../lib/layer/layer",        
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown",        
	}          
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	 $(".screenBar form").jqTransform();
	 //删除管理员
	 $('body').on('click','.userWrap .del',function(){
	 	var id = $(this).attr("attrId");
	 	param = {};
	 	param.id = id;
	 	var that = $(this);
	 	$.ajax({
				url:'/manage/distric/school/del.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(msg){
					if(msg.success){
						that.parent().remove();
					}else{
						alert('请求失败');
					}
				},
				error:function(){
					alert('请求失败');
				}
			});
	 });
	 //添加弹出
	 $('.op').on('click',function(){
	 	var id = $(this).attr("id");
	 	$.ajax({
				url:'/manage/distric/school/users.html?schoolId='+id,
				type:"get",
				dataType:"json",
				success:function(msg){
					if(msg.success){
						$(".adminBody").append(msg.data);
						$('.manageBox').show();
					}else{
						alert('提示');
					}
				},
				error:function(){
					alert('请求失败');
				}
			});
	 });
	 //添加确认 点击事件
	 $('body').on('click','.sureUp',function(){
	 	var schoolId = $(".manageBox").attr('attrId');
	 	var ids = $("input[name='userIds']:checked");
	 	var array=[];
		ids.each(function(i,value){
			array.push($(value).val());
		});
		var param = {};
		param.schoolId = schoolId;
		param.userIds = array;
		var that = $(this);
	 	$.ajax({
				url:'/manage/distric/school/update.html',
				type:"post",
				data:param,
				dataType:"json",
				success:function(msg){
					if(msg.success){
						that.parent().hide();
						$('.manageBox').remove();
						$('#'+schoolId+'').html(msg.data);
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