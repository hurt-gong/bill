fix_select('.mySelect');
//添加一级目录
$('.twoRowLeft a.addBtn').on('click',function(){
	$('.alertMange').attr('value','first');
	$(".alertMange").show();
});
//添加二级目录
$('.twoRowRight .dBtn').click(function(){
	$('.alertMange').attr('value','secend');
	$(".alertMange").show();
	$("#sectionName").val('');
});
//add first or senect section
$('.alertMange .sure').on('click',function(){
	var sectionName = $('#sectionName').val();
	var bookId = $('.bookby').val();
	var sectionId='';
	if(sectionName.length<2){
		layer.tips('一级目录要大于2位！！！', '#sectionName', {tips: [1, '#3595CC'],time: 4000});
		return ;
	}
	var pid=0;
	var saveType=$('.alertMange').attr('value');
	if(saveType=='secend'){
		pid=$('.twoRowLeft li.active').attr('value');
		sectionId=pid;
	}
	var url = "/manage/distric/section/insertSection.html";
	var data ={"bookId":bookId,"pId":pid,"name":sectionName};
	var callBack=function(msg){
		layer.msg('保存成功');
		seachSectin(sectionId);
	}
	if(saveType=='edit'){
		url="/manage/distric/section/editSection.html";
		sectionId=$('.twoRowLeft li.active').attr('value');
		var id = $('.alertMange').attr('val');
		data ={"id":id,"name":sectionName};

	}
	publicAjax(url,data,callBack);
});

$('.alertMange .cel , .clearBox .cel').on('click',function(){
	$(".alertMange").hide();
	$(".clearBox").hide();
});
// delete first section
$('.twoRowLeft a.del').on('click',function(){
	$(".clearBox").show();
	$(".clearBox").attr('value','first');
	$('#delId').val($(this).parent().attr('value'));
});
// delete second section
$('.twoRowRight a.del').on('click',function(){
	$(".clearBox").show();
	$(".clearBox").attr('value','second');
	$('#delId').val($(this).parent().attr('value'));
});
//edit section 
$('li>a.edit').on('click',function(){
	var sectionId = $(this).parent().attr('value');
	$('.alertMange').attr('value','edit');
	$('.alertMange').attr('val',sectionId);
	$('.alertMange #sectionName').val($(this).siblings('span').text());
	$('.alertMange').show();
});
$('#sureDel').on('click',function(){
	var sectionId = $('#delId').val();
	var sectionType=$(".clearBox").attr('value');
	var url = "/manage/distric/section/deleteSection.html";
	var data ={"id":sectionId};
	var callBack=function(msg){
		layer.msg('刪除成功');
		if(sectionType=='first'){
			if($('.twoRowLeft li[value='+sectionId+']').hasClass('active')){
			$('.twoRowLeft li.active').remove();
			$('.twoRowRight ul').html('');
			}else{
				$('.twoRowLeft li[value='+sectionId+']').remove();
			}
		}else{
			$('.twoRowLeft li[value='+sectionId+']').remove();
		}
		$(".clearBox").hide()
	}
	publicAjax(url,data,callBack);
});


$("#txtfile").change(function(){
	if("" == $('#txtfile').val()){
		return ;
	}
	
	var option = {
	   		type:'post',
	   		url:'/manage/distric/section/uploadSection.html',
		   	dataType:"json",
		    success:function(data){
				var msg = jQuery.parseJSON(data);
		    	if(true == msg.success){
			    	alert("上传成功");
		    	}else{
					alert("上传失败");
		    	}
		    }
	   	}
	$("#form1").ajaxSubmit(option);
});


function phBySub(){
	var phaseId = $('.phBy').val();
	$(".subby").html("");
	if(phaseId != null){
		$.ajax({
			type: "post",
			url: "/manage/distric/section/subjectJson.html",
			data: {phaseId:phaseId},
			dataType:"json",
			success: function (msg) {
				var html = "";
				var subId = "";
				for (var i = 0; i < msg.data.length; i++) {
					if(subId == ""){
						subId = msg.data[i].id;
					}
					html += "<option value='" + msg.data[i].id + "'>" + msg.data[i].name + "</option>";
				}
				$(".subby").html(html);
				fix_select('select#my_updated_select_box');
				subByBook();
			}
		});
	}else{
		fix_select('select#my_updated_select_box');
		subByBook();
	}
}

function subByBook(){
	var phby = $('.phBy').val();
	var subby = $('.subby').val();
	$(".bookby").html("");
	if(subby != null && phby != null){
		$.ajax(
		{
			type: "post",
			url: "/manage/distric/section/bookJson.html",
			data: {
				phaseId:phby,
				subjectId:subby
			},
			success: function (msg) {
				var data = jQuery.parseJSON(msg);
				var html = "";
				var subId = "";
				for (var i = 0; i < data.data.length; i++) {
					if(subId == ""){
						subId = data.data[i].id;
					}
					html += "<option value='" + data.data[i].id + "'>" + data.data[i].name + "</option>";
				}
				$(".bookby").html(html);
				fix_select('select#my_updated_select_box2');
			}
		});
	}else{
		fix_select('select#my_updated_select_box2');
	}
}
	// ok button click
	$('._dBtn').click(function(){
		seachSectin('');
	});
	$('.twoRowLeft').on('click','li>span',function(){
		$(this).parent().siblings().removeClass('active');
		$(this).parent().addClass('active');
		var sectionId = $(this).parent().attr('value');
		seachSectin(sectionId);
	});

//下载模版
$('#downloadSec').click(function(){
	
	location.href= "/manage/distric/section/downloadSection.html";
});
function fix_select(selector) { 
	//var i=$(selector).parent().find('div,ul').remove().css('zIndex'); 
	// $(selector).unwrap().removeClass('jqTransformHidden').jqTransSelect(); 
	//$(selector).parent().css('zIndex', i); 
} 

function seachSectin(sectionId){
	var phaseId = $('.phBy').val();
	var subjectId = $('.subby').val();
	var bookId = $('.bookby').val();
	location.href="/manage/distric/section/sectionList.html?bookId="
	+bookId+"&subjectId="+subjectId+"&phaseId="+phaseId+"&sectionId="+sectionId; 
}
function publicAjax(url,data,callBack){
	$.ajax(
		{
			type: "post",
			url: url,
			data:data,
			dataType:"json",
			success: function (msg) {
				callBack(msg);
			},
    		error:function(msg){
    			layer.alert('操作失败');
    		}
		});
}