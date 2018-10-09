
	//全局变量，用map存放已选择的试题
var examMap = {};//试题id和题型id的对象
var typeIdName = {};//保存题型id和name的关系
var typeMap = {};//题型id和试题数的对象

$('input[name="selectedExam"]').each(function(){
	var examId = $(this).attr('data-examid');
	var typeId = $(this).attr('data-typeid');
	var typeName = $(this).attr('data-typename');
	
	examMap[examId]=typeId;
	if(typeName==''){
		typeName="未知题型"
	}
	typeIdName[typeId]=typeName;
	if(typeMap.hasOwnProperty(typeId)){
		typeMap[typeId]=typeMap[typeId]+1;
	}else{
		typeMap[typeId]=1;
	}
	loadSelInfo();
})



//添加选项从E开始
var option_index=69;
var option_index_multi=69;
var small_question_index=4;

//页面加载时加载该学科学段下所有试题
screen_exam();
 
$(".datetimepicker").datetimepicker({lang:'ch'});


 

bindingPhaseClick();
bindingGradeClick();
bindingSubjectClick();
bindingKnowledgeClick();

 
// 发布作业
$(".publishjob_sure").unbind('click').click(function(){
	 var examIds = [];
	 for(examId in examMap){     //取出对象的所有属性名，放到数组里
		 examIds.push(examId); 
	 }
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
				console.info(json.success)
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
	 	layer.alert("请填写所有必填项！")
	 }
	
	
});

