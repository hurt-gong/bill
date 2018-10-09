require.config({　　
    baseUrl: 'http://edu.bjhd.gov.cn/', 　　
    paths: {　　　　　　
        "webuploader": "js/lib/webuploader/webuploader",
    }　　
});

    require(['webuploader'], function(webuploader) {
        // 初始化Web Uploader
        function initUploader(){
            var uploader = webuploader.create({
                // 选完文件后，是否自动上传。
                auto: true,
                // swf文件路径
                swf: 'http://edu.bjhd.gov.cn/js/lib/webuploader/Uploader.swf',
                formData : {
                    fcharset : 'ISO-8859-1',
                    writetoken : 'E1FLnPnSIPY3IyE-UDQHOGWBXH0SKUJ9',
                    cataid:'1',
                    JSONRPC     : '{"title": "这里是标题", "tag": "标签", "desc": "视频文档描述"}'
                },

                // 文件接收服务端。
                server: 'http://v.polyv.net/uc/services/rest?method=uploadfile',
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: '#picker',
                // 只允许选择图片文件。
                accept: {
                    title: 'video',
                    extensions: '.avi; *.wmv; *.mp4;*.mp3; *.mov; *.flv; *.mkv; *.rmvb',
                    mimeTypes: 'video/*'
                }
            });


           uploader.on( 'uploadSuccess', function( file , response) { 
                $('.imgbox').empty().html('<img width="290" height="164" src="' + response.url +'">');
            });
            uploader.on( 'uploadError', function( file ) {
                //alert('上传失败');
                dialog.msg('上传失败',{time:1200,icon:2});
            });
            // 上传完毕，重新初始化，解决同一文件，不刷新页面无法多次选取问题
            uploader.on( 'uploadComplete', function( file ) { 
                initUploader();
            });
        }

        initUploader(); 

    });




