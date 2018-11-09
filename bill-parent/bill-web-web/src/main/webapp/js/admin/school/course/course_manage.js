/**校-课程管理-课程管理*/
require.config({        
	paths: {
		"dialog": "../../../lib/layer/layer",        
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown",　
	}          
});


require(['jqtransform','headerSlideDown','dialog'], function(jqtransform,aaaa,dialog) {
	/**效果*/
	$(".adminBody form,.screenBar form").jqTransform();
	$('.closeAlert').on('click',function(){
	 	$(this).parent().parent().hide();
	 });
	 $('.alertMange .cel').on('click',function(){
	 	$('.alertMange').hide();
	 })
	 $('.clearBox .cel').on('click',function(){
	 	$('.clearBox').hide();
	 })

	/**事件*/
	var url;
	var param = {};
	var callback;
	/**添加学科*/
	$('.twoRowRight .dBtn').on('click',function(){
		param = {}
		$('.alertMange>h2 span').text('添加学科');
		url='/manage/school/course/addSubject.html';
		callback='add';
		$('.alertMange').show();
	});

	/**修改学科*/
	$('.twoRowRight .update').on('click',function(){
		param = {}
		$('.alertMange>h2 span').text('修改学科');
		url='/manage/school/course/updateSubject.html';
		param.id=$(this).parent().attr('value');
		$('.alertMange input').val($(this).siblings('span').text());
		callback='update';
		$('.alertMange').show();
	});

	/**保存事件*/
	$('.alertMange .sure').on('click',function(){
		param.name = $('.alertMange input[name="name"]').val().trim();
		if(param.name=='' || param.name.length<1){
			tips('请输入学科','.alertMange input[name="name"]');
			return;
		}
		$.ajax({
			url:url,
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){
				if(data.success){
					$(".alertMange").hide();
					dialog.msg('保存成功', {time: 3000, icon:1});
					if(callback == 'add'){
						location.href="/manage/school/course/list.html";
					}else if(callback=='update'){
						var opArea = $('.twoRowRight li');
						for(var i=0;i<opArea.size();i++){
							if($(opArea).eq(i).attr('value')==param.id){
							  $(opArea).eq(i).find('span').text(param.name);
							}
						}
					}
				}else{
					dialog.msg(data.message, {time: 3000, icon:7});
				}
					$('.alertMange input[type="text"]').val('');
			},
			error:function(res){
				dialog.msg('请求失败,请检查学科名称', {time: 3000, icon:2});
				$('.alertMange input[type="text"]').val('');
				$(".manage_box").hide();
			}
		});
	})
	//学科删除事件
	$('.twoRowRight .del').on('click',function(){
		param = {}
		param.id=$(this).parent().attr('value');
		url='/manage/school/course/delSubject.html';
	 	$('.clearBox').show();
	 });
	 //学科删除确定事件
	$('.clearBox .sure').on('click',function(){
		$.ajax({
			url:url,
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){
				$(".clearBox").hide();
				if(data.success){
					var opArea = $('.twoRowRight li');
					for(var i=0;i<opArea.size();i++){
						if($(opArea).eq(i).attr('value')==param.id){
						  $(opArea).eq(i).remove();
						}
					}
					dialog.msg('删除成功', {time: 3000, icon:1});
				}else{
					dialog.msg(data.message, {time: 3000, icon:7});
				}
			},
			error:function(res){
				dialog.msg('请求失败,请检查学科名称', {time: 3000, icon:2});
				$(".clearBox").hide();
			}
		});
	 });
	
	/**提示层样式*/
	function tips(val,obj){
		dialog.tips(val, obj, {
			tips: [2, '#0082c8']
		});
	}
})