//阅读题添加小题
$(".addqus").click(function(){
	var dl = '<dl>'
                +'<dt>'
                    +'<h4>'
							+'问题'+small_question_index+'：'
                    +'</h4>'
					+'<div>'
                    +'<span><input type="radio" name="'+small_question_index+'" checked="checked" value="1"><label for="1" class="tigan_class labelChecked"></label><b class="se1">单选</b></span>'
                    +'<span><input type="radio" name="'+small_question_index+'" value="2"><label for="2" class="tigan_class"></label><b class="se2">多选</b></span>'
                    +'<span><input type="radio" name="'+small_question_index+'" value="3"><label for="3" class="tigan_class"></label><b class="se3">文本</b></span>'
                    +'</div>'
                +'</dt>'
				+'<dd class="tigan_dan">'
                    +'<div class="read_dan">' 
                        +'<h5>答案：</h5>'
                        +'<span><input type="radio"  name="radio_'+small_question_index+'" checked="checked" value="A"><label for="A" class="asncheck"></label><b>A</b></span>'
                        +'<span><input type="radio"  name="radio_'+small_question_index+'" value="B"><label for="B"></label><b>B</b></span>'
                        +'<span><input type="radio"  name="radio_'+small_question_index+'" value="C"><label for="C"></label><b>C</b></span>'
                        +'<span><input type="radio"  name="radio_'+small_question_index+'" value="D"><label for="D"></label><b>D</b></span>'
                        +'<a href="javascript:;" class="add_change small">添加选项</a>'
                    +'</div>'
                +'</dd>'
                +'<dd class="tigan_duo">'
                    +'<div  class="read_duo">'
                        +'<h5>答案：</h5>'
                        +'<span><input type="checkbox"  name="checkbox_'+small_question_index+'" value="A"><label for="A"></label><b>A</b></span>'
                        +'<span><input type="checkbox" name="checkbox_'+small_question_index+'" value="B"><label for="B"></label><b>B</b></span>'
                        +'<span><input type="checkbox" name="checkbox_'+small_question_index+'" value="C"><label for="C"></label><b>C</b></span>'
                        +'<span><input type="checkbox" name="checkbox_'+small_question_index+'" value="D"><label for="D"></label><b>D</b></span>'
                        +'<a href="javascript:;" class="add_change small">添加选项</a>'
                    +'</div>'
                +'</dd>'
                +'<dd class="tigan_txt">'
                        +'<h5>答案：</h5>'
                        +'<textarea  name="textarea_'+small_question_index+'">李白（701年-762年），字太白，号青莲居士，又号“谪仙人”，汉族，绵州昌隆县（今四川省江油市）人，是唐代伟大的浪漫主义诗人，被后人誉为“诗仙”。</textarea>'
                +'</dd>'
            +'</dl>';
    $(".addqus").before(dl);
    small_question_index++;

  $('.read .tigan').find('.tigan_class').unbind('click').on('click',function(){
        if($(this).siblings("input[type='radio']").is(':checked')){
          if($(this).siblings('b').hasClass('se1')){
            $(this).parent().parent().parent().siblings('.tigan_dan').css('display','none');
          }else if($(this).siblings('b').hasClass('se2')){
            $(this).parent().parent().parent().siblings('.tigan_duo').css('display','none');
            }else if($(this).siblings('b').hasClass('se3')){
                $(this).parent().parent().parent().siblings('.tigan_txt').css('display','none');
            }
        }
        else{
          $(this).addClass('labelChecked').parent().siblings('span').find('label').removeClass('labelChecked');
          $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings('span').find("input[type='radio']").removeProp('checked');
          
          if($(this).siblings('b').hasClass('se1')){
            $(this).parent().parent().parent().siblings('.tigan_dan').css('display','block').siblings('dd').css('display','none');
          }else if($(this).siblings('b').hasClass('se2')){
            $(this).parent().parent().parent().siblings('.tigan_duo').css('display','block').siblings('dd').css('display','none');
            }else if($(this).siblings('b').hasClass('se3')){
                $(this).parent().parent().parent().siblings('.tigan_txt').css('display','block').siblings('dd').css('display','none');
            }
        }
        
    })

	$('.tigan_dan').find('input[type="radio"]').siblings('label').unbind('click').on('click',function(){
	    if($(this).siblings("input[type='radio']").is(':checked')){
	    }else{
	        $(this).addClass('asncheck').parent().siblings().find('input[type="radio"]').siblings('label').removeClass('asncheck');
	        $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings().find('input[type="radio"]').removeProp('checked');
	    }
	});
	$('.read_duo').find('input[type="checkbox"]').siblings('label').unbind('click').on('click',function(){
        if($(this).siblings("input[type='checkbox']").is(':checked')){
          $(this).removeClass('asncheck');
          $(this).siblings("input[type='checkbox']").removeProp('checked')
        }
        else{
          $(this).addClass('asncheck');
          $(this).siblings("input[type='checkbox']").prop('checked','checked')
        }
    });
    $('.small').unbind('click').click(function(){
		var obj = $(this).parent().find('span').last().find('input');
		var next_index = String.fromCharCode($(obj).val().charCodeAt()+1);
		var type = $(obj).attr('type');
		var name = $(obj).attr('name');
		var html = '<span><input type="'+type+'" name="'+name+'" value="'+next_index+'"><label for="'+next_index+'"></label><b>'+next_index+'</b></span>';
		$(this).before(html);
		$('.tigan_dan').find('input[type="radio"]').siblings('label').unbind('click').on('click',function(){
		    if($(this).siblings("input[type='radio']").is(':checked')){
		    }else{
		        $(this).addClass('asncheck').parent().siblings().find('input[type="radio"]').siblings('label').removeClass('asncheck');
		        $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings().find('input[type="radio"]').removeProp('checked');
		    }
		});
		$('.read_duo').find('input[type="checkbox"]').siblings('label').unbind('click').on('click',function(){
	        if($(this).siblings("input[type='checkbox']").is(':checked')){
	          $(this).removeClass('asncheck');
	          $(this).siblings("input[type='checkbox']").removeProp('checked')
	        }

	        else{
	          $(this).addClass('asncheck');
	          $(this).siblings("input[type='checkbox']").prop('checked','checked')
	        }
	    });

})

})

