
require.config({
    baseUrl: 'http://edu.bjhd.gov.cn/', 　
    paths: {　　　　　　
        "dialog": "js/lib/layer/layer",　　　　
        "jqtransform": "js/lib/jqTransform/jqtransform",
        "My97DatePicker": "../lib/My97DatePicker/WdatePicker",
        "headerSlideDown": "js/common/headerslideDown",
        "webuploader": "js/lib/webuploader/webuploader",
        　　　　
    }　　
});
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



    $('.tieziall').on('click',function(){
		var $allL = $('.post').find('ul').height();
		// var $allL = $('.post').find('ul>li').length*$('.post').find('ul>li').height()+21;
		if($allL==260){
			var $allLi = $('.post').find('ul>li').length*($('.post').find('ul>li').height()+1)+20;
			$('.post').find('ul').stop().animate({height:$allLi+"px"},1000);
			$(this).html('收起');
		}else{
			$('.post').find('ul').stop().animate({height:"260px"},1000);
			$(this).html('展开全部');
		}
		
	});
	$('.memberall').on('click',function(){
		var $oDl = $('.post').find('.peoManage');
		var $allL = $oDl.height();

		if($allL==194){
			var $allLi = 0;
			if($oDl.find('dl').length%3==0){
				$allLi = (($oDl.find('dl').length-$oDl.find('dl').length%3)/3)*($oDl.find('dl').height()+8);
			}else{
				$allLi = (($oDl.find('dl').length-$oDl.find('dl').length%3)/3+1)*($oDl.find('dl').height()+8);
			}
			$(this).html('收起');
			$oDl.stop().animate({height:$allLi+"px"},1000);
		}else{
			$oDl.stop().animate({height:"194px"},1000);
			$(this).html('展开全部');
		}
		
	})





     });


