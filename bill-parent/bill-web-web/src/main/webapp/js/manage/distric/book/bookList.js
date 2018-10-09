//目录修改
$('._alertBox .sure').click(function(){
	var book = $('#book').val();
	var yuan = $('#yuan').val();
	var id = $('#id').val();
	var phId = $('#phId').val();//学段
	var subId = $('#subjectId').val();//学科
	var ageId = $('#updatePhId').val();
	if(book == null || book == ""){
		layer.tips('请填写教材！！！', '#book', {tips: [1, '#3595CC'],time: 4000});
		return ;
	}else if(ageId == 'undefined' || ageId == ''){
		layer.tips('请填写年级！！！！', '#updatePhId', {tips: [1, '#3595CC'],time: 4000});
		return ;
	}else{
		location.href="/manage/distric/book/insertBook.html?bookName="+book+"&yuan="+yuan+"&id="+id+"&ageId="+ageId+"&phId="+phId+"&subId="+subId;//当前页面打开URL页面，前面三个用法相同。
	}
});

$('.cel').click(function(){
	$(".alertMange").hide();
});

$('.closeAlert').click(function(){
	$(".alertMange").hide();
});


function edit(str, id){
	$(".alertMange").show();
	$("#book").val(str);
	$("#yuan").val(str);
	$("#id").val(id);
};

//提交筛选form表单
$('.dBtnsure').click(function(){
	var phaseId = $('#phaseId').val();

	location.href = "/manage/distric/book/list.html?phaseId="+phaseId ;
});



function edits(id){
	$('#'+id).addClass("active").siblings().removeClass("active");
	var phaseId = $('#phId').val();
	$('#subjectId').val(id);
	
	$.ajax({
		type:"post",
		url:"/manage/distric/book/subToList.html",
		data:{
			phaseId:phaseId,
			subjectId:id
		},
		success:function(msg){ 
			var msg = jQuery.parseJSON(msg);
			var str = "";
			if(msg.success == true){
				for( var  i = 0 ; i < msg.data.length ; i++ ){
					var book = msg.data[i];
					var id = book.id;
					str += "<li class='cf' value='"+id+"'>"
								+"<span class='spanText'>"+book.name+"</span>"
								+"<a href='javascript:void(0)' class='del' onclick=\"bookDel('"+book.id+"')\">删除</a>"
								+"<a href='#' class='edit' onclick=\"edit('"+book.name+"', '"+book.id+"')\">修改</a>"
							+"</li>"
				}
			}	
			$(".bul").html(str);
		}
	});
};

//删除提示框
$('#celDel').click(function(){
	$(".clearBox").hide();
});
$('.closeAlert').click(function(){
	$(".clearBox").hide();
});


function bookDel(oneId){
	$(".clearBox").show();
	$('#delId').val(oneId);
};

$('#sureDel').click(function(){
	var oneId = $('#delId').val();
	$.ajax({
		type:"post",
		url:"/manage/distric/book/deleteBook.html",
		data:{"id":oneId},
		dataType:"json",
		success:function(msg){ 
			if(msg.success == true){
				$('.twoRowRight ul').find('li[value="'+oneId+'"]').remove();
				$('.clearBox').hide();
			}	

		}
	});
});