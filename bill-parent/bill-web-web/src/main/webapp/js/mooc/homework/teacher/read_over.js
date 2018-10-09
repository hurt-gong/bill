
$(".class_list").children().first().addClass("a1");
loadStudents();
$(".class_list").find("a").click(function(){
	$(".class_list").find("a").each(function(){
		$(this).removeClass("a1");
	});
	$(this).addClass("a1");
	loadStudents();
})



function loadStudents(){
	$.ajax({
		url:"http://edu.bjhd.gov.cn/homework/getStudentListByKlass",
		type:"post",
		data:{
			"hwId":$("#hwId").val(),
			"klassId":$(".class_list").find(".a1").attr("data-id")
		},
		success:function(data){
			$("._names").empty();
			$("#read_over_list").empty();
			if(data.length==0){
				$("._names").append('<p style="">该班级暂无学生作答</p>');
			}else{
				var html = "";
				for(var i=0;i<data.length;i++){
					html = html + '<span><label for="'+data[i].userId+'"></label><input type="radio" name="studentId" value="'+data[i].userId+'">'+data[i].userName+'</span>'
				}
				$("._names").append(html);

				var label = $("._names").children().first().find("label");
				$(label).addClass("label_checked");
				$(label).siblings("input[type='radio']").prop('checked','checked').parent().siblings().find('input[type="radio"]').removeProp('checked');
		
				loadAnswerDetails();
				

				$("._names").find("span").find("label").click(function(){
			       
					if(!$(this).siblings("input[type='radio']").is(':checked')){
				        $(this).addClass('label_checked').parent().siblings().find('input[type="radio"]').siblings('label').removeClass('label_checked');
				        $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings().find('input[type="radio"]').removeProp('checked');
				    }
			        loadAnswerDetails();
				})

			}
				
		}
	})
}

function loadAnswerDetails(){
	var studentId = $("input[name='studentId']:checked").val();
	var hwId = $("#hwId").val();
	$.ajax({
		url:"http://edu.bjhd.gov.cn/homework/getReadOverList",
		type:"post",
		data:{
			"studentId":studentId,
			"hwId":hwId
		},
		success:function(result){
			$("#read_over_list").empty();
			var json = eval("("+result+")");
			$("#read_over_list").append(json.data);
			//查看解析
			$('#tidetail>.analysis').find('h3').find('.look_analysis').on('click',function(){
			    $(this).parent().parent().find('.ana').toggle();
			});

			$("a[name='updateRightRate']").click(function(){
				var examId = $(this).attr("data-examId");
				var rightRate = $(this).parent().find("input").val();
				if(rightRate!=''){
					updateRightRate(rightRate,examId);
				}
			})
		}
	})
}

function updateRightRate(rightRate,examId){

	$.ajax({
		url:"http://edu.bjhd.gov.cn/homework/updateRightRate",
		type:"post",
		data:{
			"hwId":$("#hwId").val(),
			"examId":examId,
			"rightRate":rightRate
		},
		success:function(result){
			$("#"+examId).empty();
			$("#"+examId).append(result+"%");
			
		}
	})
}

