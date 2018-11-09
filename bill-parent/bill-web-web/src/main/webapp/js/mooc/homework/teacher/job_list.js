define(['../../js/lib/layer/layer'],function(dialog){
	//筛选作业
	function screen_homework(){
		$.ajax({
			url:"/homework/teaHomeworkList",
			type:"post",
			data:{
				"startDate":$("input[name='startDate']").val(),
				"endDate":$("input[name='endDate']").val(),
				"_index":$('#current').val(),
			},
			success:function(result){
				$("#jobListDiv").empty();
				var json = eval("("+result+")");
				$("#jobListDiv").append(json.data);

				$("a[name='delBtn']").click(function(){
					var hwId = $(this).attr("data-id");
					dialog.confirm("确认删除?",function(){
						$.ajax({
							url:"/homework/delete",
							type:"post",
							data:{"hwId":hwId},
							success:function(result){
								var json = eval("("+result+")");
								if (json.success) {
									dialog.msg("删除成功！",{time: 500, icon:1},function(){
										window.location.href="/homework/index.html";
									});
									
								}else{
									dialog.alert("删除失败！",{time: 2000, icon:1});
								}
							}
						})
					})
				});
				
				//分页
				$('.pageRight').click(function(){pageRight();});
				$('.right').click(function(){pageRight();});
				$('.pageLeft').click(function(){pageLeft();});
				$('.left').click(function(){pageLeft();});
				$('.numStyle').click(function(){
					var current = $(this).html();
					$('#current').val(current);
					screen_homework();
				})
			}
		})
	}

	//上一页
	function pageLeft(){
		if($('#current').val()!="1"){
			var current = parseInt($('#current').val())-1;
			$('#current').val(current);
			screen_homework();
		}
	}

	//下一页
	function pageRight(){
		var pageCount = $("#pageCount").val();
		if(pageCount!=$('#current').val()){
			var current = parseInt($('#current').val())+1;
			$('#current').val(current);
			screen_homework();
		}
	}

	return{
		screen_homework:screen_homework
	}
});