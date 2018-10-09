require.config({
    baseUrl: 'http://edu.bjhd.gov.cn/', 　
    paths: {　　　　　　
        "dialog": "js/lib/layer/layer",
        "jqtransform": "js/lib/jqTransform/jqtransform",
        "webuploader": "js/lib/webuploader/webuploader",
        "headerSlideDown": "js/common/headerslideDown",
        　　　　
    }　　
});


require(['jqtransform', 'js/admin/common/adminAlertBox','headerSlideDown','dialog'], function(jqtransform, adminAlertBox,aaaa,dialog) {
	$(".screenBar form").jqTransform();
	 $('.sub').on('click',function(){
	 	$('.manageBox').show();
	 });
	 $('.closeAlert').on('click',function(){
	 	$(this).parent().parent().hide();
	 });
    require(['webuploader'], function(webuploader) {
        // 初始化Web Uploader
        var uploader = webuploader.create({

            // 选完文件后，是否自动上传。
            auto: true,

            // swf文件路径
            swf: 'http://edu.bjhd.gov.cn/js/lib/webuploader/Uploader.swf',

            // 文件接收服务端。
            server: 'http://webuploader.duapp.com/server/fileupload.php',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
           // 初始化Web Uploader
        var uploader = webuploader.create({

            // 选完文件后，是否自动上传。
            auto: true,

            // swf文件路径
            swf: 'http://edu.bjhd.gov.cn/js/lib/webuploader/Uploader.swf',

            // 文件接收服务端。
            server: 'http://webuploader.duapp.com/server/fileupload.php',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker1',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
           // 初始化Web Uploader
        var uploader = webuploader.create({

            // 选完文件后，是否自动上传。
            auto: true,

            // swf文件路径
            swf: 'http://edu.bjhd.gov.cn/js/lib/webuploader/Uploader.swf',

            // 文件接收服务端。
            server: 'http://webuploader.duapp.com/server/fileupload.php',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker2',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
     })

$('.changeLeft').find('label').on('click',function(){
	if($(this).siblings("input[type='checkbox']").is(':checked')){
		$(this).removeClass('asncheck');
		 $(this).siblings("input[type='checkbox']").removeProp('checked')
      	$('.mainCon').find('label').removeClass('asncheck');
      	$('.mainCon').find("input[type='checkbox']").removeProp('checked')
    }
    else{
      $(this).addClass('asncheck');
      $(this).siblings("input[type='checkbox']").prop('checked','checked');
      $('.mainCon').find('label').addClass('asncheck');
      $('.mainCon').find("input[type='checkbox']").prop('checked','checked');
    }
});
$('.mainCon').find('label').on('click',function(){
	console.log($(this));
	$('.changeLeft').find('label').removeClass('asncheck').siblings("input[type='checkbox']").removeProp('checked');
	if($(this).siblings("input[type='checkbox']").is(':checked')){
		$(this).removeClass('asncheck');
		 $(this).siblings("input[type='checkbox']").removeProp('checked')
     }
    else{
      $(this).addClass('asncheck');
      $(this).siblings("input[type='checkbox']").prop('checked','checked');
    }
});

	//点击学段事件
	$(".phase").on('click',function(){
		$("#phaseId").val($(this).attr('value'));
		$("#gradeId").val("");
		$("#subjectId").val("");
		$("#key1Id").val("");
		$("#schoolClass").submit();
	});	

	//点击年级事件
	$('.grade').on('click',function(){
		$("#gradeId").val($(this).attr('value'));
		$("#subjectId").val("");
		$("#key1Id").val("");
		$("#schoolClass").submit();
	});


	//点击学科事件
	$('body').on('click','.subject',function(){
		 $("#subjectId").val($(this).attr('value'));
		 $("#key1Id").val("");
		 $("#schoolClass").submit();
	});


	//设为区推荐课程
	$("body").on("click",".setClass",function(){
		var moocId=$(this).attr('value');
		 $.post("/mooc/course/setCommend.html",{moocId:moocId},function(data){
		 	if(data!=null){
		 		$("#schoolClass").submit();
		 	}
		 });
		dialog.msg('设置成功!',{time:2000,icon:1});
	});




	//隐藏区公开课
	 $('body').on('click','.hidecourse',function(){
	 	var moocId=$(this).attr('value');
		 $.post("/mooc/course/hideCourse.html",{moocId:moocId},function(data){
		 	if(data!=null){
		 		$("#schoolClass").submit();
		 	}
		 });
		 dialog.msg('操作成功!',{time:2000,icon:1});
	 });


	 //删除区公开课
	 $('body').on('click','.deletecourse',function(){
	 	var moocId=$(this).attr('value');
		 $.post("/mooc/course/deleteCourse.html",{moocId:moocId},function(data){
		 	if(data!=null){
		 		$("#schoolClass").submit();
		 	}
		 });
		 dialog.msg('删除成功!',{time:2000,icon:1});
	 });


	 //批量删除
	 $('body').on('click','.batchdelete',function(){
	 	var a = $('.asncheck').attr('class');
	 	if(a==null){
	 		dialog.msg('您尚未选择要删除的课程，请先选择!',{time:6000,icon:2});
	 		return;
	 	}
	 	if (a=='asncheck') {
	 		$('.asncheck').prev().attr('checked','checked');
	 	}
	 	var aar = '';
	 	$('input[type=checkbox]:checked').each(function(){
	 		aar+= $(this).attr('value')+",";
	 	});
	 	var array = aar.substring(0,aar.length-1);
	 	$.ajax({
    	url:"/mooc/course/batchDelete.html",
    	data:{array:array},
    	type:"post",
    	async:false,
    	dataType:"json",
    	success:function(data){
    		 if(data.success){
	 	 		dialog.msg(data.data,{time:2000,icon:1});
	 	 		$("#schoolClass").submit();
	 	 		window.location.reload();
	 	 	}
    	}
		});
	 });




	 //展开收起功能
	 $("body").on('click','.open',function(){

	 	$(".hide").parent().parent().parent().parent().parent().next().hide();
	 	$(".hide").html('展开');
	 	$(".hide").attr('class','open');
	 	$(this).parent().parent().parent().parent().parent().next().show();
	 	$(this).attr('class','open hide');
	 	$(this).html('收起');
	 	var courseNo=$(this).attr('value');
	 	$.post("/mooc/course/getVideo.html",{courseNo:courseNo},function(msg){
	 		var msg=jQuery.parseJSON(msg);
	 		var videoList=msg.data;
	 		var html="";
	 		$('.video').html(html);
	 		for(var i=0;i<videoList.length;i++){
	 		var time=videoList[i].video.duration;
            var h=Math.floor(time/3600);
            var m=Math.floor((time-h*3600)/60);
            var s=(time-h*3600)%60;
            if(h<10){
                h="0"+h;
            }
            if(m<10){
                m="0"+m;
            }
            if(s<10){
                s="0"+s;
            }
	 			html+="<li class='cf'><p class='leftP'><span class='classOne'>课时"+(i+1)+"</span><span>《"+videoList[i].video.title+"》</span></p>";
                html+="<p class='rightP'><b>时长:"+h+":"+m+":"+s+"</b><a href='/mooc/course/details.html?id="+videoList[i].video.courseNo+"&listenUrl="+videoList[i].video.listenUrl+"&videoId="+videoList[i].video.id+"'>查看</a><a href='javascript:;' class='delVideo' value='"+videoList[i].id+"'>删除该课时</a></p></li>";
	 		}
	 		$('.video').html(html);
	 	});
	  });


	  $("body").on('click','.hide',function(){
	 	$(this).parent().parent().parent().parent().parent().next().hide();
	 	$(this).attr('class','open');
	 	$(this).html('展开');
	 });



	//删除video
	$("body").on('click','.delVideo',function(){
    var id=$(this).attr('value');
    $.ajax({
    	url:"/mooc/course/delVideo.html",
    	data:{id:id},
    	type:"post",
    	success:function(msg){
    		var courseNo=$('.delVideo').parent().parent().parent().attr('value');
	 		$.post("/mooc/course/getVideo.html",{courseNo:courseNo},function(msg){
	 		var msg=jQuery.parseJSON(msg);
	 		var videoList=msg.data;
	 		var html="";
	 		$('.video').html(html);
	 		for(var i=0;i<videoList.length;i++){
	 		var time=videoList[i].video.duration;
            var h=Math.floor(time/3600);
            var m=Math.floor((time-h*3600)/60);
            var s=(time-h*3600)%60;
            if(h<10){
                h="0"+h;
            }
            if(m<10){
                m="0"+m;
            }
            if(s<10){
                s="0"+s;
            }
	 			html+="<li class='cf'><p class='leftP'><span class='classOne'>课时"+(i+1)+"</span><span>《"+videoList[i].video.title+"》</span></p>";
	            html+="<p class='rightP'><b>时长:"+h+":"+m+":"+s+"</b><a href='/mooc/course/details.html?id="+videoList[i].video.courseNo+"&listenUrl="+videoList[i].video.listenUrl+"&videoId="+videoList[i].video.id+"'>查看</a><a href='javascript:;' class='delVideo' value='"+videoList[i].id+"'>删除该课时</a></p></li>";
	 		}
	 		$('.video').html(html);
		 	});
    },
    error:function(){
        return false;
    }
    });
dialog.msg('删除成功!',{time:2000,icon:1});
});

	  //校公开课轮播管理
	  $("body").on('click','.move',function(){
	  	$('.alertBox').show();
	  });
});