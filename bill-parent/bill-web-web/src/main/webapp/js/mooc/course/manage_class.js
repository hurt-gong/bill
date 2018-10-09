require.config({　　
    baseUrl: 'http://edu.bjhd.gov.cn/', 　　
    paths: {　
        "dialog": "js/lib/layer/layer",　
        "jqtransform": "js/lib/jqTransform/jqtransform",　　　　　
        "dialog": "js/lib/layer/layer",
        "headerSlideDown": "js/common/headerslideDown",
        //"webuploader": "js/lib/webuploader/webuploader",
    }　　
});


require(['jqtransform', 'js/admin/common/adminAlertBox','headerSlideDown',"dialog"], function(jqtransform, adminAlertBox,aaaa,dialog) {
    $(".adminBody form,.screenBar form").jqTransform();

});


require(['headerSlideDown','dialog'], function(aaaa,dialog) {
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
    $('.detailsTit a').on('click',function(){
        $(this).addClass('titStyle').siblings().removeClass('titStyle');
        $(".detailsCon>div").eq($(this).index()).show().siblings().hide();
    });
    $('#ul li').on('click',function(){
        $(this).addClass('plattit_style').siblings().removeClass('plattit_style');
        $(this).parent().siblings().children().eq($(this).index()).show().siblings().hide();
    });
    $('.announce span label').on('click',function(){
        $(this).addClass('label_checked').parent().siblings().find('label').removeClass('label_checked');
     });
    $('.ans1 span label').on('click',function(){
        $(this).addClass('label_checked').parent().siblings().find('label').removeClass('label_checked');
     });
    $('.exhibition span label').on('click',function(){
        $(this).addClass('label_checked').parent().siblings().find('label').removeClass('label_checked');
     });
    $('#change li').on('click',function(){
        $(this).addClass('li_click').siblings().removeClass('li_click');
        $('.ti_con').children().eq($(this).index()).show().siblings().hide();
    });
    $('.course_tit a').on('click',function(){
        $(this).addClass('changeA_style').siblings().removeClass('changeA_style');
        $('.course_con>div').eq($(this).index()).show().siblings().hide();
    })


    //图片轮播
    $(function(){
        var i=0;
        var j=0;
        var timer = setInterval(function autoPlay(){
            $('#pic>li').first().appendTo('#pic');
            i++;
            if(i>$('#num>li').length-1){i=0};
            $('#num>li').eq(i).addClass('li').siblings().removeClass('li');
        },2000);
        $('#tit a').on('click',function(){
            $(this).addClass('tapchange').siblings().removeClass('tapchange');
        });
        $('.up1').on('click',function(){
            $('.alertBox').show();
        });
        $('.up2').on('click',function(){
            $('.alertMange').show();
        });
        $('.closeAlert').on('click',function(){
            $('.alertMange').hide();
            $('.alertBox').hide();
        });
    });
require(['http://edu.bjhd.gov.cn/js/lib/uploadify/jquery.uploadify.min.js'],function(){
    $('#video_file_upload').uploadify({
        'auto' : true,
        'formData' : {
            'fcharset' : 'UTF-8',
            'writetoken' : 'E1FLnPnSIPY3IyE-UDQHOGWBXH0SKUJ9',
            'cataid':'1477635806414',
            'JSONRPC' : '{"title": "海淀教委视频"}'
        },
        'buttonText' : '选择视频文件',
        'fileTypeDesc' : '视频文件',
        'fileTypeExts' : '*.avi; *.mp4; *.mov',//文件类型过滤
        'swf' : 'http://edu.bjhd.gov.cn/js/lib/uploadify/uploadify.swf',
        'uploader' : 'http://v.polyv.net/uc/services/rest?method=uploadfile',
        'onUploadSuccess' : function(file, data, response) {
            var jsonobj = eval('(' + data + ')');
            var vid = jsonobj.data[0].vid;
            var first_image = jsonobj.data[0].first_image;
            var duration= jsonobj.data[0].duration;
            //var status=jsonobj.data[0].status;
            $("#listenUrl").val(vid);
            $("#cover").val(first_image);
            $("#duration").val(duration);
            //$("#status").val(status);
        }
    });
});


//查询视频列表
 getVideoList();

 //上传视频课程
$("body").on('click','.go_up',function(){
    var courseNo=$("#moocId").val();
    var listenUrl=$("#listenUrl").val();
    var title=$("#videotitle").val();
    var cover=$("#cover").val();
    var time=$("#duration").val();
    var arr=time.split(':');
    var duration=arr[0]*3600+arr[1]*60+arr[2]*1;
    $.ajax({
    url:"/mooc/course/addVideo.html",
    data:{courseNo:courseNo,listenUrl:listenUrl,title:title,cover:cover,duration:duration},
    type:"post",
    timeout:3000000000,
    async:false,
    success:function(msg){
        dialog.msg('上传成功！',{time:1200,icon:1});
        $('.alertBox').hide();
        getVideoList();
    },
    error:function(){
        return false;
    }
    });
});
    $("body").on('change',"#planfile",function(){
          var filePath=$(this).val();
            if(filePath.indexOf("pdf")!=-1 || filePath.indexOf("ppt")!=-1 || filePath.indexOf("pptx")!=-1 || filePath.indexOf("doc")!=-1 || filePath.indexOf("docx")!=-1){
                // $(".fileerrorTip").html("").hide();
                var arr=filePath.split('\\');
                var fileName=arr[arr.length-1];
                $(".planfile").html(fileName);
            }
        })
    
    //上传学案及课件
    $("body").on('click','#uploadsource',function(){
        var filename=$("#planfile").val();

      //   if(filename.substring(filename.lastIndexOf('.'))!='.pdf') {
      //   dialog.msg('抱歉，目前仅支持pdf格式的文件',{time:1200,icon:2});
      //   return;
      // }
      var url='';
      var moocId=$("#moocId").val();
      //上传
      var formData = new FormData($("#uploadForm")[0]);
      $.ajax({  
          url: '/sourceUpload/file.html',  
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (returndata) {  
              var msg = jQuery.parseJSON(returndata);
              url=msg.url;
          }  
      });
        $.post("/mooc/course/uploadsource.html",{url:url,filename:filename,moocId:moocId},function(msg){
            msg = jQuery.parseJSON(msg);
            $('.alertMange').hide();
            dialog.msg('上传成功！',{time:1200,icon:1});
            getSourceList();
        });
    });

    //点击修改课程弹出修改页面
    $(".updatecourse").on('click',function(){
         var id=$(".updatecourse").attr('value');
         window.location.href='/mooc/course/data.html?id='+id;
    });

    //保存修改
    $(".saveChange").on('click',function(){
        var id=$(".saveChange").attr('value');
        dialog.msg('修改成功！',{time:1200,icon:1});
        $("#update").submit();
    });  


    //删除微课程
    $(".delCourse").on('click',function(){
        var id=$(".delCourse").attr('value');
        dialog.alert('确定删除吗？',function(){
        window.location.href='/mooc/course/delete.html?id='+id;
        dialog.msg('删除成功！',{time:1200,icon:1});
        });
        
        
    });



    //章节的级联查询
    $('#phase1').change(function(){
        $("#phaseId").val($(this).val());
        getGradeByPhase();
    });

     $('#grade1').change(function(){
        $("#gradeId").val($(this).val());
        getSubjectByGrade();
    });

     //章节学科获取值
    $('#subject1').change(function(){
    $("#subjectId").val($(this).val());
        
    });


     //知识点的级联查询
    $('#phase2').change(function(){
        $("#phaseId").val($(this).val());
        getGradeByPhase();
    });

     $('#grade2').change(function(){
        $("#gradeId").val($(this).val());
        getSubjectByGrade();
    });

     //知识点学科获取值
    $('#subject2').change(function(){
    $("#subjectId").val($(this).val());
        
    });



     //点击选择章节
     $("body").on('click','#chooseSection',function(){
        var phaseId=$("#phaseId").val();
        var gradeId=$("#gradeId").val();
        var subjectId=$("#subjectId").val();
        if(phaseId==""){
            dialog.msg('请选择学段！',{time:1200,icon:2});
            $('.choosecha_box').hide();
            return;
        }
        if(gradeId==""){
            dialog.msg('请选择年级！',{time:1200,icon:2});
            $('.choosecha_box').hide();
            return;
        }
        if(subjectId==""){
            dialog.msg('请选择学科！',{time:1200,icon:2});
          $('.choosecha_box').hide();
          return;
        }
        $.post("/mooc/course/section.html",{phaseId:phaseId,gradeId:gradeId,subjectId:subjectId},function(msg){
            var msg = jQuery.parseJSON(msg);
            var sectionList=msg.data;
            var html="";
            var a=0;
            $("#sect1").html(html);
            for(var i=0;i<sectionList.length;i++){
            a++;
            html+="<li><div><i class='openbtn'></i><span></span><p>第"+a+"章 "+sectionList[i].name+"</p></div><ul class='selt2'><li><div><i class='openbtn section1'></i><span></span><p value="+sectionList[i].id+">二级章节</p></div>";
            html+="<ul class='selt3'></ul></li></ul></li>";
            }
            $("#sect1").html(html);
            $('.choosecha_box').show();
        });
     });


    //点击二级章节查询二级章节内容
    $('body').on('click','.section1',function(){
        var a = $(this).parent().find('p').attr('value');
        $("#section1Id").val(a);
        var section1Id = $("#section1Id").val();
        //根据一级章节的id查询二级章节
        $.post("/mooc/course/getSecSection.html",{section1Id:section1Id},function(msg){
            var msg = jQuery.parseJSON(msg);
            var secSectionList=msg.data;
            var html="";
            $(".selt3").html(html);
            for(var i=0;i<secSectionList.length;i++){
                html+="<li><em>"+secSectionList[i].name+"</em><b><input type='checkbox'><label for=''>选择</label></b></li>";
            }
            $(".selt3").html(html);
            
        });
    });



    //点击选择知识点
    $("body").on('click','#chooseKnow',function(){
        var phaseId=$("#phaseId").val();
        var gradeId=$("#gradeId").val();
        var subjectId=$("#subjectId").val();
        if(phaseId==""){
            dialog.msg('请选择学段！',{time:1200,icon:2});
            $('.choosekonw_box').hide();
            return;
        }
        if(gradeId==""){
            dialog.msg('请选择年级！',{time:1200,icon:2});
            $('.choosekonw_box').hide();
            return;
        }
        if(subjectId==""){
            dialog.msg('请选择学科！',{time:1200,icon:2});
          $('.choosekonw_box').hide();
          return;
        }
        $.post("/mooc/course/knowledge.html",{phaseId:phaseId,subjectId:subjectId},function(msg){
            var msg = jQuery.parseJSON(msg);
            var knowledgeList=msg.data;
            var html="";
            $("#know1").html(html);
            for(var i=0;i<knowledgeList.length;i++){
            html+="<li><div><i class='openbtn'></i><span></span><p>"+knowledgeList[i].name+"</p></div><ul class='selt2'><li><div><i class='openbtn know1'></i><span></span><p value="+knowledgeList[i].id+">二级知识点</p></div>";
            html+="<ul class='selt3'></ul></li></ul></li>";
            }
            $("#know1").html(html);
        $('.choosekonw_box').show();
        });
    });


    //点击二级知识点查询二级知识点内容
    $('body').on('click','.know1',function(){
        var a = $(this).parent().find('p').attr('value');
        $("#know1Id").val(a);
        var know1Id = $("#know1Id").val();
        //根据一级知识点的id查询二级知识点
        $.post("/mooc/course/getSecSecKnow.html",{know1Id:know1Id},function(msg){
            var msg = jQuery.parseJSON(msg);
            var secKnowList=msg.data;
            var html="";
            $(".selt3").html(html);
            for(var i=0;i<secKnowList.length;i++){
                html+="<li><em>"+secKnowList[i].name+"</em><b><input type='checkbox'><label for=''>选择</label></b></li>";
            }
            $(".selt3").html(html);
            
        });
    });


    //上传图片
   $(".selectimg").on('click',function(){
        $("#uploadPic").submit();
    });

   //选择系统图片
   $("#filePicker2").on('click',function(){
        $("#sysChooseImg").show();
   });



$('body').on('click','.openbtn',function(){
    $(this).toggleClass('openbtnbg').parent().siblings('ul').toggle();
    $(this).parent().parent().siblings('li').find('.openbtn').removeClass('openbtnbg').parent().siblings('ul').css('display','none');
    
 })
$('body').on('click','.choosebox_exit',function(){
    $(this).parent().css('display','none')
})
$('body').on('click','.choosebox_exit',function(){
    $(this).parent().parent().css('display','none')
})

//已上传课程、学习资源轮播
$(".video").on('click',function(){
    $(this).attr('class','video aa');
    $(".source").attr('class','source');
    $("#source").hide();
     $("#video").show();
     
});

$(".source").on('click',function(){
    $(this).attr('class','source aa');
    $(".video").attr('class','video');
    $("#video").hide();
    $("#source").show();
});


$("body").on('click','.video',function(){
    getVideoList();
});

    //点击学习资源查询学案及课件
    $("body").on('click',function(){
         getSourceList();
    });


//删除video
$("body").on('click','.delVideo',function(){
    var id=$(this).attr('value');
    dialog.alert('确定删除吗？',function(){
    $.ajax({
        url:"/mooc/course/delVideo.html",
        data:{id:id},
        type:"post",
        async:false,
        success:function(msg){ 
        if(msg!=null){
            dialog.msg('删除成功！',{time:1200,icon:1});
            getVideoList();
        } 
        },
        error:function(){
            return false;
        }
    });
    });
    
});


//删除学案及课件
$("body").on('click','.delSource',function(){
    var id=$(this).attr('value');
    dialog.alert('确定删除吗？',function(){
        $.post("/mooc/course/delSource.html",{id:id},function(data){
            if(data!=null){
            dialog.msg('删除成功！',{time:1200,icon:1});
            getSourceList();
            }
        });
    });
    
});


});


