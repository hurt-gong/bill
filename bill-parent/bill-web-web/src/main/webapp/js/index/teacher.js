require.config({
    baseUrl: '/', 　
    paths: {
 "dialog": "js/lib/layer/layer",        
"jqtransform": "js/lib/jqTransform/jqtransform",
"My97DatePicker": "../lib/My97DatePicker/WdatePicker",
"headerSlideDown": "js/common/headerslideDown",
"webuploader": "js/lib/webuploader/webuploader",
    }          
});


require(['jqtransform', 'js/admin/common/adminAlertBox','headerSlideDown','dialog'], function(jqtransform, adminAlertBox,aaaa,dialog) {
    $(".adminBody form,.screenBar form").jqTransform();
     $(function () {
$('.checkbox').on('click',function(){
  if($(this).siblings("input[type='radio']").is(':checked')){
    $(this).removeClass('cur');
    $(this).siblings("input[type='radio']").removeProp('checked');
  }
  else{
    $(this).addClass('cur').parent().siblings('span').find('label').removeClass('cur');
    $(this).siblings("input[type='radio']").prop('checked','checked');
    $(this).parent().siblings('span').find('input').removeProp('checked');
    
  }
});
    });


     $('#tit a').on('click',function(){
$(this).addClass('checked').siblings().removeClass('checked');
$(this).parents('.discussion_box').find('.obedient_box_con').children().eq($(this).index()).show().siblings().hide();
     })
     $('.close_discussion_box').on('click',function(){
$(this).parent().parent().hide();
     })
     $('#tit1 a').on('click',function(){
$(this).addClass('btn').removeClass('aLink').siblings().removeClass('btn').addClass('aLink');
$(this).parents('.myCenter_con').find('.adminBody').children().eq($(this).index()).show().siblings().hide();
     });
     
     $('.close_discussion_box').on('click',function(){
$(this).parent().parent().hide();
     });
     $('#discussion').on('click',function(){
$('.discussion_box').show();
     });
     $('#case').on('click',function(){

$('.learning_case_box').show();
     });
     $('body').on('click','.exeCheck',function(){
if($(this).siblings("input[type='checkbox']").is(':checked')){
  $(this).removeClass('cur');
  $(this).siblings("input[type='checkbox']").removeProp('checked')
}
else{
  $(this).addClass('cur');
  $(this).siblings("input[type='checkbox']").prop('checked','checked')
}
    });
     require(['/js/lib/My97DatePicker/WdatePicker.js'],function(){
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
     });
   

    





//===========过程评价（老师） start==================================================================================
    //页面加载后再单独加载过程评价里的数据
    window.onload=Tevaluate();
    function Tevaluate(){
      $.post("/teacher/teacherIdx.html", {}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
//alert(msg.data);
$('.centerMain').html(msg.data);

      });
    }
 


    //过程评价--点击  X  关闭窗口
    $('body').on('click','.close_discussion_box',function(){
       $('.gg_box').attr('style','display:none');
      $(this).parent().parent().hide();
    });

    //过程评价--点击更多
    $('#more').on('click',function(){
//跳转到过程评价页面
window.location.href="/teacher/evaluation.html?klassName=全部";
$("#districtCourse").hide();
    });

    //（老师）选择班级进行评价筛选
    $('body').on('click','#evaluationSubmit',function(){
       var klassName = $('#klassName option:selected').text();
       $.post("/teacher/teacherMore.html", {klassName:klassName}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
$('.box_content').html(msg.data);
$('#klassName option:selected').text(klassName);
$(".adminBody form,.screenBar form").jqTransform();
      });
       
      });
  
    //过程评价--点击去评价
    $('body').on('click','#goEvaluate',function(){
      
$.post("/teacher/goEvalute.html", {}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
//alert(msg.data)
$('.newBox').html(msg.data);
//获取星星等级
var oLis = $('.starcon>li');
oLis.on('click',function(){
  $('#stars').attr('value',$(this).index()+1);  //给隐藏域赋值
  for(var i=0;i<=$(this).index();i++){
    oLis.eq(i).addClass('starheigh');
  }
  for(var i=($(this).index()+1);i<=oLis.length;i++){
    oLis.eq(i).removeClass('starheigh');
  }
})


$('.gg_box').show();
      });
      
    });

    //点击选择班级，，change事件
    $('body').on('change','#chooseKlass',function(){
      var klassName = $('#chooseKlass option:selected').text();
      //alert(klassName);
      $.post("/teacher/goEvalute.html", {klassName:klassName}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
//alert(msg.data);
$('.newBox').html(msg.data);
//获取星星等级
var oLis = $('.starcon>li');
oLis.on('click',function(){
 // alert($(this).index()+1);
  $('#stars').attr('value',$(this).index()+1);  //给隐藏域赋值
  for(var i=0;i<=$(this).index();i++){
    oLis.eq(i).addClass('starheigh');
  }
  for(var i=($(this).index()+1);i<=oLis.length;i++){
    oLis.eq(i).removeClass('starheigh');
  }
})
$('#chooseKlass option:selected').text(klassName);
      });
    });

    //点击发起评价
    $('body').on('click','#button',function(){
      //设置班级name值
      var klassName = $('#chooseKlass option:selected').text();
      //获取并设置评价内容
      var content = $('#evaluateContent').val();
      //获取星星等级
      var stars = $('#stars').attr('value');
      
      
      //获取并设置学生姓名，id 
      var ids = '';  
      $("input[type=checkbox]:checked").each(function() {
ids += $(this).attr('value')+',';
      });
      ids=ids.substring(0,ids.length-1);
      if(ids.replace(',','').trim()==''){
dialog.msg('请选择学生', {time:1200,icon:6});
return;
      }
      if(content==''){
dialog.msg('请填写评价内容', {time:1200,icon:6});
return;
      }
      if(stars==''){
dialog.msg('请选择星级!', {time:1200,icon:6});
return;
      }
      
      $.post("/teacher/addEvaluation.html", {klassName:klassName,content:content,ids:ids,star:stars}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
dialog.msg(msg.data,{time:1500,icon:1})
$.post("/teacher/teacherIdx.html", {}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
$('.centerMain').html(msg.data);

      });
$('.gg_box').hide();
      });
    });


    //点击删除评价
    $('body').on('click','#deleteEvaluation',function(){
      var evaluationId = $(this).attr('value');
dialog.confirm('确定要删除吗？',function(){
  $.post("/teacher/deleteEvaluation.html", {evaluationId:evaluationId}
,function(msg){
  dialog.msg(msg,{time:1500,icon:1});
    $.post("/teacher/teacherIdx.html", {}
      ,function(msg){
      var msg = jQuery.parseJSON(msg);
      $('.centerMain').html(msg.data);
    });
});
});
    });

    //点击过程评价
   $('body').on('click','#subjectSubmit',function(){
      var klassName = $('#klassName option:selected').text();
      $('#klassNameId').attr('value',klassName);
      $('#evaluationFormId').submit();
      $('#klassNameId').attr('value',klassName);
   })



