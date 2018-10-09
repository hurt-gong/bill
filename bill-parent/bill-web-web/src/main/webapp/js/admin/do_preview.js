require.config({　　　　
	paths: {　　　　　　
		"headerSlideDown": "../common/headerslideDown",
		"dialog": "../../js/lib/layer/layer",　
	}　　
});


require(['headerSlideDown'], function(aaaa) {
});

require(['dialog'], function(dialog) {

	$('.exeCheck').on('click',function(){
	    if($(this).siblings("input[type='checkbox']").is(':checked')){
	      $(this).removeClass('cur');
	      $(this).siblings("input[type='checkbox']").removeProp('checked')
	    }
	    else{
	      $(this).addClass('cur');
	      $(this).siblings("input[type='checkbox']").prop('checked','checked')
	    }
	});
	$('.pre_con>.floorone').find('.flooright').find('.check_jiexi').on('click',function(){
	        $(this).parent().parent().find('.floor_jiexi').toggle();
	    });

	$(".datetimepicker").datetimepicker({lang:'ch'}); 

	$('.pre_con>.floorone').find('.flooright').find('.aa').click(function(){
		$.ajax({
			url:"http://edu.bjhd.gov.cn/tiku/cancelSelect",
			type:"post",
			data:{"examId":$(this).attr("data-examid"),
				  "typeId":$(this).attr("data-typeid"),
				  "typeName":$(this).attr("data-typename"),
			},
			success:function(result){
				
			}

		})
		$(this).parent().parent().remove();
		$("#preview_total").html($("#preview_total").html()-1)
		$(".exam_index").each(function(index){
			$(this).html(index+1+".");
		})
	})


	$('.pre_con>.floorone').find('.flooright').find('.bb').click(function(){
		$.ajax({
			url:"http://edu.bjhd.gov.cn/mooc/paper/teacher/cancelSelect",
			type:"post",
			data:{"examId":$(this).attr("data-examid"),
				  "typeId":$(this).attr("data-typeid"),
				  "typeName":$(this).attr("data-typename"),
			},
			success:function(result){
				
			}

		})
		$(this).parent().parent().remove();
		$("#preview_total").html($("#preview_total").html()-1);
		$(".exam_index").each(function(index){
			$(this).html(index+1+".");
		})
		
	})




	$('#publishjob').click(function(){
		var examIds = [];
		$('.pre_con>.floorone').find('.flooright').find('.aa').each(function(){
			if($(this).html()=="取消选择"){
				examIds.push($(this).attr("data-examid"));
			}
		})
		if(examIds.length>0){
			$('.publishjob_box').css('display','block').find('.publishjob_exit').on('click',function(){
				$('.publishjob_box').css('display','none')
			});
			$('.selectgrade').find('input[type="checkbox"]').siblings('label').on('click',function(){
				if($(this).siblings("input[type='checkbox']").is(':checked')){
				  $(this).removeClass('greade_checked');
				  $(this).siblings("input[type='checkbox']").removeProp('checked')
				}
				else{
				  $(this).addClass('greade_checked');
				  $(this).siblings("input[type='checkbox']").prop('checked','checked')
				}
			});
		}else{
			dialog.alert("未选择习题")
		}
		
	})

	// 发布作业
	$(".publishjob_sure").click(function(){
		var examIds = [];
		$('.pre_con>.floorone').find('.flooright').find('.aa').each(function(){
			if($(this).html()=="取消选择"){
				examIds.push($(this).attr("data-examid"));
			}
		})
		var classIds = [];
		$("input[name='classId']:checked").each(function(){
			classIds.push($(this).val());
		})
		 var title = $("#title").val();
		 var startDate = $("#startDate").val();
		 var endDate = $("#endDate").val();
		 if(examIds.length>0&&classIds.length>0&&title!=''&&startDate!=''&&endDate!=''){
		 	$.ajax({
				url:"http://edu.bjhd.gov.cn/homework/releaseHomework",
				data:JSON.stringify({
					"examId":examIds,
					"classId":classIds,
					"title":title,
					"startDate":startDate,
					"endDate":endDate,
					"subjectId":$("#subjectId").val(),
					"phaseId":$("#phaseId").val()
					}),
				type:"post",
				datatype:"json",
				contentType:"application/json;charset=UTF-8",
				success:function(data){
					var json = eval("("+data+")");
					if(json.success){
						$(".publishjob_box").hide();
						layer.msg("发布成功！正在跳转详情页~",{time: 2000, icon:1},function(){
							window.location.href="http://edu.bjhd.gov.cn/homework/teacher/answerDetail?hwId="+json.hwId;
						})
					}else{
						layer.alert(json.errorMsg);
					}
				},
				error:function(){
					layer.msg("发布失败！",{time: 2000, icon:1})
				}
			})
		 }else{
		 	dialog.alert("请填写所有必填项！")
		 }
		
		
	});

	$("#publishjobForPaper").click(function(){
		var examIds = [];
		$('.pre_con>.floorone').find('.flooright').find('.bb').each(function(){
			if($(this).html()=="取消选择"){
				examIds.push($(this).attr("data-examid"));
			}
		})
		var moocId = $("#moocId").val();
		var periodId = $("#periodId").val();
		var listenUrl = $("#listenUrl").val();

		if(examIds.length>0){
			$.ajax({
				url:"http://edu.bjhd.gov.cn/mooc/paper/teacher/releasePaper",
				data:JSON.stringify({
					"examId":examIds,
					"moocId":$("#moocId").val(),
					"periodId":$("#periodId").val(),
					}),
				type:"post",
				datatype:"json",
				contentType:"application/json;charset=UTF-8",
				success:function(result){
					var json = eval("("+result+")");
					if(json.success){
						dialog.msg("发布成功！正在跳转详情页~",{time: 2000, icon:1},function(){
							window.location.href="http://edu.bjhd.gov.cn/mooc/course/details?id="+moocId+"&videoId="+periodId+"&listenUrl="+listenUrl;
						});
						 
					}else{
						dialog.alert("发布失败！");
					}
				}
			})
		}else{
			dialog.alert("未选择习题！")
		}
		
		
		
	})
});



