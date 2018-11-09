/**校-任教管理-任课教师*/
require.config({        
	paths: {
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown"
	}          
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	$(".screenBar form").jqTransform();
	$('.seach .sub').on('click',function(){
		var teacherName = $('.seach input[name="teacherName"]').val().trim();
		location.href= '/manage/school/teach/teacherByName.html?name='+teacherName;
	 });
})
//学段下拉事件
function phaseChage(val){
	$.ajax({
		url:'/manage/school/teach/getGradeList.html',
		type:"get",
		data:{"phaseId":val},
		dataType:"json",
		success:function(data){
			if(data.success){
				$('.screenBar select.grade').html(data.data.data);
				fix_select('.screenBar select.grade');
				submitForm();
			}
		},
		error:function(){
			//dialog.msg('请求失败', {time: 3000, icon:2});
		}
	});
}
/**年级下拉事件*/
function gradeChage(){
	submitForm();
}
/**学科下拉事件*/
function subjectChage(){
	submitForm();
}
//下拉框渲染
function fix_select(selector) { 
	var i=$(selector).parent().find('div,ul').remove().css('zIndex'); 
	$(selector).unwrap().removeClass('jqTransformHidden').jqTransSelect(); 
	$(selector).parent().css('zIndex', i); 
}
//提交查询
function submitForm(){
	$('.screenBar input[name="phaseId"]').val($('.screenBar select.phase option:selected').val());
	$('.screenBar input[name="gradeId"]').val($('.screenBar select.grade option:selected').val());
	$('.screenBar input[name="subjectId"]').val($('.screenBar select.subject option:selected').val());
	$('#userForm').submit();
}