//===========过程评价（老师） end==================================================================================



//===========发学案（教师） start==================================================================================
  
  //页面加载后再单独加载过程评价里的数据
    window.onload=newPlan();
    function newPlan(){
      $.post("/learnplan/newLearnPlan.html", {}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
$('.learnPlanClass').html(msg.data);
$(".adminBody form,.screenBar form").jqTransform();
      });
    }


  //点击发学案
  $('body').on('click','#sendLearnPlan',function(){

      var subjectName = $('#subjectClass option:selected').text()
      if(subjectName=='--请选择--'){
//alert('请选择学案学科');
dialog.tips('请选择学案学科!', '#subjectClass', {
  tips: [2, '#3595CC'],
  time: 2000
});
return;
      }
      var forKlassName = $('#planClass option:selected').text();
      if(forKlassName=='--请选择--'){
dialog.msg('请选择推荐班级', {time: 1500, icon:6});
return;
      }
      
      var fileName = $('#fileUp').val();
      //后缀名
      var smallName = fileName.substring(fileName.lastIndexOf('.'));
      if (smallName != '.pdf' && smallName != '.doc' && smallName != '.docx' && smallName != '.xls' && smallName != '.xlsx' && smallName != '.ppt' && smallName != '.pptx' ) {
//alert('抱歉，目前仅支持pdf格式的文件');
dialog.tips('抱歉，目前仅支持.pdf .doc .docx .xls .xlsx .ppt .pptx 格式的文件', '#fileUp', {
  tips: [2, '#3595CC'],
  time: 2000
});
return;
      };

      var storeUrl='';  
      //上传
      var formData = new FormData($( "#uploadForm" )[0]);
      $.ajax({  
  url: '/DocUpload/file.html' ,  
  type: 'POST',  
  data: formData,  
  async: false,  
  cache: false,  
  contentType: false,  
  processData: false,  
  success: function (returndata) {  
      var msg = jQuery.parseJSON(returndata);
      storeUrl=msg.url; 
  }  
      }); 
      
      $.post("/learnplan/sendNewPlan.html", {forKlassName:forKlassName,subjectName:subjectName,fileName:fileName,storeUrl:storeUrl}
      ,function(msg){
msg = jQuery.parseJSON(msg);
//弹窗
dialog.msg(msg.data, {time: 3000, icon:1});
$('.learning_case_box').hide();
$.post("/learnplan/newLearnPlan.html", {}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
$('.learnPlanClass').html(msg.data);
      });
      });
 


  });

 //点击下载
    $('body').on('click','.download_plan',function(){
      var path=$(this).attr('value')
      var name = $(this).attr('name');
      window.location.href='/learnplan/downLearnPlan.html?filename='+name+'&path='+path;
    })

    // 点击返回
  $('body').on('click','.backButton',function(){
      window.history.back();
    })


