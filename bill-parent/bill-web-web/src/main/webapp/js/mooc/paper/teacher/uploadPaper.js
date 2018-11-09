
//全局变量，用map存放已选择的试题
var examMap = {};//试题id和题型id的对象
var typeMap = {};//题型name和试题数的对象

loadSelInfo();

$('input[name="selectedExam"]').each(function(){
	var examId = $(this).attr('data-examid');
	var typeId = $(this).attr('data-typeid');
	var typeName = $(this).attr('data-typename');
	
	examMap[examId]=typeId;
	if(typeName==''){
		typeName="未知题型"
	}
	if(typeMap.hasOwnProperty(typeName)){
		typeMap[typeName]=typeMap[typeName]+1;
	}else{
		typeMap[typeName]=1;
	}
	loadSelInfo();
})



//添加选项从E开始
var option_index=69;
var option_index_multi=69;
var small_question_index=4;




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
				'insertorderedlist','insertunorderedlist','formatblock','fontname','fontsize','bold','italic','underline','|',
				'image','hr','lineheight'],
		afterCreate : function() { 
	this.sync(); 
}, 
afterBlur:function(){ 
    this.sync(); 
}     
	});
   
});

//上传单选题
$("#single_upload").click(function(){
	var content = $("#single_content").val();
	var options = [];
	$("input[name='single_option']").each(function(){
		options.push($(this).val());
	})
	var answer = $("input[name='single_answer']:checked").val();
	var analysis = $("#single_analysis").val();
	var data = {

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
	var answer = "";
	$("input[name='multi_answer']:checked").each(function(){
		answer=answer+$(this).val();
	});
	var analysis = $("#multi_analysis").val();
	var data = {
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
		
		"typeId":$("#new_type_id").val(),
		"content":content,
		"answer":answer,
		"analysis":analysis,
		"typeName":$("#new_type_id").attr("data-type_name"),
	};
	uploadExam(data);
});

//预览编辑
$('.course_right').find('p>button').eq(0).on('click',function(){
	var total = 0;
	for(typeName in typeMap){
		if(typeMap[typeName]!=null){
			total=total+typeMap[typeName];
		}
	}
	var moocId = $("#moocId").val();
	var periodId = $("#periodId").val();
	var listenUrl = $("#listenUrl").val();
	if(total>0){
		 var url = "/mooc/paper/teacher/previewExam?moocId="+moocId+"&periodId="+periodId+"&listenUrl="+listenUrl;
		 for(examId in examMap){ 
			 if(examMap[examId]!=null){
			 	url=url+"&examId="+examId;
			 }
		 }
		 window.location.href=url;
		}else{
			layer.alert("未选择习题！");
		}
	
});

//发布习题
$('.course_right').find('p>button').eq(1).on('click',function(){

	 var examIds = [];
	 for(examId in examMap){     //取出对象的所有属性名，放到数组里
		 examIds.push(examId); 
	 }
	var moocId = $("#moocId").val();
	var periodId = $("#periodId").val();
	var listenUrl = $("#listenUrl").val();

	if(examIds.length>0){
		$.ajax({
			url:"/mooc/paper/teacher/releasePaper",
			data:JSON.stringify({
				"examId":examIds,
				"moocId":moocId,
				"periodId":periodId,
				}),
			type:"post",
			datatype:"json",
			contentType:"application/json;charset=UTF-8",
			success:function(result){
				var json = eval("("+result+")");
				if(json.success){
					layer.msg("发布成功！正在跳转详情页~",{time: 2000, icon:1},function(){
						window.location.href="/mooc/course/details?id="+moocId+"&videoId="+periodId+"&listenUrl="+listenUrl;
					})
				}else{
					layer.alert("发布失败！");
				}
				
			}
		})
	}else{
		layer.alert("未选择习题！");
	}
	
	

})





//为“已上传题目”填充数据
function loadSelInfo(){
	$("#selectedInfo").empty();
	var total = 0;
	var str = "";
	for(typeName in typeMap){
		if(typeMap[typeName]!=null){
			total=total+typeMap[typeName];
			str = str+"<li>"+typeName+"："+typeMap[typeName]+"</li>";
		}
	}
	var lis = "<li>共"+total+"题，其中</li>"+str;
	$("#selectedInfo").append(lis);
}



//上传习题
function uploadExam(data){
	
	if(data.content!=''&&data.answer!=undefined&&data.answer!=''&&data.analysis!=''){
		$.ajax({
			url:"/tiku/uploadExam",
			type:"post",
			data:data,
			success:function(examId){
				layer.msg("上传成功！",{time: 2000, icon:1});
				option_index_multi=69;
				option_index=69;
				small_question_index=4;
				//TODO
				console.info(examId);
				examMap[examId]=$("#new_type_id").val();
				var typeName=$("#new_type_id").attr("data-type_name");
				if(typeMap.hasOwnProperty(typeName)&&typeMap[typeName]!=null){
					typeMap[typeName]=typeMap[typeName]+1;
				}else{
					typeMap[typeName]=1;
				}
				
				loadSelInfo();
			}
		})
	}else{
		layer.alert("请填写所有项！")
	}
	
}






