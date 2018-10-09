define(['../../js/lib/layer/layer'],function(dialog){

	var answeredQuestions = [];

	function paper_main(){
		$.ajax({
			url:"http://edu.bjhd.gov.cn/mooc/paper/teacher/transferPaper",
			type:"post",
			data:{"moocId":$("#moocId").val(),
				  "periodId":$("#periodId").val()
			},
			success:function(result){
				var json = eval("("+result+")");
				$(".exercisesLeft").empty();
				$(".exercisesLeft").append(json.data);

				 //查看解析
			    $('#tidetail>.analysis').find('h3').find('.look_analysis').on('click',function(){
			        $(this).parent().parent().find('.look_analysis_box').toggle();
			    });

			    //查看作答详情
			    $('#tidetail>.analysis').find('h3').find('span').find('.look_respond').on('click',function(){
			        $('.look_respond_box').toggle().find('h2').find('.close_box').on('click',function(){
			            $('.look_respond_box').css('display','none')
			        });
			        $("#examId").val($(this).attr("data-examId"));
			        var options = $(this).attr("data-options");
			        options = options.replace('[','');
			        options = options.replace(']','');
			        var optionArray = options.split(',');
			        var li = '';
			        for (var i = 0; i < optionArray.length; i++) {
			        	li += '<li data-value="'+optionArray[i].replace(' ','')+'"><span>'+optionArray[i]+'</span></li>'
			        };
			       $('#options').empty();
			       $('#options').append(li);
			        $(".atudent_name").empty();
			       

			        $(".look_respond_con").find("ul").find("li").on("click",function(){
			    	
				    	$("#options li").each(function(){
				    		$(this).removeClass("word_change");
				    	});
				    	$(this).addClass("word_change");
				    	$.ajax({
				    		url:"http://edu.bjhd.gov.cn/mooc/paper/teacher/getStudentListByOption",
				    		type:"post",
				    		data:{
				    			"paperId":$("#paperId").val(),
				    			"option":$(this).attr("data-value"),
				    			"examId":$("#examId").val()
				    		},
				    		success:function(data){
				    			$(".atudent_name").empty();
				    			var lis = "";
				    			for(var i=0;i<data.length;i++){
				    				lis = lis+'<span>'+data[i]+'</span>';
				    			}
				    			$(".atudent_name").append(lis);
				    		}
				    	});
				    });

			    });


			    

			    //去批阅
			    $(".go_read").click(function(){
			    	$.ajax({
			    		url:"/mooc/paper/teacher/readOver",
			    		type:"post",
			    		data:{"paperId":$("#paperId").val()},
			    		success:function(result){
			    			var json = eval("("+result+")");
			    			$(".exercisesLeft").empty();
							$(".exercisesLeft").append(json.data);

							  <!--教师主观题批阅-->
						    if($("._names").length>0){
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
			    })

			  
			   
			   
				<!--做作业-->
				$('.options input[type="radio"]').siblings('label').on('click',function(){
			        if(!$(this).siblings("input[type='radio']").is(':checked')){
			            $(this).addClass('asncheck').parent().siblings().find('input[type="radio"]').siblings('label').removeClass('asncheck');
			            $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings().find('input[type="radio"]').removeProp('checked');
			        }
			     });

				$('input[type="checkbox"]').siblings('label').on('click',function(){
			        if($(this).siblings("input[type='checkbox']").is(':checked')){
			          $(this).removeClass('asncheck');
			          $(this).siblings("input[type='checkbox']").removeProp('checked')
			        }
			        else{
			          $(this).addClass('asncheck');
			          $(this).siblings("input[type='checkbox']").prop('checked','checked')
			        }
			    });

				var questionTotal = $("#questionTotal").val();
				

				$('label').click(function(){
					addAnsweredQuestions($(this).prev());
					updateTooltip();
				})
				

				$("textarea").keyup(function(){
					var textareaValue =  $(this).val().replace(/(^\s*)|(\s*$)/g, "");
					if(textareaValue!=''){
						addAnsweredQuestions($(this));
					}else{
						var index = ifContains($(this));
						if(index!=-1){
							answeredQuestions.splice(index,1);
						}
						
					}
					updateTooltip();
				})
				
				$("#submitJob").click(function(){
					if($("#questionTotal").val()==answeredQuestions.length){
						var hwId = $("#hwId").val();
						var examIds = [];
						var typeIds = [];
						var answers = [];
						$("input[name='examIds']").each(function(index,hh){
							var answer = "";
							if($(hh).attr("data-type")==351){
								answer = $("input[name='answer_"+$(hh).val()+"']:checked").val();
							}else if($(hh).attr("data-type")==352||$(hh).attr("data-type")==353){
								$("input[name='answer_"+$(hh).val()+"']:checked").each(function(){
									answer = answer+$(this).val();
								});
							}else{
								answer = $("textarea[name='answer_"+$(hh).val()+"']").val();
							}
							examIds.push($(hh).val());
							typeIds.push($(hh).attr("data-type"));
							answers.push(answer);
						});
						
						 submit_job(examIds,typeIds,answers);
					}else{
						dialog.alert("尚未完成!");
					}
					
				})

				
			}
		})
	}





	//提交作业
	function submit_job(examIds,typeIds,answers){
		$.ajax({
			url:"http://edu.bjhd.gov.cn/mooc/paper/studentpaper/submitJob",
			type:"post",
			data:JSON.stringify({
				"paperId":$("#paperId").val(),
				"examId":examIds,
				"typeId":typeIds,
				"answer":answers
			}),
			datatype:"json",
			contentType:"application/json;charset=UTF-8",
			success:function(result){
				dialog.msg("提交成功！系统正在阅卷~",{time: 2000, icon:1},function(){
					var json = eval("("+result+")");
					$(".exercisesLeft").empty();
					$(".exercisesLeft").append(json.data);
					 //查看解析
				    $('#tidetail>.analysis').find('h3').find('.look_analysis').unbind('click').on('click',function(){
				        $(this).parent().parent().find('.look_analysis_box').toggle();
				    });
				});
			}
		})
	}



function loadAnswerDetails(){
	var studentId = $("input[name='studentId']:checked").val();
	var paperId = $("#paperId").val();
	if(studentId!=''&&studentId!=null){
		$.ajax({
			url:"http://edu.bjhd.gov.cn/mooc/paper/teacher/getReadOverList",
			type:"post",
			data:{
				"studentId":studentId,
				"paperId":paperId
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
	
}

function updateRightRate(rightRate,examId){

	$.ajax({
		url:"http://edu.bjhd.gov.cn/mooc/paper/teacher/updateRightRate",
		type:"post",
		data:{
			"paperId":$("#paperId").val(),
			"examId":examId,
			"rightRate":rightRate
		},
		success:function(result){
			$("#"+examId).empty();
			$("#"+examId).append(result+"%");
			
		}
	})
}

function updateTooltip(){
	var notAnsweredQuestionNum = $("#questionTotal").val()-answeredQuestions.length;
	var html = '<p>已答：'+answeredQuestions.length+'题</p><p>未答：'+notAnsweredQuestionNum+'题</p>';
	$(".judge").empty();
	$(".judge").append(html);
}

//对已答题数组进行增加
function addAnsweredQuestions(obj){
	var domName = $(obj).attr("name");
	if(ifCancelCheckbox(obj)){
		var flag = false;
		for(var i=0;i<answeredQuestions.length;i++){
			if(answeredQuestions[i]==domName){
				flag = true;
			}
		}
		if(flag==false){
			answeredQuestions.push(domName);
		}
	}else{
		for(var i=0;i<answeredQuestions.length;i++){
			if(answeredQuestions[i]==domName){
				answeredQuestions.splice(i,1);
			}
		}
	}
	
}

//判断选择的是不是checkbox,如果是checkbox,当前是否被选中
function ifCancelCheckbox(obj){
	var domName = $(obj).attr("name");
	var type = $(obj).attr('type');
	if(type=='checkbox'){
		var length = $('input[name="'+domName+'"]:checked').length;
		if(length==0){
			return false;
		}
	}
	return true;
}

//判断已答题数组里是否有当前题,返回下标
function ifContains(obj){
	var domName = $(obj).attr("name");
	for(var i=0;i<answeredQuestions.length;i++){
		if(answeredQuestions[i]==domName){
			return i;
		}
	}
	return -1;
}


	

	return{
		paper_main:paper_main,
		submit_job:submit_job,
		loadAnswerDetails:loadAnswerDetails,
		updateRightRate:updateRightRate,
	}
});