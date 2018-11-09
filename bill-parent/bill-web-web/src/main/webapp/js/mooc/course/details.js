require.config({          
	baseUrl: '/',           
	paths: {
		"dialog": "js/lib/layer/layer",        
		"jqtransform": "js/lib/jqTransform/jqtransform",
		"headerSlideDown": "js/common/headerslideDown",
"webuploader": "js/lib/webuploader/webuploader",
"paper": "js/mooc/paper/teacher/admin",
	}          
});


require(['jqtransform','js/admin/common/adminAlertBox','headerSlideDown','dialog'], function(jqtransform,adminAlertBox,aaaa,dialog) {
	$('.exeCheck').on('click',function(){
		$(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings('span').find("input[type='radio']").removeProp('checked');
		$(this).addClass('label_checked').parent().siblings('span').find('label').removeClass('label_checked');
		
    });
	//点亮星星
	$(function(){
	var oLis = $('.starcon>li');
	oLis.on('mouseover',function(){
		for(var i=0;i<=$(this).index();i++){
			oLis.eq(i).addClass('starheigh');
		}
		for(var i=($(this).index()+1);i<=oLis.length;i++){
			oLis.eq(i).removeClass('starheigh');
		}
	})

	oLis.on('click',function(){
		var score = $(this).index()+1;
		$("#score").val(score);
	})
	
})

	//获取请求路径中播放地址的值
	function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
var str = url.substr(1);
strs = str.split("&");
for(var i = 0; i < strs.length; i ++) {
    theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
}
    }
    return theRequest;
	}
	var Request = new Object();
	Request = GetRequest();
	var listenUrl=Request['listenUrl'];
	var videoId=Request['videoId'];
	$("#videoId").val(videoId);

	//视频播放
    var player = polyvObject('#polyvplayer').videoPlayer({
     'width':'910',
     'height':'530',
     'vid':listenUrl,
     'flashvars' : {"autoplay":"0","setScreen":"100"}
    });


getInteractionList();

	//视频学习、习题、学案及课件轮播转换
	$(".detail").on('click',function(){
		$(this).parent().find('a').removeClass('titStyle').addClass('detail');
		$(this).attr('class','titStyle');

	});

	$("#moveStudy").on('click',function(){
		$(".moveStudy").show();
		$(".exercises").hide();
		$(".courseware").hide();
	});

	$("#exercises").on('click',function(){
		$(".moveStudy").hide();
		$(".exercises").show();
		$(".courseware").hide();
	});

	$("#courseware").on('click',function(){
		$(".moveStudy").hide();
		$(".exercises").hide();
		$(".courseware").show();
	});

	//课程互动、我的答疑、课程评价轮播转换
	$(".comment").on('click',function(){
		$(this).parent().find('li').removeClass('plattit_style').addClass('comment');
		$(this).attr('class','plattit_style');

	});

	$("#interaction").on('click',function(){
		$(".interaction").show();
		$(".answer").hide();
		$(".evaluate").hide();
	});

	$("#answer").on('click',function(){
		$(".interaction").hide();
		$(".answer").show();
		$(".evaluate").hide();
	});

	$("#evaluate").on('click',function(){
		$(".interaction").hide();
		$(".answer").hide();
		$(".evaluate").show();
	});

	//课程互动点击发表
	$('body').on('click','.publish',function(){
		var content=$("#content").val();
		var type=$("#type").val();
		var isLeaf=$("#isLeaf").val();
		var score=$("#score").val();
		if(content==""){
			dialog.msg('您还没有输入内容！',{time:1200,icon:1});
			return;
		}
		publish(content,type,isLeaf,score);
		dialog.msg('评价成功！',{time:1200,icon:1});
		$("#content").val("");
	});

	//课程互动回复发表二级评论
	$('body').on('click','.replyPublish',function(){
		var content=$(this).parent().parent().find('textarea').val();
		if(content==""){
			dialog.msg('您还没有输入内容！',{time:1200,icon:1});
			return;
		}
		var type=$("#type").val();
		var isLeaf=0;
		var vid=$("#videoId").val();
			$.ajax({
			url:"/mooc/course/replyPublish.html",
			data:{content:content,type:type,isLeaf:isLeaf,vid:vid},
			type:"post",
			async:false,
			dataType:"json",
			success:function(data){
				if(data!=null){
					if(type==1){
						$.post("/mooc/course/replyComment.html",{type:type,vid:vid},function(msg){
					if(msg!=null){
						var msg = jQuery.parseJSON(msg);
						var replyCommentList=msg.data;
						var html="";
						$(".sec_dl").html(html);
						for(var i=0;i<replyCommentList.length;i++){
							html+="<div class='my_pic'><img src='"+replyCommentList[i].headUrl+"'></div><div class='my_con'><h4>"+replyCommentList[i].userName+"<span>"+replyCommentList[i].klassName+"  "+replyCommentList[i].schoolName+"</span></h4>";
							html+="<p class='p_text'>"+replyCommentList[i].content+"</p><p class='time_show'><span><a href=javascript:; value='"+replyCommentList[i].id+"' class='deleteSecComment'>删除</a></span></p></div>";       
						}
						$(".sec_dl").html(html);
						dialog.msg('回复成功!',{time:1200,icon:1});
					}
					$(".replyText").val("");
				});
					}
				}
			},
			error:function(){
				return false;
			}
	  });
	});

	//课程评价点击发表
	$('body').on('click','.evaluatePublish',function(){
		var content=$("#evaluateContent").val();
		if(content==""){
			dialog.msg('您还没有输入内容！',{time:1200,icon:1});
			return;
		}
		var type=$("#evaluateType").val();
		var score=$("#score").val();
		var isLeaf='1';
		if(content.length<10){
			dialog.msg('评价不能少于十个字！',{time:1200,icon:2});
		}else{
			publish(content,type,isLeaf,score);
			$("#evaluateContent").val("");
		}
		
	});

	// //取消置顶
	// $("body").on('click','.reset_top',function(){
	// 	var id=$(this).parent().parent().parent().find('input').val();
	// 	$.post("/mooc/course/top.html",{id:id},function(data){
	// 		getInteractionList();
	// 	});
	// });

	// //置顶
	// $("body").on('click','.top_pic',function(){
	// 	var id=$(this).parent().find('input').val();
	// 	$.post("/mooc/course/top.html",{id:id},function(data){
	// 		getInteractionList();
	// 	});
	// });


	//课程互动点击回复查询二级回复列表
	$("body").on('click','.reply',function(){
		$(this).parent().parent().parent().find("div").show();
		//$('.bottom').hide();
		var type=$("#type").val();
		var vid=$("#videoId").val();
			if(type==1){
				$.post("/mooc/course/replyComment.html",{type:type,vid:vid},function(msg){
					if(msg!=null){
						var msg = jQuery.parseJSON(msg);
						var replyCommentList=msg.data;
						var html="";
						$(".sec_dl").html(html);
						for(var i=0;i<replyCommentList.length;i++){
							html+="<div class='my_alcon'><div class='my_pic'><img src='"+replyCommentList[i].headUrl+"'></div><div class='my_con'><h4>"+replyCommentList[i].userName+"<span>"+replyCommentList[i].klassName+"  "+replyCommentList[i].schoolName+"</span></h4>";
							html+="<p class='p_text'>"+replyCommentList[i].content+"</p><p class='time_show'><span><a href=javascript:; value='"+replyCommentList[i].id+"' class='deleteSecComment'>删除</a></span></p></div></div>";       
						}
						$(".sec_dl").html(html);
					}
				});
			}
			$(this).attr('class','reply');
			$('.hide').parent().parent().parent().children('div').hide();
			$(this).attr('class','reply hide');
	});


	//我的答疑点击回复查询二级评论列表
	$("body").on('click','.answerReply',function(){
		 $(this).parent().parent().parent().find("div").show();
		 //$('.bottom').hide();
		var type=2;
		var vid=$("#videoId").val();
			if(type==2){
				$.post("/mooc/course/replyComment.html",{type:type,vid:vid},function(msg){
					if(msg!=null){
						var msg = jQuery.parseJSON(msg);
						var answerReplyCommentList=msg.data;
						var html="";
						$(".sec_dl").html(html);
						for(var i=0;i<answerReplyCommentList.length;i++){
							html+="<div class='my_pic'><img src='"+answerReplyCommentList[i].headUrl+"'></div><div class='my_con'><h4>"+answerReplyCommentList[i].userName+"<span>"+answerReplyCommentList[i].klassName+"  "+answerReplyCommentList[i].schoolName+"</span></h4>";
							html+="<p class='p_text'>"+answerReplyCommentList[i].content+"</p><p class='time_show'><span><a href=javascript:; value='"+answerReplyCommentList[i].id+"' class='answerSecRep'>删除</a></span></p></div>";       
						}
						$(".sec_dl").html(html);
					}
				});
			}
			$(this).attr('class','answerReply');
			$('.hide').parent().parent().parent().children('div').hide();
			$(this).attr('class','answerReply hide');
	});


	//我的答疑回复发表
	$('body').on('click','.answerReplyPublish',function(){
		var content=$(this).parent().parent().find('textarea').val();
		if(content==""){
			dialog.msg('您还没有输入内容！',{time:1200,icon:1});
			return;
		}
		var type=$('#answer').find('a').attr('value');
		var isLeaf=$("#isNotLeaf").val();
		var vid=$("#videoId").val();
			$.ajax({
			url:"/mooc/course/replyPublish",
			data:{content:content,type:type,isLeaf:isLeaf,vid:vid},
			type:"post",
			async:false,
			dataType:"json",
			success:function(data){
				if(data!=null){
					if(type==2){
						$.post("/mooc/course/replyComment.html",{type:type,vid:vid},function(msg){
					if(msg!=null){
						var msg = jQuery.parseJSON(msg);
						var answerReplyCommentList=msg.data;
						var html="";
						$(".sec_dl").html(html);
						for(var i=0;i<answerReplyCommentList.length;i++){
							html+="<div class='my_pic'><img src='"+answerReplyCommentList[i].headUrl+"'></div><div class='my_con'><h4>"+answerReplyCommentList[i].userName+"<span>"+answerReplyCommentList[i].klassName+"  "+answerReplyCommentList[i].schoolName+"</span></h4>";
							html+="<p class='p_text'>"+answerReplyCommentList[i].content+"</p><p class='time_show'><span><a href=javascript:; value='"+answerReplyCommentList[i].id+"'  class='answerSecRep'>删除</a></span></p></div>";       
						}
						$(".sec_dl").html(html);
						dialog.msg('回复成功!',{time:1200,icon:1});
					}
					$(".replyText").val("");
				});
					}
				}
			},
			error:function(){
				return false;
			}
	  });
	});

	//删除课程互动下的评论
	$("body").on('click','.deleteComment',function(){
		var id=$(this).parent().parent().parent().find('input').attr('value');
		 dialog.alert('确定删除吗？',function(){
		 	$.post("/mooc/course/deleteComment.html",{id:id},function(data){
		 		var data=jQuery.parseJSON(data);
				if(data.success){
					getInteractionList();
					dialog.msg(data.data,{time:1200,icon:1});
				}
				else{
					getInteractionList();
					dialog.msg(data.message,{time:1200,icon:2});
				}
			
			});
		 });
		
	}); 

	//删除我的答疑下的评论
	$("body").on('click','.deleteAnswerComment',function(){
		var id=$(this).parent().parent().parent().find('input').attr('value');
		dialog.alert('确定删除吗？',function(){
			$.post("/mooc/course/deleteComment.html",{id:id},function(data){
				var data=jQuery.parseJSON(data);
				if(data.success){
					dialog.msg(data.data,{time:1200,icon:1});
					getAnswerList();
				}
				else{
					dialog.msg(data.message,{time:1200,icon:2});
					getAnswerList();
				}
		});
		});
		
	}); 
	//删除课程互动下的二级回复
	$("body").on('click','.deleteSecComment',function(){
		var id=$(this).attr('value');
		var type=1;
		dialog.alert('确定删除吗？',function(){
			$.post("/mooc/course/deleteComment.html",{id:id},function(data){
				var data=jQuery.parseJSON(data);
				if(data.success){
					dialog.msg(data.data,{time:1200,icon:1});
					window.location.reload();
				}
				else{
					dialog.msg(data.message,{time:1200,icon:2});
					window.location.reload();
				}
			});
		});
	});

	//删除我的答疑下的二级回复
	$("body").on('click','.answerSecRep',function(){
		var id=$(this).attr('value');
		var type=2;
		dialog.alert('确定删除吗？',function(){
			$.post("/mooc/course/deleteComment.html",{id:id},function(data){
				var data=jQuery.parseJSON(data);
				if(data.success){
					dialog.msg(data.data,{time:1200,icon:1});
					window.location.reload();
				}
				else{
					dialog.msg(data.message,{time:1200,icon:2});
					window.location.reload();
				}
			});
		});
	});

	//点击课程互动 
	$("#interaction").on('click',function(){
		getInteractionList();
	});

	//点击我的答疑
	$("body").on('click','#answer',function(){
		getAnswerList();
	});

	//点击课程评价
	$("body").on('click','#evaluate',function(){
		getEvaluateList();
	});

	//课程互动下全部与只看本校
	$("body").on('click','#allCom',function(){
		getInteractionList();
	});

	$("body").on('click','#ownSchlCom',function(){
		var type=1;
		var vid=$("#videoId").val();
		$.ajax({
			url:"/mooc/course/ownSchlCom.html",
			data:{type:type,vid:vid},
			type:"post",
			async:false,
			dataType:"json",
			success:function(data){
				if(data!=null){
					var commentList = data.data;
					var html="";
					$("#comment").html(html);
					for(var i=0;i<commentList.length;i++){
					html+="<dl><dt><img src='"+commentList[i].headUrl+"'></dt><dd><input type='hidden' name='id' value='"+commentList[i].id+"'/><h4>"+commentList[i].userName+"<span>"+commentList[i].klassName+"  "+commentList[i].schoolName+"</span></h4>";
					html+="<p class='p_text'>"+commentList[i].content+"</p><div style='display:none' class='replyDiv'><div class='reply_od'><img src='"+commentList[i].headUrl+"'><h5><textarea class='replyText' name='content'></textarea>";
					html+="	<p class=''><a href='javascript:;' class='replyPublish'>回复</a></p></h5></div></div><div style='display:none' class='sec_dl cf'>"; 
					html+="</div><p class='time_show'><span><a href='javascript:;' class='reset_top' id='caltop"+commentList[i].id+"'></a><a href='javascript:;' class='reply' value='"+commentList[i].id+"'>回复</a><a href='javascript:;' class='deleteComment'>删除</a></span></p></dd></dl>";
					
					}
					$("#comment").html(html);
				}

			},
			error:function(){
				return false;
			}
	  	});

	});
	

	//获取课程互动下的二级回复列表
	function getReplyComment(){
		var vid=$("#videoId").val();
		$.post("/mooc/course/replyComment.html",{type:type,vid:vid},function(msg){
			if(msg!=null){
				var msg = jQuery.parseJSON(msg);
				var replyCommentList=msg.data;
				var html="";
				$(".sec_dl").html(html);
				for(var i=0;i<replyCommentList.length;i++){
					html+="<div class='my_alcon'><div class='my_pic'><img src='"+replyCommentList[i].headUrl+"'></div><div class='my_con'><h4>"+replyCommentList[i].userName+"<span>"+replyCommentList[i].klassName+"  "+replyCommentList[i].schoolName+"</span></h4>";
					html+="<p class='p_text'>"+replyCommentList[i].content+"</p><p class='time_show'><span><a href=javascript:; value='"+replyCommentList[i].id+"' class='deleteSecComment'>删除</a></span></p></div></div>";       
				}
				$(".sec_dl").html(html);
			}
		});
	}

	//点击课程互动查询comment列表
	function getInteractionList(){
		var vid=$("#videoId").val();
		var type=1;
			$.ajax({
			url:"/mooc/course/comment.html",
			data:{type:type,vid:vid},
			type:"post",
			async:false,
			dataType:"json",
			success:function(data){	
				if(data!=null){
					var commentList = data.data;
					var html="";
					$("#comment").html(html);
					for(var i=0;i<commentList.length;i++){
					html+="<dl><dt><img src='"+commentList[i].headUrl+"'></dt><dd><input type='hidden' name='id' value='"+commentList[i].id+"'/><h4>"+commentList[i].userName+"<span>"+commentList[i].klassName+"  "+commentList[i].schoolName+"</span></h4>";
					html+="<p class='p_text'>"+commentList[i].content+"</p><div style='display:none' class='replyDiv'><div class='reply_od'><img src='"+commentList[i].headUrl+"'><h5><textarea class='replyText' name='content'></textarea>";
					html+="	<p class=''><a href='javascript:;' class='replyPublish'>回复</a></p></h5></div></div><div style='display:none' class='sec_dl cf'>";
					html+="</div><p class='time_show'><span><a href='javascript:;' class='reset_top' id='caltop"+commentList[i].id+"'></a><a href='javascript:;' class='reply' value='"+commentList[i].id+"'>回复</a><a href='javascript:;' class='deleteComment'>删除</a></span></p></dd></dl>";
					}
					$("#comment").html(html);
				}
			},
			error:function(){
				return false;
			}
	  	});
	}


	//点击我的答疑查询comment列表
	function getAnswerList(){
		var type=2;
		var vid=$("#videoId").val();
			$.ajax({
			url:"/mooc/course/answerComment.html",
			data:{type:type,vid:vid},
			type:"post",
			async:false,
			dataType:"json",
			success:function(data){	
				if(data!=null){
					var html = "";
					$(".answer_item").html(html);
					var answerCommentList = data.data;
					for(var i=0;i<answerCommentList.length;i++){
					html+="<dl><dt><img src='"+answerCommentList[i].headUrl+"'></dt><dd><input type='hidden' name='id' value="+answerCommentList[i].id+">";
					html+="<h4>"+answerCommentList[i].userName+"<span>"+answerCommentList[i].klassName+"  "+answerCommentList[i].schoolName+"</span></h4><p class='p_text'>"+answerCommentList[i].content+"</p>";
					html+="<div style='display:none' class='replyDiv'><div class='reply_od'><img src='"+answerCommentList[i].headUrl+"'><h5><textarea class='replyText' id='$velocityCount' name='content'></textarea>";
					html+="<input type='hidden' name='isLeaf' id='isNotLeaf' value='0'/><p class=''><a href='javascript:;' class='answerReplyPublish'>回复</a></p></h5></div></div>";
    html+="<div style='display:none' class='sec_dl cf'>";
    html+="</div>";
    html+="<p class='time_show'><span><a href='javascript:;' class='answerReply'>回复</a><a href='javascript:;' class='deleteAnswerComment'>删除</a></span></p></dd></dl>";  
					}
					$(".answer_item").html(html);
				}
			},
			error:function(){
				return false;
			}
	  	});
	}


	//点击课程评价查询comment列表
	function getEvaluateList(){
		var type=3;
		var vid=$("#videoId").val();
			$.ajax({
			url:"/mooc/course/evaluateComment.html",
			data:{type:type,vid:vid},
			type:"post",
			async:false,
			dataType:"json",
			success:function(data){	
				if(data!=null){
					var html = "";
					$("#evaluate_item").html(html);
					var evaluateCommentList = data.data;
					for(var i=0;i<evaluateCommentList.length;i++){
						html+="<dl><dt><img src='"+evaluateCommentList[i].headUrl+"'></dt><dd><h4><em><ul id='"+evaluateCommentList[i].id+"' class='starcon star'></ul>";
						html+="</em>"+evaluateCommentList[i].userName+"<span>"+evaluateCommentList[i].klassName+"  "+evaluateCommentList[i].schoolName+"</span></h4><p class='p_text'>"+evaluateCommentList[i].content+"</p><p class='time_show'></p></dd></dl>";  	
					}
					$("#evaluate_item").html(html);
					for(var i=0;i<evaluateCommentList.length;i++){
						var id=evaluateCommentList[i].id;
						var aa=evaluateCommentList[i].score;
							if(aa!=null){
							var html2="";
							$('#'+id).html(html2);
							for(j=0;j<aa;j++){
					 			html2+="<li class='starheigh'></li>";
					 		}
					 		$('#'+id).html(html2);
							}
					}
					$('#'+id).html(html2);
				}
			},
			error:function(){
				return false;
			}
	  	});
	}


	//发表评论
	function publish(content,type,isLeaf,score){
		var vid=$("#videoId").val();
		$.ajax({
			url:"/mooc/course/publish.html",
			data:{content:content,type:type,isLeaf:isLeaf,score:score,vid:vid},
			type:"post",
			async:false,
			dataType:"json",
			success:function(data){
				if(data!=null){
					if(type==1){
						$.post("/mooc/course/comment.html",{type:type},function(data1){
							getInteractionList();
						});
					}
					if(type==3){
						if(data.success){
						dialog.msg(data.data,{time:1200,icon:1});
						$.post("/mooc/course/evaluateComment.html",{type:type},function(data1){
							getEvaluateList();
						});
					}else{
						dialog.msg(data.message,{time:1200,icon:2});
						$.post("/mooc/course/evaluateComment.html",{type:type},function(data1){
							getEvaluateList();
						});
					}
					}
				}
			},
			error:function(){
				return false;
			}
	  });
	}
});


 
require(['paper'], function(paper){
	paper.paper_main();
})
