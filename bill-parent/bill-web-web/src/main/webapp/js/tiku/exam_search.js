define(function(){
	//筛选试题
	function screen_exam(){
		$.ajax({
			url:"/tiku/examList",
			type:"post",
			data:{
				"phaseId":$("#phaseId").val(),
				"subjectId":$("#subjectId").val(),
				"kId":$("#kId").val(),
				"typeId":$("#typeId").val()
			},
			datatype:"json",
			success:function(result){
				$(".course_center").remove();
				$(".course_right").remove();
				var json = eval("("+result+")");
				$(".course_left").after(json.data);
				
				$("#typeList li a").click(function(){
					 var typeId = $(this).attr("data-id");
					 changeType(typeId);
				});
				
				$("#typeList li").each(function(){
					 $(this).removeClass("li_click");
					 if($(this).find("a").attr("data-id")===$("#typeId").val()){
						 $(this).addClass("li_click");
					 }
				 });
			}
			
		})
		
	}
	
	//切换知识点
	function changeKnowledge(kId){
		$("#kId").val(kId);
		screen_exam();
	}
	
	//切换题型
	function changeType(typeId){
		$("#typeId").val(typeId);
		screen_exam();
	}
	
	return {
		screen_exam:screen_exam,
		changeKnowledeg:changeKnowledge,
		changeType:changeType
	}
	

})