//添加选项(单选)
$("#single_add_option").click(function(){
	if(option_index<71){
		var index = String.fromCharCode(option_index);
		var cur_index = option_index-64;
		$("#single_options").append('<b>'+index+'、<input type="text" name="option'+cur_index+'" style="width:300px"/><a href="javascript:;" class="del_option singleOption">删除</a><br/><br/></b>')
		$("#single_answers").find("span:last").after('<span><input type="radio" name="single_answer" value="'+index+'"><label for="'+index+'"></label>'+index+'</span>');
		
		$('.singleOption').unbind('click').on('click',function(){
			var inputName = $(this).parent().find('input').attr('name');
			var index = inputName.charAt(6);
			if(index==5){
				if($('input[name="option6"]').length>0){
					$('input[name="option6"]').parent().remove();
				}else{
					$(this).parent().remove();
				}
			}else{
				$(this).parent().remove();
			}
			
			$('input[name="single_answer"]').last().parent().remove();
			option_index--;
		});

		$('.solution').find('input[type="radio"]').siblings('label').unbind('click').on('click',function(){
		    if($(this).siblings("input[type='radio']").is(':checked')){
		    }else{
		        $(this).addClass('asncheck').parent().siblings().find('input[type="radio"]').siblings('label').removeClass('asncheck');
		        $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings().find('input[type="radio"]').removeProp('checked');
		    }
		});
		option_index++;
	}else{
		layer.alert("最多6个选项！");
	}
	
});

//添加选项(多选)
$("#multi_add_option").click(function(){
	if(option_index_multi<71){
		var index = String.fromCharCode(option_index_multi);
		var cur_index = option_index_multi-64;
		$("#multi_options").append('<b>'+index+'、<input type="text" name="multi_option'+cur_index+'" style="width:300px"/><a href="javascript:;" class="del_option multiOption">删除</a><br/><br/></b>');
		$("#multi_answers").find("span:last").after('<span><input type="checkbox" name="multi_answer" value="'+index+'"><label for="'+index+'"></label>'+index+'</span>');
		
		$('.multiOption').unbind('click').on('click',function(){
			var inputName = $(this).parent().find('input').attr('name');
			var index = inputName.charAt(12);
			if(index==5){
				if($('input[name="multi_option6"]').length>0){
					$('input[name="multi_option6"]').parent().remove();
				}else{
					$(this).parent().remove();
				}
			}else{
				$(this).parent().remove();
			}
			
			$('input[name="multi_answer"]').last().parent().remove();
			option_index_multi--;
		})
		$('.solution').find('input[type="checkbox"]').siblings('label').unbind('click').on('click',function(){
	        if($(this).siblings("input[type='checkbox']").is(':checked')){
	          $(this).removeClass('asncheck');
	          $(this).siblings("input[type='checkbox']").removeProp('checked')
	        }
	        else{
	          $(this).addClass('asncheck');
	          $(this).siblings("input[type='checkbox']").prop('checked','checked')
	        }
	    });
		option_index_multi++;
	}else{
		layer.alert("最多6个选项！");
	}
	
	
});

$('.small').click(function(){
	var obj = $(this).parent().find('span').last().find('input');
	var next_index = String.fromCharCode($(obj).val().charCodeAt()+1);
	var type = $(obj).attr('type');
	var name = $(obj).attr('name');
	var html = '<span><input type="'+type+'" name="'+name+'" value="'+next_index+'"><label for="'+next_index+'"></label><b>'+next_index+'</b></span>';
	$(this).before(html);
	$('.tigan_dan').find('input[type="radio"]').siblings('label').unbind('click');
	$('.tigan_dan').find('input[type="radio"]').siblings('label').on('click',function(){
	    if($(this).siblings("input[type='radio']").is(':checked')){
	    }else{
	        $(this).addClass('asncheck').parent().siblings().find('input[type="radio"]').siblings('label').removeClass('asncheck');
	        $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings().find('input[type="radio"]').removeProp('checked');
	    }
	});
	$('.read_duo').find('input[type="checkbox"]').siblings('label').unbind('click');
	$('.read_duo').find('input[type="checkbox"]').siblings('label').on('click',function(){
        if($(this).siblings("input[type='checkbox']").is(':checked')){
          $(this).removeClass('asncheck');
          $(this).siblings("input[type='checkbox']").removeProp('checked')
        }
        else{
          $(this).addClass('asncheck');
          $(this).siblings("input[type='checkbox']").prop('checked','checked')
        }
    });

})