//查询学案及课件
function getSourceList(){
    var moocId=$("#moocId").val();
    //点击已上传课程查询列表
    $.ajax({
    url:"/mooc/course/getSource.html",
    data:{moocId:moocId},
    type:"post",
    async:false,
    success:function(msg){  
        var html = "<tr><th class='courseware_case'>课件及学案</th><th class='class_times'>下载人数</th><th>操作</th></tr>";
        $("#source").html(html);
        var msg=jQuery.parseJSON(msg);
       var sourceList = msg.data;
         for(var i=0;i<sourceList.length;i++){
            html += "<tr><td class='courseware_case'>"+sourceList[i].name+".rar</td><td class='class_times'>"+sourceList[i].downloadNum+"人下载</td><td class='set1'><a href='/mooc/course/downLoadSource.html?filename="+sourceList[i].name+"&path="+sourceList[i].url+"&id="+sourceList[i].id+"'>下载</a>  <a href='javascript:;' class='delSource' value='"+sourceList[i].id+"'>删除</a></td></tr>";
        }
        $("#source").html(html);  
    },
    error:function(){
        return false;
    }
    });
}

//查询已上传的课时视频列表
function getVideoList(){
    var courseNo=$("#moocId").val();
    var status = $("#status").attr('value');
    //点击已上传课程查询列表
    $.ajax({
    url:"/mooc/course/getVideo.html",
    data:{courseNo:courseNo},
    type:"post",
    async:false,
    success:function(msg){  
        var html2="<option>第1课时</option>";
        var html = "<tr><th class='class_time'>课时</th><th class='class_namem'>课程名称</th><th class='class_times'>时长</th><th class='class_times'>点播人数</th><th class='class_times'>习题作答人数</th><th>操作</th></tr>";
        $("#video").html(html);
        $("#chooseVideo").html(html2);
        var msg=jQuery.parseJSON(msg);
        var videoList = msg.data;
        var a=1;
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
            html += "<tr><td class='class_time'>课时"+(i+1)+"</td><td class='class_namem'>"+videoList[i].video.title+"</td><td class='class_times'>"+h+":"+m+":"+s+"</td><td class='dianbo class_times'>"+videoList[i].video.checkNum+"人点播</td>";
            if(videoList[i].paper!=null){
                html+="<td class='class_times'>"+videoList[i].paper.answerNum+"人</td><td class='set1'><a href='/mooc/course/details.html?id="+videoList[i].video.courseNo+"&listenUrl="+videoList[i].video.listenUrl+"&videoId="+videoList[i].video.id+"'>查看</a>  <a href='/mooc/course/details.html?id="+videoList[i].video.courseNo+"&listenUrl="+videoList[i].video.listenUrl+"&videoId="+videoList[i].video.id+"'>查看习题</a>  <a href='javascritp:;' class='delVideo' value='"+videoList[i].video.id+"'>删除</a></td></tr>";  
            }else{
                html+="<td class='class_times'>0人</td><td class='set1'><a href='/mooc/course/details.html?id="+videoList[i].video.courseNo+"&listenUrl="+videoList[i].video.listenUrl+"&videoId="+videoList[i].video.id+"'>查看</a>  <a href='/mooc/paper/teacher/uploadPaperIndex?moocId="+videoList[i].video.courseNo+"&periodId="+videoList[i].video.id+"&listenUrl="+videoList[i].video.listenUrl+"'>添加习题</a>  <a href='javascritp:;' class='delVideo' value='"+videoList[i].video.id+"'>删除</a></td></tr>"; 
            }
            html2+="<option>第"+(a+1)+"课时</option>";
            a++;
        }
        $("#video").html(html);
        $("#chooseVideo").html(html2);
    },
    error:function(){
        return false;
    }
    });
}
//<a href='javascritp:;' class='updateVideo' value='"+videoList[i].video.id+"'></a>
// $("body").on('click','.updateVideo',function(){
//     var videoId=$(this).attr('value');
//     $('.alertBox').show();
//     alert(videoId)
// });







//根据学段查询年级
    function getGradeByPhase() {
        var phaseId = $("#phaseId").val();
        $.ajax({
            url:"/mooc/course/grade",
            data:{phaseId:phaseId},
            type:"post",
            async:false,
            dataType:"json",
            success:function(msg){  
                var html = "<option>年级</option>";
                $(".grade").html(html);
                var gradeList = msg.data;
                for(var i=0;i<gradeList.length;i++){
                    html += "<option value='"+gradeList[i].id+"'>"+gradeList[i].name+"</option>";
                }
                $(".grade").html(html);     
            },
            error:function(){
                return false;
            }
        });
    }



    //根据年级查询学科
    function getSubjectByGrade() {
        var gradeId = $("#gradeId").val();
        $.ajax({
            url:"/mooc/course/subject",
            data:{gradeId:gradeId},
            type:"post",
            async:false,
            dataType:"json",
            success:function(msg){
                var html = "<option>学科</option>";
                $(".subject").html(html);
                var subjectList = msg.data;
                for(var i=0;i<subjectList.length;i++){
                    html += "<option value='"+subjectList[i].id+"'>"+subjectList[i].name+"</option>";
                }
                $(".subject").html(html);       
            },
            error:function(){
                return false;
            }
        });
    }