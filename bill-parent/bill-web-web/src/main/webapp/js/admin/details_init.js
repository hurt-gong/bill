require.config({　　
	baseUrl: 'http://edu.bjhd.gov.cn/', 　　
	paths: {　　　　　　
		"dialog": "js/lib/layer/layer",　　　　
		"jqtransform": "js/lib/jqTransform/jqtransform",
		"headerSlideDown": "js/common/headerslideDown",
        "webuploader": "js/lib/webuploader/webuploader",　　　
	},　
});

require(['http://edu.bjhd.gov.cn/js/lib/uploadify/jquery.uploadify.min.js'],function(){
   $(function() {
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
                    alert(vid);
                },
                 'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
                    $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
                },
                

            });
	});
   });
require(['jqtransform','js/admin/common/adminAlertBox','headerSlideDown'], function(jqtransform,adminAlertBox,aaaa) {
	$(".adminBody form,.screenBar form").jqTransform();
	 
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
		$("#new_type_id").val($(this).find("a:first").attr("data-id"));
        $("#new_type_id").attr("data-type_name",$(this).find("a:first").html());
	});
	$('.course_tit a').on('click',function(){
		$(this).addClass('changeA_style').siblings().removeClass('changeA_style');
		$('.course_con>div').eq($(this).index()).show().siblings().hide();
		if($(this).index()==1){
			$(".con2_left").after($(".course_right"));
		}else{
			$(".course_center").after($(".course_right"));
		}
		
	});
    //查看解析
    $('#tidetail>.floorone').find('.flooright').find('.check_jiexi').unbind('click').on('click',function(){
        $(this).parent().parent().find('.floor_jiexi').toggle();
    });
    //单选
    // $('.solution').find('input[type="radio"]').siblings('label').on('click',function(){
    //     $(this).addClass('asncheck').parent().siblings().find('input[type="radio"]').siblings('label').removeClass('asncheck');

    // });
     $('.solution').find('input[type="radio"]').siblings('label').on('click',function(){
        if($(this).siblings("input[type='radio']").is(':checked')){
            // $(this).removeClass('asncheck');
            // $(this).siblings("input[type='radio']").removeProp('checked');
        }else{
            $(this).addClass('asncheck').parent().siblings().find('input[type="radio"]').siblings('label').removeClass('asncheck');
            $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings().find('input[type="radio"]').removeProp('checked');
        }
     });
    //多选
    $('.solution').find('input[type="checkbox"]').siblings('label').on('click',function(){
        if($(this).siblings("input[type='checkbox']").is(':checked')){
          $(this).removeClass('asncheck');
          $(this).siblings("input[type='checkbox']").removeProp('checked')
        }
        else{
          $(this).addClass('asncheck');
          $(this).siblings("input[type='checkbox']").prop('checked','checked')
        }
    });
    //阅读题
    $('.read_dan').find('label').on('click',function(){
        if($(this).siblings("input[type='radio']").is(':checked')){
          // $(this).removeClass('asncheck');
          // $(this).siblings("input[type='radio']").removeProp('checked')
        }
        else{
          $(this).addClass('asncheck').parent().siblings('span').find('label').removeClass('asncheck');
          $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings('span').find("input[type='radio']").removeProp('checked');
        }
    });
    $('.read_duo').find('label').on('click',function(){
       if($(this).siblings("input[type='checkbox']").is(':checked')){

          $(this).removeClass('asncheck');
          $(this).siblings("input[type='checkbox']").removeProp('checked')
        }
        else{
          $(this).addClass('asncheck');
          $(this).siblings("input[type='checkbox']").prop('checked','checked');
        }
    });
    //阅读题题干选项
    $('.read .tigan').find('.tigan_class').on('click',function(){

        if($(this).siblings("input[type='radio']").is(':checked')){
          // $(this).removeClass('labelChecked');
          // $(this).siblings("input[type='radio']").removeProp('checked')
         // $(this).parent().parent().parent().siblings('dd').css('display','block').siblings('dd').css('display','none');
          if($(this).siblings('b').hasClass('se1')){
            // alert(1)
            $(this).parent().parent().parent().siblings('.tigan_dan').css('display','none');
          }else if($(this).siblings('b').hasClass('se2')){
            // alert(2)
            $(this).parent().parent().parent().siblings('.tigan_duo').css('display','none');
            }else if($(this).siblings('b').hasClass('se3')){
                $(this).parent().parent().parent().siblings('.tigan_txt').css('display','none');
            }
        }
        else{
          $(this).addClass('labelChecked').parent().siblings('span').find('label').removeClass('labelChecked');
          $(this).siblings("input[type='radio']").prop('checked','checked').parent().siblings('span').find("input[type='radio']").removeProp('checked');
          // console.log($(this).siblings())
          if($(this).siblings('b').hasClass('se1')){
            // alert(1)
            $(this).parent().parent().parent().siblings('.tigan_dan').css('display','block').siblings('dd').css('display','none');
          }else if($(this).siblings('b').hasClass('se2')){
            // alert(2)
            $(this).parent().parent().parent().siblings('.tigan_duo').css('display','block').siblings('dd').css('display','none');
            }else if($(this).siblings('b').hasClass('se3')){
                $(this).parent().parent().parent().siblings('.tigan_txt').css('display','block').siblings('dd').css('display','none');
            }
        }
        
    });
});
    
    //发布习题
$('.course_right').find('p>button').eq(1).on('click',function(){
    $('.publishjob_box').css('display','block').find('.publishjob_exit').unbind('click').on('click',function(){
        $('.publishjob_box').css('display','none')
    });
    $('.selectgrade').find('input[type="checkbox"]').siblings('label').unbind('click').on('click',function(){
        if($(this).siblings("input[type='checkbox']").is(':checked')){
          $(this).removeClass('greade_checked');
          $(this).siblings("input[type='checkbox']").removeProp('checked')
        }
        else{
          $(this).addClass('greade_checked');
          $(this).siblings("input[type='checkbox']").prop('checked','checked')
        }
    });

})
    

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
	});
    $('#tit a').on('click',function(){
        $(this).addClass('tapchange').siblings().removeClass('tapchange');
        $(this).parent().siblings('ul').eq($(this).index()).css('display','block').siblings('ul').css('display','none');
    });
    $('.left_nav ul li').on('click',function(){
        $(this).children('a').eq(0).addClass('clicka').parent().siblings('li').children('a').removeClass('clicka');
        $(this).find('dl').css('display','block').parent().siblings('li').find('dl').css('display','none');
    });
    $(document).on('mouseup',function(){
        $('.left_nav ul li').find('dl').css('display','none');
        $('.left_nav ul li').children('a').removeClass('clicka');
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