//加载富文本编辑器
KindEditor.ready(function(K){
	var editor=K.create("textarea[name='content']",{
		width : '700px',
		themeType : 'simple',
		items:['preview','|','cut','copy','paste','plainpaste','|','justifycenter','justifyleft','justifyright','justifyfull','|',
				'insertorderedlist','insertunorderedlist','formatblock','fontname','fontsize','forecolor','hilitecolor','bold','italic','underline','|',
				'image','subscript','superscript','table','lineheight'],
		afterCreate : function() { 
        	this.sync(); 
        }, 
        afterBlur:function(){ 
            this.sync(); 
        } ,
        uploadJson:"http://edu.bjhd.gov.cn/tiku/uploadImage" ,
        
	});
   
});


//上传单选题
$("#single_upload").click(function(){
	var content = $("#single_content").val();
	if($("#phaseId").val()==53){
		var index = 64;
		for (var i = 1; i <=6; i++) {
			var option = $("input[name='option"+i+"']").val();
			if(option!=undefined&&option!=''){
				content=content+"<p>"+String.fromCharCode(index+i)+"、"+option+"</p>";
			}
		};
	}
	var answer = $("input[name='single_answer']:checked").val();
	var analysis = $("#single_analysis").val();
	var data = {"phaseId":$("#phaseId").val(),
			  "gradeId":$("#gradeId").val(),
			  "subjectId":$("#subjectId").val(),
			  "typeId":$("#new_type_id").val(),
			  "content":content,
			  "answer":answer,
			  "analysis":analysis,
			  "optionCount":$("input[name='single_answer']").length,
			  "option1":$("input[name='option1']").val(),
			  "option2":$("input[name='option2']").val(),
			  "option3":$("input[name='option3']").val(),
			  "option4":$("input[name='option4']").val(),
			  "option5":$("input[name='option5']").val(),
			  "option6":$("input[name='option6']").val(),
			  "optionCount":$("#single_options input").length,
			  "typeName":$("#new_type_id").attr("data-type_name"),
			};
	uploadExam(data);
});

//上传多选题
$("#multi_upload").click(function(){
	var content = $("#multi_content").val();
	if($("#phaseId").val()==53){
		var index = 64;
		for (var i = 1; i <=6; i++) {
			var option = $("input[name='multi_option"+i+"']").val();
			if(option!=undefined&&option!=''){
				content=content+"<p>"+String.fromCharCode(index+i)+"、"+option+"</p>";
			}
		};
	}
	var answer = "";
	$("input[name='multi_answer']:checked").each(function(){
		answer=answer+$(this).val();
	});
	var analysis = $("#multi_analysis").val();
	var data = {"phaseId":$("#phaseId").val(),
			  "gradeId":$("#gradeId").val(),
			  "subjectId":$("#subjectId").val(),
			  "typeId":$("#new_type_id").val(),
			  "content":content,
			  "answer":answer,
			  "analysis":analysis,
			  "optionCount":$("input[name='multi_answer']").length,
			  "option1":$("input[name='multi_option1']").val(),
			  "option2":$("input[name='multi_option2']").val(),
			  "option3":$("input[name='multi_option3']").val(),
			  "option4":$("input[name='multi_option4']").val(),
			  "option5":$("input[name='multi_option5']").val(),
			  "option6":$("input[name='multi_option6']").val(),
			  "optionCount":$("#multi_options input").length,
			  "typeName":$("#new_type_id").attr("data-type_name"),
			};
	 uploadExam(data);
});

//上传填空题
$("#fill_in_upload").click(function(){
	var content = $("#fill_in_content").val();
	var answer = $("#fill_in_answer").val();
	var analysis = $("#fill_in_analysis").val();
	var data = {
			"phaseId":$("#phaseId").val(),
			"gradeId":$("#gradeId").val(),
			"subjectId":$("#subjectId").val(),
			"typeId":$("#new_type_id").val(),
			"content":content,
			"answer":answer,
			"analysis":analysis,
			"typeName":$("#new_type_id").attr("data-type_name"),
			};
	uploadExam(data);
});

//上传写作题
$("#writing_upload").click(function(){
	var content = $("#writing_content").val();
	var data = {
		"phaseId":$("#phaseId").val(),
		"gradeId":$("#gradeId").val(),
		"subjectId":$("#subjectId").val(),
		"typeId":$("#new_type_id").val(),
		"content":content,
		"answer":"略",
		"analysis":"略",
		"typeName":$("#new_type_id").attr("data-type_name"),
	};
	uploadExam(data);

});

