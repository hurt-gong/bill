/**区-区人员管理
*/
require.config({
    baseUrl: 'http://edu.bjhd.gov.cn/', 　
    paths: {　　　　　　
        "dialog": "../js/lib/layer/layer",
        "jqtransform": "js/lib/jqTransform/jqtransform",
        "webuploader": "js/lib/webuploader/webuploader",
        "headerSlideDown": "js/common/headerslideDown"
    }　　
});

/**
  * 事件操作
*/
require(['jqtransform','headerSlideDown','dialog'], function(jqtransform,aaaa,dialog) {
	$(".screenBar form").jqTransform();
	var url;	//添加更新用户url
	var callback;
	var optArea;
	/**人员单选按钮点击事件*/
	$('.checkbox').on('click',function(){
		$(this).addClass('cur');
		$(this).siblings("input[type='radio']").prop('checked','checked');
		$(this).parent().siblings().find('.checkbox').removeClass('cur');
		$(this).parent().siblings().find("input[type='radio']").attr("checked",false);
	});

	/**修改用户*/
	 $(".add").on('click',function(){
		var uId = $(this).parent().attr('value');
		var name = $(this).parents('tr').find('.uName').text();
		var gender = $(this).parents('tr').find('.uGender').attr('value');
		var idCardNo = $(this).parents('tr').find('.uIdCardNo').text();
		var mobile = $(this).parents('tr').find('.uMobile').text();
		$('.manage_box input[name="id"]').val(uId);
		$('.manage_box input[name="name"]').val(name);
		$('.manage_box input[name="idCardNo"]').val(idCardNo);
		var radioList=$('.manage_box input[name="gender"]');
		var radio;
		for(var i=0;i<radioList.size();i++){
			if(radioList.eq(i).attr('value')==gender){
				radio = radioList.eq(i);
			}
		}
		$(radio).prop('checked','checked');
		$(radio).siblings('.checkbox').addClass('cur');
		$(radio).parent().siblings().find('input[name="gender"]').attr("checked",false);
		$(radio).parent().siblings().find('.checkbox').removeClass('cur');
		$('.manage_box input[name="mobile"]').val(mobile);
		url='/manage/distric/staff/updateUser.html';
		callback = 'update';
		$('.manage_box h2 span').text('修改人员');
	 	$(".manage_box").show();
		optArea = $(this).parents('tr');
	})

	/**添加用户*/
	 $(".move").on('click',function(){
		url='/manage/distric/staff/addUser.html';
		callback = 'reload';
		$('.manage_box h2 span').text('添加人员')
	 	$(".manage_box").show();
	 })
	
	 $('.close_box').on('click',function(){
	 	$(".manage_box").hide();
	 })

	/**删除用户*/
	 $('.del').on('click',function(){
		$('.clearBox .sure').attr('value',$(this).parent('.operation').attr('value'));
		$('.clearBox p').text('确认删除'+$(this).parents('tr').find('.uName').text()+'？');
		optArea=$(this).parents('tr');
	 	$('.clearBox').show();
	 });

	 /**批量删除*/
	$('.mainHead a.allclear').on('click',function(){

		$(".clearBox").show();
		$(".clearBox").addClass('delAll');
	 });
	/**删除用户确定按钮*/
	$('.clearBox .sure').on('click',function(){
		if($(".clearBox").hasClass('delAll')){
			delAll();
			$(".clearBox").removeClass('delAll');
			$(".clearBox").hide();
			return;
		}
		var param={};
		param.id=$('.clearBox .sure').attr('value');
		$(".clearBox").hide();
		$.ajax({
			url:'/manage/distric/staff/delUserById.html',
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){
				if(data.success){
					$(optArea).remove();
					dialog.msg('删除成功', {time: 3000, icon:1});
					if($('.peoTable tr').size()==1){
						location.href="/manage/distric/staff/list.html";
					}
				}else{
					dialog.msg(data.message, {time: 3000, icon:7});
				}
			},
			error:function(){
				dialog.msg('请求失败', {time: 3000, icon:2});
			}
		});
	 });
	/**人员保存事件*/
	$('.manage_box .save').on('click',function(){
		var param = {};
		param.id = $('.manage_box input[name="id"]').val();
		param.name = $('.manage_box input[name="name"]').val().trim();
		param.idCardNo = $('.manage_box input[name="idCardNo"]').val().trim();
		param.gender = $('.manage_box input[name="gender"]:checked').val().trim();
		param.mobile = $('.manage_box input[name="mobile"]').val().trim();
		if(param.name.length<2 || param.name.length>15){
			tips('请输入2~15个字符','.manage_box input[name="name"]');
			return;
		}else if(param.idCardNo.length!=15 && param.idCardNo.length!=18){
			tips('请输入正确身份证号','.manage_box input[name="idCardNo"]');
			return;
		}if(param.mobile.length!=11){
			tips('请输入11位数字','.manage_box input[name="mobile"]');
			return;
		}
		$.ajax({
			url:url,
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){
				if(data.success){
					$(".manage_box").hide();
					dialog.msg('保存成功', {time: 3000, icon:1});
					if(callback == 'reload'){
						location.href="/manage/distric/staff/list.html";
					}else if(callback=='update'){
						updateUser();
					}
					$('.manage_box input[type="text"]').val('');
				}else{
					dialog.msg(data.message, {time: 3000, icon:7});
				}
			},
			error:function(){
				dialog.msg('请求失败', {time: 3000, icon:2});
				$(".manage_box").hide();
			}
		});
	})

	/**导出excel事件*/
	$('.seach .exportExcel').on('click',function(){
		location.href="/manage/distric/staff/exportStaff.html";
	 });

	/**提示层样式*/
	function tips(val,obj){
		dialog.tips(val, obj, {
			tips: [2, '#0082c8']
		});
	}
	/**更新用户*/
	function updateUser(){
		$(optArea).find('.uName').text($('.manage_box input[name="name"]').val());
		var gender = $('.manage_box input[name="gender"]:checked').val()
		$(optArea).find('.uGender').attr('value',gender);
		if(gender==1){
			$(optArea).find('.uGender').text('男');
		}else{
			$(optArea).find('.uGender').text('女');
		}
		$(optArea).find('.uIdCardNo').text($('.manage_box input[name="idCardNo"]').val());
		$(optArea).find('.uMobile').text($('.manage_box input[name="mobile"]').val());
	}

	function delAll(){
		var idList = new Array();  
		var uids = $('.peoTable input[name="uId"]:checked');
		if(uids.length==0){
			dialog.msg('请至少选择一条记录', {time: 3000, icon:7});
		  return;
		}
		for (var i = 0; i < uids.length; i++) {  
			idList.push($(uids[i]).val());   
		}
		$.ajax({
			url:'/manage/distric/staff/delUserList.html',
			type:"post",
			data:{"idList":idList},
			dataType:"json",
			success:function(data){
				if(data.success){
					dialog.msg('删除成功', {time: 3000, icon:1});
					setTimeout("location.href='/manage/distric/staff/list.html'",2000);
				}else{
					dialog.msg(data.message, {time: 3000, icon:7});
				}
			},
			error:function(){
				dialog.msg('请求失败', {time: 3000, icon:2});
			}
		});
	}
	/**导入excel*/
    require(['webuploader'], function(webuploader) {
        // 初始化Web Uploader
        var uploader = webuploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: 'http://edu.bjhd.gov.cn/js/lib/webuploader/Uploader.swf',
            // 文件接收服务端。
            server: 'http://edu.bjhd.gov.cn/manage/distric/staff/importStaff.html',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker',

            // 只允许选择图片文件。
            accept: {
                title: 'excel',
                extensions: 'xls,xlsx',
                mimeTypes: '.xls,.xlsx'
            }
        });
       uploader.on( 'uploadSuccess', function(file,response){
		   if(response.success){
				dialog.msg("导入成功", {time: 3000, icon:1});
		   }else{
			   dialog.msg(response.message, {time: 3000, icon:2});
		   }
		});

		uploader.on( 'uploadError', function(a){
			dialog.msg('请求失败', {time: 3000, icon:2});
		})
    })
//全选
	$(document).on('click','.changeLeft span',function(){
		if($(this).find('a').hasClass('jqTransformChecked')){
			$('.peoTable .jqTransformCheckbox ').removeClass('jqTransformChecked');
			$('.peoTable input[name="uId"]').attr("checked",false);
		}else{
			$('.peoTable .jqTransformCheckbox ').addClass('jqTransformChecked');
			$('.peoTable input[name="uId"]').prop('checked','checked');
		}
	})
//弹窗效果
	$(".adminBody form").jqTransform();
	$('#btn').click(function() {
		$('.alertBox').show();
	});
	$("#close").click(function() {
		$('.alertBox').hide();
	});
	$('#nav li').mouseover(function(){
		$(this).removeClass('bgLi').addClass('li').siblings().removeClass('li').addClass('bgLi');
	});
	$('.manage_box .cancel').on('click',function(){
	 	$(this).parent().parent().parent().hide();
	 });
	 $('.closeAlert').on('click',function(){
	 	$(this).parent().parent().hide();
	 });
	 $('.cel').on('click',function(){
	 	$(this).parent().hide();
	 });
})
