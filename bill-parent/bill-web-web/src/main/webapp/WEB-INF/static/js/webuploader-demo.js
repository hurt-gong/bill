var $ = jQuery,
    $list = $('#thelist'),
    $btn = $('#ctlBtn'),
    state = 'pending',
    md5Finish = false,
    uploader;

//初始化
uploader = WebUploader.create({

    // swf文件路径
    swf: 'http://cdn.staticfile.org/webuploader/0.1.0/Uploader.swf',

    // 文件接收服务端。
     server: '/upload/file.html',
     //server: '/test.html',
    
     // server: 'http://upload.bjjh.org.cn/upload/file.html?shape=center&width=300&height=300',

    // 上传中的文件数
    progressNum:1,
    
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#picker',
    
    //formData: {md5:""},
    
    method: 'POST',
    
    multiple:true,//允许多个文件上传
    
    threads: 1,//上传并发数
    
    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
    resize: false
});

//当有文件被添加进队列的时候
uploader.on( 'fileQueued', function( file ) {
	uploader.md5File(file).progress(function(percentage) {
		//及时显示进度
	    console.log('Percentage:', percentage);
	    $('#md5file').html("秒传 MD5 值计算中：" + percentage);
	}).then(function(val) {
		//完成
		uploader.options.formData.md5 = val;
		md5Finish = true;
		$('#md5file').html("秒传 MD5 值：" + val);
		$("#md5").val(val);
	    console.log('md5 result:', val);
	});

    $list.append( '<div id="' + file.id + '" class="item">' +
'<h4 class="info">' + file.name + '</h4>' +
'<p class="state">等待上传...</p>' +
    '</div>' );
});

//扩展data对象，就会作为附带参数发过去
//uploader.on('uploadBeforeSend', function(obj, data ) {
	//居中截图 宽 300 高 300
  //  data.shape = "center";
  //  data.width = 300;
  //  data.height = 300;

    //要求秒传记录md5值
  //  data.md5 = md5Val;
//});
//文件上传过程中创建进度条实时显示。
uploader.on( 'uploadProgress', function( file, percentage ) {
    var $li = $( '#'+file.id ),
$percent = $li.find('.progress .progress-bar');

    // 避免重复创建
    if ( !$percent.length ) {
$percent = $('<div class="progress progress-striped active">' +
  '<div class="progress-bar" role="progressbar" style="width: 0%">' +
  '</div>' +
'</div>').appendTo( $li ).find('.progress-bar');
    }

    $li.find('p.state').text('上传中');

    $percent.css( 'width', percentage * 100 + '%' );
});

uploader.on( 'uploadSuccess', function( file, response) {
    $( '#'+file.id ).find('p.state').text('已上传');
    //这里后台返回结果
    console.log(response);
});

uploader.on( 'uploadError', function( file ) {
    $( '#'+file.id ).find('p.state').text('上传出错');
});

uploader.on( 'uploadComplete', function( file ) {
    $( '#'+file.id ).find('.progress').fadeOut();
});

uploader.on( 'all', function( type ) {
    if ( type === 'startUpload' ) {
state = 'uploading';
    } else if ( type === 'stopUpload' ) {
state = 'paused';
    } else if ( type === 'uploadFinished' ) {
state = 'done';
    }

    if ( state === 'uploading' ) {
$btn.text('暂停上传');
    } else {
$btn.text('开始上传');
    }
});

$btn.on( 'click', function() {
	if(md5Finish){
	    if ( state === 'uploading' ) {
	uploader.stop();
	    } else {
	uploader.upload();
	    }
	} else {
		alert('正在计算md5值，请稍等...');
	}
});