//上传阅读题
$("#read_upload").click(function(){
	var content = $("#read_content").val();

	var answer = "";
	//TODO 拼接各个小题答案
	for(var i=1;i<small_question_index;i++){
		var smallAnswer = i+".";
		var type = $("input[name='"+i+"']:checked").val();
		if(type=="1"){
			smallAnswer = smallAnswer+$("input[name='radio_"+i+"']:checked").val();
		}else if(type=="2"){
			var temp = "";
			$("input[name='checkbox_"+i+"']:checked").each(function(){
				temp = temp+$(this).val();
			});
			smallAnswer = smallAnswer+temp;
		}else{
			smallAnswer = smallAnswer +$("textarea[name='textarea_"+i+"'").val();
		}
		answer = answer+smallAnswer+"    ";
	}
	var analysis = $("#read_analysis").val();
	var data = {
		"phaseId":$("#phaseId").val(),
		"gradeId":$("#gradeId").val(),
		"subjectId":$("#subjectId").val(),
		"typeId":$("#new_type_id").val(),
		"content":content,
		"answer":answer,
		"analysis":analysis,
		"typeName":$("#new_type_id").attr("data-type_name"),
	};
	uploadExam(data);
});


//ajax筛选试题
function screen_exam(){
	$.ajax({
		url:"http://edu.bjhd.gov.cn/tiku/examList",
		type:"post",
		data:{
			"phaseId":$("#phaseId").val(),
			"gradeId":$("#gradeId").val(),
			"subjectId":$("#subjectId").val(),
			"kId":$("#kId").val(),
			"typeId":$("#typeId").val(),
			"_index":$('#current').val(),
		},
		datatype:"json",
		success:function(result){
			$(".course_center").remove();
			$(".course_right").remove();
			var json = eval("("+result+")");
			$(".course_left").after(json.data);
			
			//绑定题型click事件，触发试题的重新加载
			$("#typeList li a").click(function(){
				 var typeId = $(this).attr("data-id");
				 $("#typeId").val(typeId);
				 screen_exam();
			});
			
			$("#typeList li").each(function(){
				 $(this).removeClass("li_click");
				 if($(this).find("a").attr("data-id")===$("#typeId").val()){
					 $(this).addClass("li_click");
				 }
			 });
			 
			 //判断试题是否被选中，以显示“选择该题”或“取消选择”
			 $("a[name='selectExam']").each(function(){
				var examId = $(this).attr("data-id");
				var typeId = $(this).attr("data-typeId");
				
				if(examMap.hasOwnProperty(examId)){
					$(this).text("取消选择");
				}else{
					$(this).text("选择该题");
				}
			 });
			 
			 
			 //给选择试题链接绑定事件
			$("a[name='selectExam']").click(function(){
				var examId = $(this).attr("data-id");
				var typeId = $(this).attr("data-typeId");
				var typeName = $(this).attr("data-typeName");
				changeSelState($(this),examId,typeId,typeName);
			});
			
			loadSelInfo();
			
			 //查看解析
			$('#tidetail>.floorone').find('.flooright').find('.check_jiexi').unbind('click').on('click',function(){
				$(this).parent().parent().find('.floor_jiexi').toggle();
			});
			
			//预览编辑
			$('.course_right').find('p>button').eq(0).unbind('click').on('click',function(){
				var total = 0;
				for(typeId in typeMap){
					if(typeMap[typeId]!=null){
						total=total+typeMap[typeId];
					}
				}
				if(total>0){
					 var url = "http://edu.bjhd.gov.cn/tiku/previewExam?";
					 for(examId in examMap){  
					 	if(examMap[examId]!=null){
					 		 url=url+"examId="+examId+"&";
					 		} 
					 }
					 window.location.href=url+"subjectId="+$('#subjectId').val()+"&phaseId="+$('#phaseId').val();
					}else{
						layer.alert("未选择习题！");
					}
				
			});

			 //发布习题
		    $('.course_right').find('p>button').eq(1).on('click',function(){
		        $('.publishjob_box').css('display','block').find('.publishjob_exit').on('click',function(){
		            $('.publishjob_box').css('display','none')
		        });

		    $('.selectgrade').find('input[type="checkbox"]').siblings('label').unbind('click').on('click',function(){
		        if($(this).siblings("input[type='checkbox']").is(':checked')){
		          $(this).removeClass('greade_checked');
		          $(this).siblings("input[type='checkbox']").removeProp('checked')
		        }
		        else{
		          $(this).addClass('greade_checked');
		          $(this).siblings("input[type='checkbox']").prop('checked','checked')
		        }
		    });
		        

    })
			
		
			//分页
			$('.pageRight').click(function(){pageRight();});
			$('.right').click(function(){pageRight();});
			$('.pageLeft').click(function(){pageLeft();});
			$('.left').click(function(){pageLeft();});
			$('.numStyle').click(function(){
				var current = $(this).html();
				$('#current').val(current);
				screen_exam();
			})

			if($('a[name="upload_div"]').attr('class')=='changeA_style'){
				$(".con2_left").after($(".course_right"));
			}
		}
		
	})
	
}
	