//===========发学案（教师） end==================================================================================

//===========发起讨论帖（教师） start==================================================================================
 
  $('#discussion').on('click',function(){
$.post("/group/group/getGroupList.html", {}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
$('.chooseGroup').html(msg.data);
$.post("/group/group/getGroupList2.html", {}
      ,function(msg){
var msg = jQuery.parseJSON(msg);
$('.chooseGroup2').html(msg.data);
      });
      });

$('.discussion_box').show();


     });


  //点击发表普通讨论帖
  $('#sendTopic').on('click',function(){
      var title = $('#topicTitle').val();
      var content = $('#topicContent').val();
      var groupName = $('#sendChoose option:selected').text();
      if(title==""){
dialog.tips('标题不能为空！', '#topicTitle', {
  tips: [2, '#3595CC'],
  time: 2000
});
return;
      }
      if(content==""){
dialog.tips('内容不能为空！', '#topicContent', {
  tips: [2, '#3595CC'],
  time: 2000
});
return;
      }
      if(groupName=="请选择"){
dialog.msg('请选择班群', {time: 1500, icon:6});
return;
      }
      $.post("/group/group/sendTopic.html", {title:title,content:content,groupName:groupName}
      ,function(msg){
dialog.msg(msg, {time: 1500, icon:1});
$('.discussion_box').hide();
$('#topicTitle').val('');
$('#topicContent').val('');
      });
  });

  //点击添加投票选项
  $('body').on('click','#addVoteOption',function(){
 
    $('.voteOptions').html("<div class='change_class cf'><span><label for='' class='label_title new_add'>00</label><input type='text' name='voteOn' id='' class='voteOn'><a href='javascript:;' style='color:#0082c8' class='moveOut'>  移除</a></span></div><div class='insert'><div class='_add_change cf'><label for='' class='label_title new_add'>00</label><a href='javascript:;' id='addVoteOption'>添加投票选项</a></div></div>");
    $('.voteOptions').attr('class','');
    $('.insert').attr('class','voteOptions');
  });

  //点击移除投票选项
  $('body').on('click','.moveOut',function(){
    $(this).parent().parent().html('');
  });

  //点击发表投票讨论帖
  $('#sendVoteTopic').on('click',function(){

    var voteType = $('.checkbox-con').find($("input[type='radio']:checked")).val();//投票类型
    var voteTitle = $('#voteTitle').val();//投票标题
    
    var voteContent = $('#voteContent').val();//投票内容
    var options = document.getElementsByName('voteOn');//投票选项
    var allOption = '';
    for(var i=0;i<options.length;i++){
      var option = options[i];
      allOption += option.value+',';
    }
    allOption = allOption.substring(0,allOption.length-1);//所有的投票选项 拼接成字符串
    var endTime = $('#time').val();
    var crTime = new Date();
    var groupName = $('#sendChoose2 option:selected').text();
      if(voteTitle==""){
dialog.tips('标题不能为空！', '#voteTitle', {
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
dialog.tips('请输入内容！', '#voteContent', {
  tips: [2, '#3595CC'],
  time: 2000
});
return;
      }
       if(groupName=="请选择"){
dialog.msg('请选择班群', {time: 1500, icon:6});
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
      $.post("/group/group/sendVoteTopic.html", {groupName:groupName,voteType:voteType,title:voteTitle,content:voteContent,allOption:allOption,endTime:endTime,crTime:crTime}
      ,function(msg){
 dialog.msg('成功！', {time: 1500, icon:1});
 $('.discussion_box').hide();
 $('#voteTitle').val('');//投票标题
 $('#voteContent').val('');//投票内容
      }); 

  });




//===========发讨论帖（教师） end==================================================================================


//===========区推荐课程开始==================================================================================
getSubject();
directCourseList();
//查询学科
function getSubject(){
  $.post("/mooc/course/allSubject.html",{},function(data){
    var data1=jQuery.parseJSON(data);
    var subjectList=data1.data;
    var leng=subjectList.length;
    if(leng>3){
    var html="";
    $("#subject").html(html);
    for(var i=0;i<3;i++){
      html+="<a href='javascript:;' value="+subjectList[i].id+" class='subj'>"+subjectList[i].name+"</a>";
    }
    html+="<a href='/mooc/course/directCourseToView.html' class='aStyle' id='moresubj'>更多 >></a>";
    $("#subject").html(html);
    }
    if(leng<3){
    var html="";
    $("#subject").html(html);
    for(var i=0;i<subjectList.length;i++){
      html+="<a href='javascript:;' value="+subjectList[i].id+" class='subj'>"+subjectList[i].name+"</a>";
    }
    $("#subject").html(html);
    }
  });

  // $("body").on('click','#moresubj',function(){
  //   $.post("/mooc/course/allSubject.html",{},function(data){
  //   var data1=jQuery.parseJSON(data);
  //   var subjectList=data1.data;
  //   var leng=subjectList.length;
  //   var html="";
  //   $("#subject").html(html);
  //   for(var i=0;i<subjectList.length;i++){
  //     html+="<a href='javascript:;' value="+subjectList[i].id+" class='subj'>"+subjectList[i].name+"</a>";
  //   }
  //   $("#subject").html(html);
  //   });
  // });
}

//点击变换
$("body").on('click','.subj',function(){
  $('.subj').attr('class','subj');
  $(this).attr('class','aStyle subj');
  $("#subjectId").val($(this).attr('value'));
  directCourseList();
});


//区级推荐课程查询
function directCourseList(){
  var subjectId=$("#subjectId").val();
  $.post("/mooc/course/directCourse.html",{subjectId:subjectId},function(msg){
    var msg=jQuery.parseJSON(msg);
    var moocList=msg.data;
    var leng=moocList.length;
    if(leng==0){
      $("#dictCour").html("");
      return;
    }
    if(leng>3){
    var html="";
    $("#dictCour").html(html);
    for(var i=0;i<3;i++){
      html+="<li><span>"+moocList[i].teacherName+"</span><div><h2>"+moocList[i].title+"</h2><p>"+moocList[i].content+"</p></div></li>";
    }
    html+="<a href='/mooc/course/directCourseToView.html' class='aStyle' id='moreCourse' style='float:right;color:#0058c3;margin-top:10px;margin-right:20px;'>更多 >></a>";
    $("#dictCour").html(html);
    }
    if(leng<=3){
    var html="";
    $("#dictCour").html(html);
    for(var i=0;i<leng;i++){
      html+="<li><span>"+moocList[i].teacherName+"</span><div><h2>"+moocList[i].title+"</h2><p>"+moocList[i].content+"</p></div></li>";
    }
    //html+="<a href='javascript:;' class='aStyle' id='moreCourse' style='float:right'>更多 >></a>";
    $("#dictCour").html(html);
    }

  });

  //   $("body").on('click','#moreCourse',function(){
  //     $("#districtCourse").show();
  //   $.post("/mooc/course/directCourse.html",{subjectId:subjectId},function(data){
  //   var data1=jQuery.parseJSON(data);
  //   var moocList=data1.data;
  //   var html=" ";
  //   $("#districtCourseContent").html(html);
  //   for(var i=0;i<moocList.length;i++){
  //     html+="<li><span>一天前</span><div><h2>"+moocList[i].title+"</h2><p>"+moocList[i].content+"</p></div></li>";
  //   }
  //   $("#districtCourseContent").html(html);
  //   });
  // });
}


});
//===========区推荐课程结束==================================================================================
/********学案预览******************/  
require(['/js/admin/flexpaper/js/flexpaper.js','/js/admin/flexpaper/js/flexpaper_handlers.js'],function(){
  var url = $('#viewerPlaceHolder').attr('value');
  
  $('#viewerPlaceHolder').FlexPaperViewer(
    { 
config : {      
   //SWFFile : '/js/lib/flexpaper/2.swf',
   //SWFFile : 'http://js.bjjh.org.cn/sea-modules/flexpaper/pdf/11.swf',
   SWFFile : url,
   jsDirectory:'/js/lib/flexpaper/js/',
   Scale : 0.6,
   ZoomTransition : 'easeOut',
   ZoomTime : 0.5,
   ZoomInterval : 0.2,
   FitPageOnLoad : true,
   FitWidthOnLoad : false,
   FullScreenAsMaxWindow : false,
   ProgressiveLoading : false,
   MinZoomSize : 0.2,
   MaxZoomSize : 5,
   SearchMatchAll : false,
   InitViewMode : 'Portrait',
   RenderingOrder : 'flash',
   StartAtPage : '',
   ViewModeToolsVisible : true,
   ZoomToolsVisible : true,
   NavToolsVisible : true,
   CursorToolsVisible : true,
   SearchToolsVisible : true,
   WMode : 'window',
   localeChain: 'en_US'
       }
     });

})
  
  



