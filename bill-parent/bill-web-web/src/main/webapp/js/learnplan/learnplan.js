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

    //点击选择学科名称，查询学案列表
    $('body').on('click','#chooseSource',function(){
$('.lihover').attr('class','');
$(this).attr('class','lihover');
var subjectName = $('.lihover').text();

if(subjectName=='全部'){
  window.location.reload();
}

$.post('/learnplan/chosubject.html',{subjectName:subjectName}
    ,function(msg){
       var msg = jQuery.parseJSON(msg);
       //alert(msg.data);
       $('.chooseSource').html(msg.data);     
    });

    })

    //点击删除学案
    $('body').on('click','#deletePlan',function(){
      //alert('删除');
      var id = $(this).attr('value');
//if(dialog.confirm('确定删除该学案吗？')){
dialog.confirm('确定删除该学案吗？',function(){
  $.post('/learnplan/deletePlan.html',{id:id}
,function(msg){
   var msg = jQuery.parseJSON(msg);
      var subjectName = $('.lihover').text();
       if(subjectName=='全部'){
    dialog.alert(msg.data,function(){
      window.location.reload();
    });
  return;
} else {
  dialog.msg(msg.data,{time:1200,icon:1});
  $.post('/learnplan/chosubject.html',{subjectName:subjectName}
    ,function(msg){
       var msg = jQuery.parseJSON(msg);
       $('.chooseSource').html(msg.data);
    });
}
   });
})
  
    });

    //点击下载
    $('body').on('click','#down',function(){

      var path=$(this).attr('value')
      var name = $(this).attr('name');
      window.location.href='/learnplan/downLearnPlan.html?filename='+name+'&path='+path;
    })

    //点击学案标题进入阅览
    $('body').on('click','.inLearnPlan',function(){
      var url = $(this).attr('value');
      window.location.href='/learnplan/learnPlanPreview.html?url='+url;
    });

    // 点击弹出发学案弹窗 
    $('#case').on('click',function(){
    	$('.learnPlanClass').show();
$('.learning_case_box').show();
     });
    
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
      $('.learnPlanClass').hide();
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

  //关闭
  $('body').on('click','.close_discussion_box',function(){
$(this).parent().parent().hide();
$('.learnPlanClass').hide();
     });


















});