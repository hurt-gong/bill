require.config({　　　　
	paths: {　　　　　　
		"dialog": "../lib/layer/layer",　　　　
		"jqtransform": "../lib/jqTransform/jqtransform",
		"headerSlideDown": "../common/headerslideDown",
        "webuploader": "../lib/webuploader/webuploader",　　　
	}　　
});


require(['jqtransform', './common/adminAlertBox','headerSlideDown'], function(jqtransform, adminAlertBox,aaaa) {
	 $(".adminBody form,.screenBar form").jqTransform();
	 // a.alert1()
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
            pick: '#filePicker2',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

        /*var uploader = webuploader.create({

            // 选完文件后，是否自动上传。
            auto: true,

            // swf文件路径
            swf: 'http://edu.bjhd.gov.cn/js/lib/webuploader/Uploader.swf',

            // 文件接收服务端。
            server: 'http://webuploader.duapp.com/server/fileupload.php',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker3',

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
            pick: '#filePicker4',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });*/

    })
	 $('.checkbox').on('click',function(){
	    if($(this).siblings("input[type='radio']").is(':checked')){
	      $(this).removeClass('cur');
	      $(this).siblings("input[type='radio']").removeProp('checked')
	    }
	    else{
	      $(this).addClass('cur');
	      $(this).siblings("input[type='radio']").prop('checked','checked')
	    }
	  });
	 $(".add").on('click',function(){
	 	$(".manage_box").show();
	 	$(this).addClass('disable').parent().parent().siblings().find('a').removeClass('.disable');
	 })
	 $(".move").on('click',function(){
	 	$(".manage_box").show();
	 })
	 $('.close_box').on('click',function(){
	 	$(".manage_box").hide();
	 })
});