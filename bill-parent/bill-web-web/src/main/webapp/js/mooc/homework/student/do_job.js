define(['../../js/lib/layer/layer'],function(dialog){
	//提交作业
	function submit_job(examIds,typeIds,answers){
		$.ajax({
			url:"/homework/submitJob",
			type:"post",
			data:JSON.stringify({
				"hwId":$("#hwId").val(),
				"examId":examIds,
				"typeId":typeIds,
				"answer":answers
			}),
			datatype:"json",
			contentType:"application/json;charset=UTF-8",
			success:function(hwId){
				dialog.msg("提交成功！请等待发答案~",{time: 2000, icon:1},function(){
					window.location.href="/homework/index.html";
				});
				
			}
		})
	}

	

	return{
		submit_job:submit_job
	}
});