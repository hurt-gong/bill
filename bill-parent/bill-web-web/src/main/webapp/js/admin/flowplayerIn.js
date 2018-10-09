require.config({　　
	baseUrl: 'http://edu.bjhd.gov.cn/', 　　
	paths: {　　　　　　
		"dialog": "js/lib/layer/layer",　　　　
		"jqtransform": "js/lib/jqTransform/jqtransform",
		"headerSlideDown": "js/common/headerslideDown",

        　　　　
        
        "webuploader": "js/lib/webuploader/webuploader",　　　
	}　　
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
	});
	$('.course_tit a').on('click',function(){
		$(this).addClass('changeA_style').siblings().removeClass('changeA_style');
		$('.course_con>div').eq($(this).index()).show().siblings().hide();
	});

//回复内容
$('.publick_item>div').find('.asn_reply').on('click',function(){
	$(this).parent().parent().parent().addClass('asn_block');
	var $that = $(this);
	$('.asn_close').on('click',function(){
		$(this).parent().parent().parent().siblings('.sec_dl').slideUp(300);
		$that.parent().parent().parent().removeClass('asn_block');
	})
	$('.asn_expand').on('click',function(){
		$(this).parent().parent().parent().siblings('.sec_dl').stop().slideDown(300)
	})
})
//点亮星星
$(function(){
	var oLis = $('.starcon>li');
	oLis.on('mouseover',function(){
		for(var i=0;i<=$(this).index();i++){
			oLis.eq(i).addClass('starheigh');
		}
		for(var i=($(this).index()+1);i<=oLis.length;i++){
			oLis.eq(i).removeClass('starheigh');
		}
	})
	$('.starcon').on('mouseout',function(){
		for(var i=0;i<=oLis.length;i++){
			oLis.eq(i).removeClass('starheigh');
		}
	})

// oLis.on('click',function(){
// 		alert($(this).index())
// 	})
	
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
        /*var uploader = webuploader.create({

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
		$('#tit a').on('click',function(){
			$(this).addClass('tapchange').siblings().removeClass('tapchange');
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

});
//play
var $playFlag = false
$('#play_btn').on('click',function(){
	// alert('1');
	// var $nowPlayWid = $('.play_moveright').width();
	// console.log($nowPlayWid);
	if($playFlag){
		$('.play_moveright').stop().animate({
			"width":"280px"
		},900);
		$('.play_moveleft').stop().animate({
			"width":"910px"
		},900)
		$('.play_movecenter>img').attr('src','http://edu.bjhd.gov.cn/img/admin/play_04.jpg')
		$playFlag = false;
	}else{
		$('.play_moveright').stop().animate({
			"width":"0px"
		},900);
		$('.play_moveleft').stop().animate({
			"width":"1090px"
		},900)
		$('.play_movecenter>img').attr('src','http://edu.bjhd.gov.cn/img/admin/play_04a.jpg');
		$playFlag = true;
	}
});
flowplayer("player", {
					src : 'http://edu.bjhd.gov.cn/js/lib/flowplayer/flowplayer.commercial-3.2.18.swf',
					bgcolor : '#000000',
					width : '100%',
					height : '100%',
					wmode : 'wmode',//Other valid settings: 'opaque','transparent','direct' (new and as of yet unstable)
					allowfullscreen : 'true',
					allowscriptaccess : 'always',//'samedomain','never'
					quality : 'high',
					SeamlessTabbing : 'false'
				},{
					clip :  {
			        	autoPlay : false,
			        	autoBuffering : true
				    	},
			    	playlist : [ // playlist is an array of Clips, hence [...]
			    		
					    {
					        url : 'http://play.abc.com/2.mp4',
					        // url : 'http://192.168.1.184:8080/mp4/123.mp4?start=238.88',
					        // url : "http://192.168.1.184:8080/flv/rBAXzVamT4SABat1BmKKHQmPGpc006.flv",
					        autoPlay : true,
					        title : '一休',
					        details : {
					            date : '03/24/2008',
					            creator : 'John Doe',
					            subject : ['culture', 'traveling', 'scandinavia']
					        }
					    },
					    {
					        url : 'http://play.abc.com/3.mp4',
					        autoPlay: true,
					        title : '一休',
					        details : {
					            date : '03/24/2008',
					            creator : 'John Doe',
					            subject : ['culture', 'traveling', 'scandinavia']
					        }
					    },
					    {
					        // "standard" flowplayer properties
					        url : 'http://static.bjjh.org.cn/group1/M00/00/08/rBAXzVamT4SABat1BmKKHQmPGpc006.flv',
					        autoPlay : true,
					        // custom property
					        title : '一休',
					        // custom properties can also be objects such as here
					        details : {
					            date : '03/24/2008',
					            creator : 'John Doe',
					            subject : ['culture', 'traveling', 'scandinavia']
					        }
					    }
			      	],
			      	plugins: { // load one or more plugins
			          controls: { // load the controls plugin
			 
			              // always: where to find the Flash object
			              url : 'http://edu.bjhd.gov.cn/js/lib/flowplayer/flowplayer.controls-3.2.16.swf',
			 
			              // now the custom options of the Flash object
			              playlist : false,
			              backgroundColor : '#000000',
			              tooltips : { // this plugin object exposes a 'tooltips' object
			                  buttons : true,
			                  fullscreen : '全屏',
			                  fullscreenExit : '退出',
			                  play : '播放',
			                  pause : '暂停',
			                  mute : '静音',
			                  unmute : '声音',
			                  stop : '停止',
			                  previous : '上一个',
			                  next : '下一个',
			                  scrubber : 'scrubber',
			                  volume : 'volume'
			              }
			        	}
      				},
      				onFinish: function() {
			          	//alert("Click Player to start video again");
			      	}
			});