//改变试题的选中状态
function changeSelState(obj,examId,typeId,typeName){
	
	if(examMap.hasOwnProperty(examId)&&examMap[examId]!=null){//取消选择
		
		examMap[examId]=null;
		typeMap[typeId]=typeMap[typeId]-1;
		if(typeMap[typeId]<=0){
			typeMap[typeId]=null;
		}
		obj.text("选择该题");
		
	}else{
		examMap[examId]=typeId;
		if(typeName==''){
			typeName="未知题型";
		}
		typeIdName[typeId]=typeName;
		if(typeMap.hasOwnProperty(typeId)&&typeMap[typeId]!=null){
			typeMap[typeId]=typeMap[typeId]+1;
		}else{
			typeMap[typeId]=1;
		}
		obj.text("取消选择");
	}
	loadSelInfo();
}

//为“已上传题目”填充数据
function loadSelInfo(){
	$("#selectedInfo").empty();
	var total = 0;
	var str = "";
	for(typeId in typeMap){
		if(typeMap[typeId]!=null){
			total=total+typeMap[typeId];
			str = str+"<li>"+typeIdName[typeId]+"："+typeMap[typeId]+"</li>";
		}
	}
	var lis = "<li>共"+total+"题，其中</li>"+str;
	$("#selectedInfo").append(lis);
}

//根据学段重新加载年级
function loadGrade(phaseId){
	$.ajax({
		url:"http://edu.bjhd.gov.cn/tiku/getGradeList",
		data:{"phaseId":phaseId},
		type:"post",
		datatype:"json",
		success:function(data){
			//TODO 重新加载年级列表
			var html = "";
			for(var i=0;i<data.length;i++){
				html = html+'<a href="javascript:;" data-id="'+data[i].id+'">'+data[i].name+'</a>';
			}
			$("#gradeListDiv p a").remove();
			$("#gradeListDiv p").append(html);
			$("#gradeId").val("");
			bindingGradeClick();
 
		}
	})
}

//根据年级重新加载学科
function loadSubject(gradeId){
	$.ajax({
		url:"http://edu.bjhd.gov.cn/tiku/getSubjectList",
		data:{"gradeId":gradeId},
		type:"post",
		datatype:"json",
		success:function(data){
			//TODO 重新加载学科列表
			var html = "";
			for(var i=0;i<data.length;i++){
				html = html+'<a href="javascript:;" data-id="'+data[i].id+'">'+data[i].name+'</a>';
			}
			$("#subjectListDiv p a").remove();
			$("#subjectListDiv p").append(html);
			$("#subjectId").val("");
			bindingSubjectClick();
		}
	})
}

function loadKnowledge(){
	$.ajax({
		url:"http://edu.bjhd.gov.cn/tiku/getKnowledgeList",
		data:{
			"phaseId":$("#phaseId").val(),
			"subjectId":$("#subjectId").val(),
			"gradeId":$("#gradeId").val(),
		},
		type:"post",
		datatype:"json",
		success:function(data){
			//TODO 重新加载知识点列表
			var html = "";
			for(var i=0;i<data.length;i++){
				html = html+'<li><a href="javascript:;" data-id="'+data[i].id+'">'+data[i].name+'</a></li>';
			}
			$("#knowledgeList").empty();
			$("#knowledgeList").append(html);
			$("#kId").val("");
			bindingKnowledgeClick();
		}
	})
}

