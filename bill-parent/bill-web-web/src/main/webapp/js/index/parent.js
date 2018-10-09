require.config({
    baseUrl: 'http://edu.bjhd.gov.cn/', 　
    paths: {　　　　　　
        "dialog": "../lib/layer/layer",　　　　
        "jqtransform": "js/lib/jqTransform/jqtransform",
        "My97DatePicker": "../lib/My97DatePicker/WdatePicker",
        "headerSlideDown": "js/common/headerslideDown",
        "webuploader": "js/lib/webuploader/webuploader",
        　　　　
    }　　
});
require(['jqtransform', 'js/admin/common/adminAlertBox','headerSlideDown'], function(jqtransform, adminAlertBox,aaaa) {
	$(".adminBody form,.screenBar form").jqTransform();

//===========过程评价（家长） start==================================================================================
	//页面加载后再单独加载过程评价里的数据
    window.onload=Pevaluate();
    function Pevaluate(){
      $.post("/parent/parentIdx.html", {}
                      ,function(msg){
                        var msg = jQuery.parseJSON(msg);
                        //alert(msg.data);
                        $('.centerMain').html(msg.data);
                      });
    }
  //点击更多
	$('body').on('click','#parentMore',function(){
		window.location.href="/parent/evaluation.html?subjectName=全部";
	})
	//点击X 关闭弹窗
	$('.close_discussion_box').on('click',function(){
        $(this).parent().parent().hide();
     })

	//（家长）选择学科进行评价筛选
    $('body').on('click','#subjectSubmit',function(){
       var subjectName = $('#subjectName option:selected').text();
       //alert(subjectName);
       $.post("/parent/parentMore.html", {subjectName:subjectName}
              ,function(msg){
                var msg = jQuery.parseJSON(msg);
                $('.box_content').html(msg.data);
                $('#subjectName option:selected').text(subjectName);
                $(".adminBody form,.screenBar form").jqTransform();
              });
       
      });
    //点击学科
    $('body').on('click','.hello',function(){
    	$('.aStyle').attr('class','hello');
    	$(this).attr('class','aStyle');
    	var name =$(this).attr('value');
    	$.post("/parent/parentIdx.html", {subjetName:name}
                      ,function(msg){
                        var msg = jQuery.parseJSON(msg);
                        //alert(msg.data);
                        $('.centerMain').html(msg.data);
                      });
    })

      //点击过程评价
     $('body').on('click','#subjectSubmit',function(){
        var klassName = $('#subjectName option:selected').text();
        $('#subjectNameId').attr('value',klassName);
        $('#evaluationFormId').submit();
        $('#subjectNameId').attr('value',klassName);
     })
//===========过程评价（家长） end==================================================================================



























});