require(['jqtransform','headerSlideDown','dialog'], function(jqtransform,aaaa,dialog) {

	$('.roomBottom>h4').on('click',function(){
		$(this).addClass('publish_btn').siblings('h4').removeClass('publish_btn');
		$('.roomBottom>div').eq($(this).index()).css('display','block').siblings('div').css('display','none');
	})
	$('.publish_type p>span').find('label').on('click',function(){
		$(this).addClass('labelChecked').parent().siblings().find('label').removeClass('labelChecked');
	 });
	$('.publish_viewStaff p>span').find('label').on('click',function(){
		$(this).addClass('labelChecked').parent().siblings().find('label').removeClass('labelChecked');
	 })

	$('#set_checked').on('click',function(){
		if($(this).siblings("input").is(':checked')){
          $(this).removeClass('greade_checked');
          $(this).siblings("input").removeProp('checked')
        }
        else{
          $(this).addClass('greade_checked');
          $(this).siblings("input").prop('checked','checked')
        }
		
	})


	$('.topNav li').on('click',function(){
		$(this).addClass('navStyle').siblings().removeClass('navStyle');
	});
	$('.change>a').on('click',function(){
		$(this).addClass('essenceStyle').siblings().removeClass('essenceStyle');
	});
	$('.reply').on('click',function(){
		var txt=$(this).text();
		var textH=$('.replyTextarea').height(); //179
		var dlH=$('.replyBox dl').height();  //79
		var dlNum=$(this).parents().children('.replyBox').find('dl').length;  //2
		var all=dlH*dlNum+textH+160;
		//alert(all);
		if(txt=='回复'){
			$(this).parents().children('td').animate({'height':all},500);
			$(this).text('收起回复');
		}else if(txt=='收起回复'){
			$(this).parents().children('td').animate({'height':149},500);
			$(this).text('回复');
		}
	});
	$(function () {
	    $('.checkbox').on('click',function(){
	      if($(this).siblings("input[type='radio']").is(':checked')){
	        $(this).removeClass('cur');
	        $(this).siblings("input[type='radio']").removeProp('checked')
	      }
	      else{
	        $(this).addClass('cur');
	        $(this).siblings("input[type='radio']").prop('checked','checked')
	      }
	    });
	});
	

	//点击 看帖，精华帖，学习贴，我的贴子
	define("classrooms_init.js", function(){});
	$(" .roomTop .topiclist li[name='allTopic']").click(function(){
		$("#digest").val(null);
		$("#learn").val(null);
		//$('#topicForm').submit();
		var groupId = $("#groupIdhidden").attr('value');
		//$.post("/group/group/list.html?groupId="+groupId, {});//所有帖子
		window.location.href="/group/group/list.html?groupId="+groupId;
	});
	$(" .roomTop .topiclist li[name='isDigest']").click(function(){
		$("#learn").val(null);
		$("#digest").val(1);
		//$('#topicForm').submit();
		var groupId = $("#groupIdhidden").attr('value');
		var isDigest = $("#digest").val();
		//alert(isDigest);
		//$.post("/group/group/list.html?groupId="+groupId, {isDigest:isDigest});//精华
		window.location.href="/group/group/list.html?groupId="+groupId+"&isDigest="+isDigest;
	});
	$(" .roomTop .topiclist li[name='isLearn']").click(function(){
		$("#digest").val(null);
		$("#learn").val(1);
		//$('#topicForm').submit();
		var groupId = $("#groupIdhidden").attr('value');
		var isLearn = $("#learn").val();
		//$.post("/group/group/list.html?groupId="+groupId, {isLearn:isLearn});//学习贴
		window.location.href="/group/group/list.html?groupId="+groupId+"&isLearn="+isLearn;
	});
	$(" .roomTop .topiclist li[name='userId']").click(function(){
		$("#myTopic").val($("#userIdhidden").val());
		//$('#topicForm').submit();
		var groupId = $("#groupIdhidden").attr('value');
		var userId = $("#myTopic").val();
		//$.post("/group/group/list.html?groupId="+groupId, {userId:userId});//我的帖子
		window.location.href="/group/group/list.html?groupId="+groupId+"&userId="+userId;
	});


	//点击进入班群
	$('.comeIn').on('click',function(){

		var groupId = $(this).attr('value');
		window.location.href='/group/group/list.html?groupId='+groupId;
		
	});

	//点击（返回）个人中心
	$(".personal").on('click',function(){

		window.location.href='/group/group/indexGroup.html';
	});


	//点击班群管理（点击进出和点击退出）
	$('.groupManageHref').on('click',function(){

		var groupId = $(this).attr('value');
		var name = $(this).attr('name');
		if (name == 'in') {
			window.location.href="/group/group/topicManage.html?groupId="+groupId;
		} else {
			window.location.href="/group/group/list.html?groupId="+groupId;
		}
	})

	//点击帖子管理
	$('.topicManage').on('click',function(){
		var groupId = $('.topicManage').attr('value');
		$.post("/group/group/essenceManage.html", { groupId:groupId }
											,function(msg){
												var msg = jQuery.parseJSON(msg);
												//alert(msg.data);
												$('.peoManage').html(msg.data);
											});
	});

	//点击成员管理
	$('.memberManage').on('click',function(){
		var groupId = $('.memberManage').attr('value');
		window.location.href='/group/group/memberManage.html?groupId='+groupId;
	});

	//点击 精华的帖子
	$('#essenceStyle').on('click',function(){
		var groupId = $(this).attr('value');
		//window.location.href='/group/group/essenceManage.html?groupId='+groupId;
		$.post("/group/group/essenceManage.html", { groupId:groupId }
			,function(msg){
				var msg = jQuery.parseJSON(msg);
				$('.invitation').html(msg.data);
			});
	});

	//点击 置顶的帖子
	$('#topStyle').on('click',function(){
		var groupId = $(this).attr('value');
		//window.location.href='/group/group/topManage.html?groupId='+groupId;
		$.post("/group/group/topManage.html", { groupId:groupId }
			,function(msg){
				var msg = jQuery.parseJSON(msg);
				$('.invitation').html(msg.data);	
			});
	});

	//取消精华
	$('body').on('click','.cancelEssence',function(){
		var topicId = $(this).attr('attrId');
		var groupId = $('#essenceStyle').attr('value');

		dialog.confirm('确定取消精华吗？',function(){
			$.post("/group/group/essenceManage.html", { topicId: topicId , groupId:groupId},
	   			function(data){
	   				dialog.msg('取消精华成功',{time:1500,icon:1});
	     			$.post("/group/group/essenceManage.html", { groupId:groupId }
									,function(msg){
										var msg = jQuery.parseJSON(msg);
										$('.invitation').html(msg.data);
									});
	   		});
		});
		
	})

	//取消置顶
	$('body').on('click','.cancelTop',function(){
		var topicId = $(this).attr('attrId');
		var groupId = $('#topStyle').attr('value');
		dialog.confirm('确定取消置顶吗？',function(){
			$.post("/group/group/topManage.html", { topicId: topicId , groupId:groupId},
	   			function(data){
	   			dialog.msg('取消置顶成功',{time:1500,icon:1});
     			$.post("/group/group/topManage.html", { groupId:groupId }
					,function(msg){
						var msg = jQuery.parseJSON(msg);
						$('.invitation').html(msg.data);
					});
	   		});
		});
	});



	//点击 教师管理
	$('#teacherStyle').on('click',function(){
		var groupId = $(this).attr('value');
		//alert(groupId);
		$.post("/group/group/teacherManage.html", { groupId:groupId }
					,function(msg){
						var msg = jQuery.parseJSON(msg);
						$('.peoManage').html(msg.data);
					});
	});

	//点击设为管理员
	$('body').on('click','.newManager',function(){
		var userId = $(this).attr('value');	
		var groupId = $('.style').attr('value');
		var flag = 1;
		dialog.confirm('确定设为管理员吗？',function(){
			$.post("/group/group/adminManage.html", { userId:userId , groupId:groupId , flag:flag},
	   			function(data){
	   			dialog.msg(data,{time:1500,icon:1});
     			$('.newManager').html('');
     			$.post("/group/group/teacherManage.html", { groupId:groupId }
										,function(msg){
											var msg = jQuery.parseJSON(msg);
											//alert(msg.data);
											$('.peoManage').html(msg.data);
										});
	   		});
		});
	});

	//点击禁言
	$('body').on('click','.newGag',function(){
		var userId = $(this).attr('value');	
		var groupId = $('.style').attr('value');
		var flag = 2;
		dialog.confirm('确定要禁言吗？',function(){
			$.post("/group/group/adminManage.html", { userId:userId , groupId:groupId , flag:flag},
	   			function(data){
	     		dialog.msg(data,{time:1500,icon:1});
     			$('.newManager').html('');
     			$.post("/group/group/teacherManage.html", { groupId:groupId }
										,function(msg){
											var msg = jQuery.parseJSON(msg);
											//alert(msg.data);
											$('.peoManage').html(msg.data);
										});
	   		});
		});
	});

	//点击取消管理员
	$('body').on('click','.manager',function(){
		var userId = $(this).attr('value');	
		var groupId = $('.style').attr('value');
		var flag = 2;	
		dialog.confirm('确定取消该管理员吗？',function(){
			$.post("/group/group/adminManage.html", { userId:userId , groupId:groupId , flag:flag},
	   			function(data){
	     		dialog.msg(data,{time:1500,icon:1});
	     		$.post("/group/group/teacherManage.html", { groupId:groupId }
											,function(msg){
												var msg = jQuery.parseJSON(msg);
												//alert(msg.data);
												$('.peoManage').html(msg.data);
											});
	   		});
		});
	});

	//点击取消禁言
	$('body').on('click','.gag',function(){
		var userId = $(this).attr('value');	
		var groupId = $('.style').attr('value');
		var flag = 2;	
		dialog.confirm('确定取消禁言吗？',function(){
			$.post("/group/group/adminManage.html", { userId:userId , groupId:groupId , flag:flag},
	   			function(data){
	     		dialog.msg(data,{time:1500,icon:1});
	     		$.post("/group/group/teacherManage.html", { groupId:groupId }
											,function(msg){
												var msg = jQuery.parseJSON(msg);
												$('.peoManage').html(msg.data);
											});
	   		});
		});
	});


	//点击 学生管理
	$('#studentStyle').on('click',function(){
		var groupId = $(this).attr('value');		
		//window.location.href='/group/group/studentManage.html?groupId='+groupId;
		$.post("/group/group/studentManage.html", { groupId:groupId }
					,function(msg){
						var msg = jQuery.parseJSON(msg);
						//alert(msg.data);
						$('.peoManage').html(msg.data);
						
					});
	});

	//点击设为管理员
	$('body').on('click','.newStudentManager',function(){
		var userId = $(this).attr('value');	
		var groupId = $('.style').attr('value');
		var flag = 1;
		dialog.confirm('确定设为管理员吗？',function(){
			$.post("/group/group/adminManage.html", { userId:userId , groupId:groupId , flag:flag},
					function(data){
					dialog.msg(data,{time:1500,icon:1});
					$('.newStudentManager').html('');
					$.post("/group/group/studentManage.html", { groupId:groupId }
											,function(msg){
												var msg = jQuery.parseJSON(msg);
												//alert(msg.data);
												$('.peoManage').html(msg.data);
											});
				});
		});
	});

	//点击禁言
	$('body').on('click','.newStudentGag',function(){
		var userId = $(this).attr('value');	
		var groupId = $('.style').attr('value');
		var flag = 2;
		dialog.confirm('确定要禁言吗？',function(){
			$.post("/group/group/adminManage.html", { userId:userId , groupId:groupId , flag:flag},
					function(data){
					dialog.msg(data,{time:1500,icon:1});
					$('.newStudentManager').html('');
					$.post("/group/group/studentManage.html", { groupId:groupId }
											,function(msg){
												var msg = jQuery.parseJSON(msg);
												$('.peoManage').html(msg.data);
											});
			});
		});
	});

	//点击取消管理员
	$('body').on('click','.studentManager',function(){
		var userId = $(this).attr('value');	
		var groupId = $('.style').attr('value');
		var flag = 2;	
		dialog.confirm('确定取消该管理员吗？',function(){
			$.post("/group/group/adminManage.html", { userId:userId , groupId:groupId , flag:flag},
					function(data){
					dialog.msg(data,{time:1500,icon:1});
					$.post("/group/group/studentManage.html", { groupId:groupId }
											,function(msg){
												var msg = jQuery.parseJSON(msg);
												//alert(msg.data);
												$('.peoManage').html(msg.data);
											});
			});
		});
	});

	//点击取消禁言
	$('body').on('click','.studentGag',function(){
		var userId = $(this).attr('value');	
		var groupId = $('.style').attr('value');
		var flag = 2;	
		dialog.confirm('确定取消禁言吗？',function(){
			$.post("/group/group/adminManage.html", { userId:userId , groupId:groupId , flag:flag},
					function(data){
					dialog.msg(data,{time:1500,icon:1});
					$.post("/group/group/studentManage.html", { groupId:groupId }
											,function(msg){
												var msg = jQuery.parseJSON(msg);
												$('.peoManage').html(msg.data);
											});
			});
		});
		
	});

	//点击新增帖子
	$("body").on('click','#addTopic',function(){
		var groupId = $("#addTopic").attr('name');
		var content = $("#content").val();
		var title = $("#title").val();
		var boo = $("#isLearn").is(":checked");
		//alert(boo);
		if(title==null || title==''){
			dialog.msg('标题不能为空',{time:1500,icon:6});
			return;
		}
		if(content==null || content==''){
			dialog.msg('内容不能为空',{time:1500,icon:6});
			return;
		}
		$.post("/group/group/addTopic.html", { content: content , title:title ,groupId:groupId ,isLearn:boo},
	   			function(msg){
	     		var msg = jQuery.parseJSON(msg);
	     		if (msg.data == '对不起，您当前没有该权限') {
	     			dialog.msg('对不起，您当前没有该权限',{time:1500,icon:7});

	     		} else{
	     			
	     			dialog.msg('成功！',{time:1500,icon:6});
	     			$('.roomDl').html(msg.data);
	     			var content = $("#content").val('');
					var title = $("#title").val('');
					window.location.href="#roomheader";
	     			//$('.roomBottom').hide();
	     			
	     		}
	   		});
		
	})

	$('.toNew').click(function(){
		$('.roomBottom').show();
		window.location.href="#roomBottom";
	});


	//点击发布回复帖子
	$("#comment").on('click',function(){
		var topicId = $("#comment").attr('name');
		var content = $("#words").val();
		if(content == '' || content == null){
			dialog.msg('内容不能为空',{time:1500,icon:6});
			return;
		}
		$.post("/group/group/addTopicBack.html", { content: content , topicId:topicId },
	   			function(data){
	     			if (data=='回复成功！') {
	     				dialog.alert(data,function(){
	     					window.location.reload(true);
	     				});
	     			} else{
	     				dialog.msg(data,{time:1200,icon:7});
	     			}
	   		});
	})

	//点击回复子帖
	$(".fun").on('click',function(){
		var topicId = $(this).attr('name');
		var sayBack = $("."+topicId+"").val();
		if(sayBack == '' || sayBack == null){
			dialog.msg('内容不能为空',{time:1500,icon:6});
			return;
		}
		$.post("/group/group/addTopicBack.html", { content: sayBack ,topicId:topicId },
	   			function(data){
	     			if (data=='回复成功！') {
	     				dialog.alert(data,function(){
	     					window.location.reload(true);
	     				});
	     			} else{
	     				dialog.msg(data,{time:1200,icon:7});
	     			}
	     		
	   		});
	})

	//删除回复帖
	$('.delreplay').on('click',function(){
		var topicId = $(this).attr('attrId');
		dialog.confirm('确定删除吗？',function(){
			$.post("/group/group/delete.html", { topicId:topicId },
	   			function(data){
	     		dialog.alert(data,function(){
	     			window.location.reload(true);
	     		});
	   		});
		});
	});


	//投票
	$('.voteIt').on('click',function(){
		var optionType = $(this).attr('name');
		if (optionType==1) {//单选
			var voteContent = $('input:radio:checked').val();
		} else{
			var voteContent = '';
			$("input[type=checkbox]:checked").each(function() {
        		voteContent += $(this).attr('value')+',';
      		});
      		voteContent = voteContent.substring(0,voteContent.length-1);
            //alert(voteContent);
            //return;
		}
		
		var voteId = $(this).attr('value');
		var tip = $('#tip').html();	

		var endtime =$('.overTime').attr('value');		 
		endtime = endtime.replace(/-/g,"/");//替换字符，变成标准格式
		var nowDate = new Date();//取今天的日期
		var endDate = new Date(Date.parse(endtime));
		if(nowDate <= endDate){
		    if(typeof(voteContent) == 'undefined'){
				dialog.msg('请先选择再投票',{time:1500,icon:6});
				return;
				} else{
					$.post("/group/group/voteIt.html", { voteContent:voteContent , voteId:voteId}
						,function(msg){
						var msg = jQuery.parseJSON(msg);
						$('#tip').html(msg.message);
						$('.content_box').html(msg.data);
						dialog.msg($('#tip').html(),{time:1200,icon:6});
					});
			}
		} else{
			dialog.msg('对不起，投票已结束。',{time:1500,icon:2});
			$('.voteIt').attr('class','voteIt');
			$('.endVote').attr('class','vote_btn endVote');
		}		
	});

	//语录编辑
	$('.announcement').on('click',function(){
		var quotations = $('.announcementWords').val();
		var groupId = $(this).attr('value');
		$.post("/group/group/updateQuotations.html", { groupId:groupId,quotations:quotations },
	   			function(msg){
	   			var msg = jQuery.parseJSON(msg);
	   				dialog.msg(msg.data,{time:1500,icon:6});
	   		});

	});

	//公告编辑
	$('.notice').on('click',function(){
		var notice = $('.noticeWords').val();
		var groupId = $(this).attr('value');
		
		$.post("/group/group/updateQuotations.html", { groupId:groupId,notice:notice },
	   			function(msg){
	   			var msg = jQuery.parseJSON(msg);
	     		dialog.msg(msg.data,{time:1500,icon:6});
	     		
	   		});
	});


	//点击添加投票选项
	$('body').on('click','#addInput',function(){
		//alert('ddd');
		$('.addMore').html("<div class='optionVoteId'><p><input type='text' name='optionVote' /><a href='javascript:;' class='moveOut'>移除</a></p></div><div class='hello'><i id='addInput'><a href='javascript:;'>添加投票选项</a></i></div>");
		$('.addMore').attr('class','');
		$('.hello').attr('class','addMore');
	});

	//点击移除投票选项
	$('body').on('click','.moveOut',function(){
		$(this).parent().parent().html('');
	});

	//点击发表  投票帖子
	$('#addVoteTopic').on('click',function(){
		var voteType = '';
		var groupId = $("#addVoteTopic").attr('name');
		voteType = $("input[type='radio']:checked").val();//投票类型
	    var voteTitle = $('#titleVote').val();//投票标题
	    var voteContent = $('#conentVote').val();//投票内容
	    var options = document.getElementsByName('optionVote');//投票选型
	    var allOption = '';
	    for(var i=0;i<options.length;i++){
	      var option = options[i];
	      allOption += option.value+',';
	    }
	    allOption = allOption.substring(0,allOption.length-1);//所有的投票选项 拼接成字符串
	    var endTime = $('#time').val();
	    var crTime = new Date();

		if(voteTitle==""){
         dialog.tips('标题不能为空！', '#titleVote', {
          tips: [2, '#3595CC'],
          time: 2000
        });
         return;
       }
       if (typeof(voteType) == "undefined") { 
		   dialog.msg('请选择投票类型',{time:1200,icon:6});
		   return;
		}
       if(voteContent==""){
         dialog.tips('请输入内容！', '#conentVote', {
          tips: [2, '#3595CC'],
          time: 2000
        });
        return;
       }
       if(allOption.replace(',','').trim()==''){
        dialog.msg('您还未填写投票选项', {time: 1500, icon:6});
        return;
       }
       if(endTime==''){
        dialog.msg('请选择截止日期', {time: 1500, icon:6});
        return;
       }
	    $.post("/group/group/sendVoteTopic.html", {groupId:groupId,voteType:voteType,title:voteTitle,content:voteContent,allOption:allOption,endTime:endTime,crTime:crTime}
              ,function(msg){
                 var msg = jQuery.parseJSON(msg);
                 if (msg.data=='对不起，您当前没有该权限') {
                 	dialog.msg(msg.data,{time:1500,icon:2});
                 } else{
                 	dialog.msg('成功！',{time:1500,icon:1});
	     			$('.roomDl').html(msg.data);
	     			$('#titleVote').val('');//投票标题
	    			$('#conentVote').val('');//投票内容
	    			//$('.roomBottom').hide();
	    			window.location.href="#roomheader";
                 }
              }); 
	});

	//点击设为精华
	$('body').on('click','#toDigest',function(){
		var flag = 1;
		var topicId = $(this).attr('value');
		$.post("/group/group/toDigest.html", {topicId:topicId,flag:flag}
              ,function(msg){
              	var msg = jQuery.parseJSON(msg);
              	dialog.msg('成功！',{time:1500,icon:1});
              	$('#toDigestClass').html(msg.data);
              });
	});

	//点击取消精华
	$('body').on('click','#outDigest',function(){
		var flag = 2;
		var topicId = $(this).attr('value');
		$.post("/group/group/toDigest.html", {topicId:topicId,flag:flag}
              ,function(msg){
              	var msg = jQuery.parseJSON(msg);
              	dialog.msg('成功！',{time:1500,icon:1});
              	$('#toDigestClass').html(msg.data);
              });
	});

	//点击设为置顶
	$('body').on('click','#toTop',function(){
		var flag = 1;
		var topicId = $(this).attr('value');
		$.post("/group/group/toTop.html", {topicId:topicId,flag:flag}
              ,function(msg){
              	var msg = jQuery.parseJSON(msg);
              	dialog.msg('成功！',{time:1500,icon:1});
              	$('#toDigestClass').html(msg.data);
              });
	});

	//点击取消置顶
	$('body').on('click','#outTop',function(){
		var flag = 2;
		var topicId = $(this).attr('value');
		$.post("/group/group/toTop.html", {topicId:topicId,flag:flag}
              ,function(msg){
              	var msg = jQuery.parseJSON(msg);
              	dialog.msg('成功！',{time:1500,icon:1});
              	$('#toDigestClass').html(msg.data);
              });
	});

	//点击取消删除帖子
	$('body').on('click','.cancelTopic',function(){
		
		var topicId = $(this).attr('value');
		var groupId = $(this).attr('name');
		dialog.confirm('确定删除吗？',function(){
			$.post("/group/group/cancelTopic.html", {topicId:topicId}
              ,function(msg){
              	dialog.alert(msg,function(){
              		window.location.href='/group/group/list.html?groupId='+groupId;
              	});
              	
              });
		});
		
	});

	//进入帖子后返回
	$('.backToList').click(function(){
		window.location.href=document.referrer;
	});

	//管理自己的帖子
	$('body').on('click','#deleteMine',function(){
		var topicId = $(this).attr('value');
		var groupId = $("#groupIdhidden").attr('value');
		var userId = $(this).attr('name');
		dialog.confirm('确定删除吗？',function(){
			$.post("/group/group/cancelTopic.html", {topicId:topicId}
              ,function(msg){
              	
              	window.location.href='/group/group/list.html?groupId='+groupId+"&userId="+userId;
              });
		});
	})

});