require.config({　　　　
	paths: {　　　　　　
		"dialog": "../lib/layer/layer",　　　　
		"jqtransform": "../lib/jqTransform/jqtransform",
		"My97DatePicker": "../lib/My97DatePicker/WdatePicker",
		"headerSlideDown": "../common/headerslideDown",
		"job_list_tea":　"../mooc/homework/teacher/job_list",　　　　
		"do_job":　"../mooc/homework/student/do_job",　　　
	}　　
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	 $(".adminBody form,.screenBar form").jqTransform();
	 require(['http://edu.bjhd.gov.cn/js/lib/My97DatePicker/WdatePicker.js'],function(){
		 $("#time").on("click",function(){
		 		WdatePicker({
		 		el:'time',
		 	})
		 	
		 	
		 });
		 $("#time1").on("click",function(){
		 	WdatePicker({
		 		el:'time1',
		 	})
		 })
	 })
	 $('.navClass li').on('click',function(){
	 	$(this).addClass('nav_li').siblings().removeClass('nav_li');
	 })

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
	    		url:"http://edu.bjhd.gov.cn/homework/getStudentList",
	    		type:"post",
	    		data:{
	    			"hwId":$("#hwId").val(),
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

  

});

<!--教师作业列表  开始-->
require(['job_list_tea','dialog'],function(list,dialog){
	var curDate = new Date();
	var tomorrowDate = new Date(Date.parse(curDate)+1000*60*60*24);
	var month_cur = curDate.getMonth()+1;
	var month_tomo = tomorrowDate.getMonth()+1;
	$("input[name='startDate']").val(curDate.getFullYear()+"-"+month_cur+"-"+curDate.getDate());
	$("input[name='endDate']").val(tomorrowDate.getFullYear()+"-"+month_tomo+"-"+tomorrowDate.getDate());
	list.screen_homework();

	$("#today").click(function(){
		$("#today").removeClass("aLink");
		$("#before").removeClass("btn");
		$("#today").addClass("btn");
		$("#before").addClass("aLink");
		var curDate = new Date();
		var tomorrowDate = new Date(Date.parse(curDate)+1000*60*60*24);
		var month_cur = curDate.getMonth()+1;
		var month_tomo = tomorrowDate.getMonth()+1;
		$("input[name='startDate']").val(curDate.getFullYear()+"-"+month_cur+"-"+curDate.getDate());
		$("input[name='endDate']").val(tomorrowDate.getFullYear()+"-"+month_tomo+"-"+tomorrowDate.getDate());
		list.screen_homework();
	});

	$("#before").click(function(){
		$("#today").removeClass("btn");
		$("#before").removeClass("aLink");
		$("#today").addClass("aLink");

		$("#before").addClass("btn");
		var curDate = new Date();
		var month_cur = curDate.getMonth()+1;
		$("input[name='startDate']").val("");
		$("input[name='endDate']").val(curDate.getFullYear()+"-"+month_cur+"-"+curDate.getDate());
		list.screen_homework();
	});

	$("#seeBtn").click(function(){
		list.screen_homework();
	})
});
<!--教师作业列表  结束-->



<!--学生做作业-->
require(['do_job','dialog'],function(job,dialog){

	$('input[type="radio"]').siblings('label').on('click',function(){
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
	var answeredQuestions = [];

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
				}else if($(hh).attr("data-type")==352||$(hh).attr("data-type")==353||$(hh).attr("data-type")==2){
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
			console.info(examIds)
			console.info(typeIds)
			console.info(answers)
			 job.submit_job(examIds,typeIds,answers);
		}else{
			dialog.alert("尚未完成!");
		}
		
	})

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

	function updateTooltip(){
		var notAnsweredQuestionNum = $("#questionTotal").val()-answeredQuestions.length;
		var html = '<p>已答：'+answeredQuestions.length+'题</p><p>未答：'+notAnsweredQuestionNum+'题</p>';
		$(".judge").empty();
		$(".judge").append(html);
	}
	
});

