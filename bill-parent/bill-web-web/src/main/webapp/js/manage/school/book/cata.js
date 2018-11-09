$('.alertMange .sure').click(function(){
	var sectionName = $('#sectionName').val();
	var fatherId = $("#fatherId").val();
	var yuan = $('#yuan').val();
	var id = $('#id').val();
	var bookId = $('#bookId').val();
	
	if(sectionName == null || sectionName == ""){
		//layer.tips('我是另外一个tips，只不过我长得跟之前那位稍有些不一样。', '吸附元素选择器', {tips: [1, '#3595CC'],time: 4000});
		layer.tips('请填写目录！！！', '#sectionName', {tips: [1, '#3595CC'],time: 4000});
		//tips('请输入11位数字','.manage_box input[name="mobile"]');
		return ;
	}else{
		location.href="/manage/school/section/insertSection.html?sectionName="+sectionName+"&yuan="+yuan+"&id="+id+"&fatherId="+fatherId+"&bookId="+bookId//当前页面打开URL页面，前面三个用法相同。
	}
});

$('.cel').click(function(){
	$(".alertMange").hide();
});

function oneDel(oneId){
	$(".clearBox").show();
	$('#delId').val(oneId);
};

$('#sureDel').click(function(){
	var oneId = $('#delId').val();
	location.href="/manage/school/section/deleteSection.html?id="+oneId;
});

$('#celDel').click(function(){
	$(".clearBox").hide();
});

function twoDel(twoId){
	$(".clearBox").show();
	$('#delId').val(twoId);
};

//$('.edit').click(function(){
//	var spanText = $('.spanText').text();
//	alert("================"+spanText);
//});

function edit(str, id){
	$(".alertMange").show();
	$("#sectionName").val(str);
	$("#yuan").val(str);
	$("#id").val(id);
	$("#fatherId").val(id);
};

//添加二级目录
$('.dBtn').click(function(){
	$(".alertMange").show();
	$("#sectionName").val('');
});

function edits(id){
	//$('.oneName').addClass("cf");
	$('#'+id).addClass("active").siblings().removeClass("active");
	$("#fatherId").val(id);
	var str = "" ;
	//location.href="/manage/distric/section/sectionList.html?fatherId="+id; 
	$.ajax({
		data:{
			fatherId: id
		},
		type:"POST",   
		url:"/manage/school/section/twoList.html",
		success:function(msg){ 
		//data = "{"data":[{"bookId":1,"id":"434236762617806848","name":"sdfsdfdsf","pid":"434236707831808000"}],"success":true}
		var msg = jQuery.parseJSON(msg);
			if(msg.success == true){
				for( var  i = 0 ; i < msg.data.length ; i++ ){
					var sec = msg.data[i];
					var id = sec.id;
					str += "<li class='cf'><span>"+sec.name+"</span> "
							+"<a href='javascript:void(0)' class='del'  onclick=\"twoDel(\' "+id+" \')\">删除</a>"
							+"<a href='javascript:void(0)' class='edit'>修改</a>"
						+"</li>"
				}
			}	
			$(".bul").html(str);
		}
		
	});
};


$("#txtfile").change(function(){
	if("" == $('#txtfile').val()){
		return ;
	}
	
	var option = {
	   		type:'post',
	   		url:'/manage/school/section/uploadSection.html',
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

//检索条件
//$(function(){ 
//	select1();
	//$('#phaseId').bind("change", select2);
	//$('#S2').bind("change", select3);
//});

function phBySub(){
	var phBy = $('.phBy').val();
	$(".subby").html("");
	if(phBy != null){
		$.ajax(
		{
			type: "post",
			url: "/manage/school/section/subjectJson.html",
			data: {
				phaseId:phBy
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
			url: "/manage/school/section/bookJson.html",
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

$('._dBtn').click(function(){
	var phBy = $('.phBy').val();
	var subby = $('.subby').val();
	var bookby = $('.bookby').val();
	
	location.href="/manage/school/section/sectionList.html?bookId="+bookby+"&subjectId="+subby+"&phaseId="+phBy; 
});

$(function(){  
	$(".highRow ul").each(function(){
//取出ul下的第一个li
var li= $(this).children().first();
li.addClass("active");
    });
    //alert("第二种方法。");  
});

//下载模版
$('#downloadSec').click(function(){
	
	location.href= "/manage/school/section/downloadSection.html";
});