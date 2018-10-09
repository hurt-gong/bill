require.config({
    baseUrl: 'http://edu.bjhd.gov.cn/', 　
    paths: {　　　　　　
        "dialog": "js/lib/layer/layer",
        "webuploader": "js/lib/webuploader/webuploader",
        "headerSlideDown": "js/common/headerslideDown",
        　　　　
    }　　
});


require(['js/admin/common/adminAlertBox','headerSlideDown'], function(adminAlertBox,aaaa) {
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

        var uploader = webuploader.create({

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
        });

    })


});
$('.changeLeft').find('label').on('click',function(){
    if($(this).siblings("input[type='checkbox']").is(':checked')){
        $(this).removeClass('asncheck');
         $(this).siblings("input[type='checkbox']").removeProp('checked')
        $('.mainCon').find('label').removeClass('asncheck');
        $('.mainCon').find("input[type='checkbox']").removeProp('checked')
    }
    else{
      $(this).addClass('asncheck');
      $(this).siblings("input[type='checkbox']").prop('checked','checked');
      $('.mainCon').find('label').addClass('asncheck');
      $('.mainCon').find("input[type='checkbox']").prop('checked','checked');
    }
});
$('.mainCon').find('label').on('click',function(){
    $('.changeLeft').find('label').removeClass('asncheck').siblings("input[type='checkbox']").removeProp('checked');
    if($(this).siblings("input[type='checkbox']").is(':checked')){
        $(this).removeClass('asncheck');
         $(this).siblings("input[type='checkbox']").removeProp('checked')
     }
    else{
      $(this).addClass('asncheck');
      $(this).siblings("input[type='checkbox']").prop('checked','checked');
    }
});
