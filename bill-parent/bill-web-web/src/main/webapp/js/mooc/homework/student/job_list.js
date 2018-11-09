screen_homework();

$('.navClass li').on('click',function(){
 	$(this).addClass('nav_li').siblings().removeClass('nav_li');
 	$("#subjectId").val($(this).attr("data-id"));
 	screen_homework();
 });

//筛选作业
function screen_homework(){
	$.ajax({
		url:"/homework/stuHomeworkList",
		type:"post",
		data:{
			"subjectId":$("#subjectId").val(),
			"_index":$('#current').val(),
		},
		success:function(result){
			$("#jobListDiv_stu").empty();
			var json = eval("("+result+")");
			$("#jobListDiv_stu").append(json.data);

			$('a[name="answerDetail"]').click(function(){
				var hwId = $(this).attr('data-hwid');
				var endDate = new Date($(this).attr('data-enddate')).getTime();
				var now = new Date().getTime();
				if(now>endDate){
					window.location.href="/homework/student/answerDetail?hwId="+hwId;
				}else{
					layer.alert("请等待发答案~");
				}
			})

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
