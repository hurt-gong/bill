/**区-区教学管理-年级配课
*  校-课程管理-年级配课
*/
require.config({        
	paths: {
		"dialog": "../../../lib/layer/layer",        
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown",　
	}          
});


require(['jqtransform','headerSlideDown','dialog'], function(jqtransform,aaaa,dialog) {
var gradeId;
	var operArea;
	$(".adminBody form,.screenBar form").jqTransform();
	 /**添加事件绑定*/
	 $('.add').on('click',function(){
		 gradeId = $(this).parents().attr('value');
		 operArea=$(this).prev();
		 $.ajax({
			url:'/manage/distric/teach/getSubjectNoSel.html',
			type:"get",
			data:{'gradeId':gradeId},
			dataType:"json",
			success:function(data){
				$('.alertMange .mySelect').html(data.data.data);
				 
				 fix_select('.alertMange .mySelect');
				$('.alertMange').show();
			},
			error:function(res){
				dialog.msg('请求失败,请刷新重试', {time: 3000, icon:2});
				$(".clearBox").hide();
			}
		});
	 });
		//删除
	 $('.tableBox li a').on('click',function(){
		gradeId= $(this).parent().attr('value');
		var subjectId = $(this).attr('value');
		operArea=$(this).parent();
		$.ajax({
			url:'/manage/distric/teach/delGradeSubject.html',
			type:"get",
			data:{'gradeId':gradeId,'subjectId':subjectId},
			dataType:"json",
			success:function(data){
				$(operArea).remove();
				dialog.msg('删除成功', {time: 3000, icon:1});
			},
			error:function(res){
				dialog.msg('请求失败', {time: 3000, icon:2});
			}
		});
	 });
	//保存
	  $('.alertMange .sure').on('click',function(){
		var text = $('.alertMange .jqTransformSelectWrapper span').text();
		var option = $('.alertMange .jqTransformSelectWrapper option');
		var subjectId;
		if($(option).size()==0){
			$('.alertMange').hide();
			return;
		}
		for(var i=0;i<$(option).size();i++){
			if(text == $(option).eq(i).text()){
				subjectId=$(option).eq(i).attr('value');
			}
		}
		 $.ajax({
			url:'/manage/distric/teach/addGradeSubject.html',
			type:"get",
			data:{'gradeId':gradeId,'subjectId':subjectId},
			dataType:"json",
			success:function(data){
				var html = '<li>'+ text+'<a href="javascript:void(0)" value='+subjectId+'>×</a></li>';
				$(operArea).append(html);
				dialog.msg('添加成功', {time: 3000, icon:1});
			},
			error:function(res){
				dialog.msg('请求失败,请检查学科名称', {time: 3000, icon:2});
			}
		});
	 	$('.alertMange').hide();
	 })

	 $('.closeAlert').on('click',function(){
	 	$(this).parent().parent().hide();
	 })
	 $('.alertMange .cel').on('click',function(){
	 	$('.alertMange').hide();
	 })

	//下拉框渲染
	function fix_select(selector) { 
		var i=$(selector).parent().find('div,ul').remove().css('zIndex'); 
		$(selector).unwrap().removeClass('jqTransformHidden').jqTransSelect(); 
		$(selector).parent().css('zIndex', i); 
	} 
})