//上传习题
function uploadExam(data){
	console.info(data.answer)
	if(data.content!=''&&data.answer!=undefined&&data.answer!=''&&data.analysis!=''){
		$.ajax({
			url:"http://edu.bjhd.gov.cn/tiku/uploadExam",
			type:"post",
			data:data,
			success:function(examId){
				layer.msg("上传成功！",{time: 2000, icon:1});
				option_index_multi=69;
				option_index=69;
				small_question_index=4;
				//TODO
				console.info(examId);
				var typeId = $("#new_type_id").val();
				var typeName=$("#new_type_id").attr("data-type_name");
				examMap[examId]=typeId;
				typeIdName[typeId]=typeName;
				if(typeMap.hasOwnProperty(typeId)&&typeMap[typeId]!=null){
					typeMap[typeId]=typeMap[typeId]+1;
				}else{
					typeMap[typeId]=1;
				}
				
				loadSelInfo();
			}
		})
	}else{
		layer.alert("请填写所有项！")
	}
	
}

//绑定学段click事件，触发年级列表的加载
function bindingPhaseClick(){
	$("#phaseListDiv p a").click(function(){
		 $("#phaseListDiv p a").each(function(){
			 $(this).removeClass("aStyle");
		 });
		 $(this).addClass("aStyle");
		 var phaseId = $(this).attr("data-id");
		 $("#phaseId").val(phaseId);
		 loadGrade(phaseId);
		 if(phaseId==53){	//小学
		 	$("a[name='singleSelect']").attr("data-id",2);
		 	$("a[name='multiSelect']").attr("data-id",2);
		 	$("a[name='fill_in']").attr("data-id",1);
		 	$("a[name='read']").attr("data-id",7);
		 	$("a[name='writing']").attr("data-id",9);
		 	$("#new_type_id").val(2);
		 }else{
		 	$("a[name='singleSelect']").attr("data-id",351);
		 	$("a[name='multiSelect']").attr("data-id",353);
		 	$("a[name='fill_in']").attr("data-id",354);
		 	$("a[name='read']").attr("data-id",357);
		 	$("a[name='writing']").attr("data-id",358);
		 	$("#new_type_id").val(351);
		 }
	});
}

//绑定年级click事件
function bindingGradeClick(){
	$("#gradeListDiv p a").click(function(){
				 $("#gradeListDiv p a").each(function(){
					 $(this).removeClass("aStyle");
				 });
				 $(this).addClass("aStyle");
				 var gradeId = $(this).attr("data-id");
				 $("#gradeId").val(gradeId);
				 loadSubject(gradeId);
			});
}

//绑定学科click事件
function bindingSubjectClick(){
	$("#subjectListDiv p a").click(function(){
		$("#subjectListDiv p a").each(function(){
			$(this).removeClass("aStyle");
		});
		$(this).addClass("aStyle");
		var subjectId = $(this).attr("data-id");
		$("#subjectId").val(subjectId);
		$("#kId").val("");
		$("#typeId").val("");
		loadKnowledge();
		screen_exam();
				
	});
}

//绑定知识点click事件，触发试题的重新加载
function bindingKnowledgeClick(){
	$("#knowledgeList li a").click(function(){
		 $("#knowledgeList li a").each(function(){
			 $(this).removeClass("clicka");
		 });
		 $(this).addClass("clicka");
		 var kId = $(this).attr("data-id");
		 $("#kId").val(kId);
		 screen_exam();
	});
}

//上一页
function pageLeft(){
	if($('#current').val()!="1"){
		var current = parseInt($('#current').val())-1;
		$('#current').val(current);
		screen_exam();
	}
}

//下一页
function pageRight(){
	var pageCount = $("#pageCount").val();
	if(pageCount!=$('#current').val()){
		var current = parseInt($('#current').val())+1;
		$('#current').val(current);
		screen_exam();
	}
}

