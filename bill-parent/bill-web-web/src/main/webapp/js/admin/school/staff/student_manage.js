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
	$(".screenBar form").jqTransform();
	/**搜索*/
	$('.seach .sub').on('click',function(){
		$('#studentForm input[name="gradeId"]').val($('.screenBar .grade option:selected').val());
		$('#studentForm input[name="klassId"]').val($('.screenBar .klass option:selected').val());
		$('#studentForm input[name="name"]').val($('.seach input[name="stuName"]').val().trim());
		$('#studentForm').submit();
	 });
	$('.seach a').on('click',function(){
		var klassId = $('.screenBar .klass option:selected').val();
		var name = $('.seach input[name="stuName"]').val().trim();
		location.href= '/manage/school/staff/exportStudent.html?klassId='+klassId+'&name='+name;
	 });

	/**学生详细信息*/
	$('.peoTable a.a_alert_box').on('click',function(){
		$('.alert_box h2').nextAll().remove();
		var studentId = $(this).attr('value');
		$.ajax({
			url:'/manage/school/staff/getStudentDtail.html',
			type:"get",
			data:{"studentId":studentId},
			dataType:"json",
			success:function(data){
				if(data.success){
					//$('.alert_box').html(data.data.data);
					$(data.data.data).insertAfter('.alert_box h2');
					$('.alert_box').show();
				}
			},
			error:function(){
				//dialog.msg('请求失败', {time: 3000, icon:2});
			}
		});
	});
	$('.alert_box .closeAlert').on('click',function(){
		$('.alert_box').hide();
		$('.alert_box h2').nextAll().remove();
	});
	$(document).on("click",'.alert_box .box_top a',function(){
		var id=$(this).attr('value');
		if(''==id){
			return;
		}
		$.ajax({
			url:'/manage/school/staff/resetPass.html',
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data.data){
					dialog.msg('更新成功', {time: 3000, icon:1});
				}else{
				dialog.msg('请求失败,用户证件号错误', {time: 3000, icon:2});
				}
			},
			error:function(){
				dialog.msg('请求失败,用户证件号错误', {time: 3000, icon:2});
			}
		});
	});
})
	/**年级下拉*/
	function getKlass(val){
		$.ajax({
			url:'/manage/school/staff/getKlassList.html',
			type:"get",
			data:{"gradeId":val},
			dataType:"json",
			success:function(data){
				if(data.success){
					$('#studentForm .klass').html(data.data.data);
					fix_select('#studentForm .klass');
				}
			},
			error:function(){
				//dialog.msg('请求失败', {time: 3000, icon:2});
			}
		});
	};
	//下拉框渲染
	function fix_select(selector) { 
		var i=$(selector).parent().find('div,ul').remove().css('zIndex'); 
		$(selector).unwrap().removeClass('jqTransformHidden').jqTransSelect(); 
		$(selector).parent().css('zIndex', i); 
	} 