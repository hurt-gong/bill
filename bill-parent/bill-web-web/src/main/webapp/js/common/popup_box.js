/**公用提示框*/
define(function (require, exports, module) { 
	//var dialog = require('dialog');
	/**输入框,执行callback回调*/
   exports.confirm = function(title, content, val, callback){
var box=$('<div class="alertMange"><h2><a href="javascript:;" class="closeAlert">×</a>'+title+'</h2><p><label for="">'+content+':</label><input type="text" name="" value='+val+'></p><button class="sure">确认</button><button class="cel">取消</button></div>');

$('.adminBody').append(box);

$('.alertMange').show();

//确认弹出框绑定事件
$('.alertMange .sure').click(function(){
    callback($('.alertMange input').val());
    $(this).parent().hide();
});
$('.alertMange .closeAlert').on('click',function(){
    $(this).parent().parent().hide();
});
$('.alertMange .cel').on('click',function(){
    $(this).parent().hide();
});

    };
	/**确认框,*/
	exports.prompt = function(title, content, callback){
		var box=$('<div class="clearBox"><h2><a href="javascript:;" class="closeAlert">×</a>'+title+'</h2><p>'+content+'</p><button class="sure">确认</button><button class="cel">取消</button></div>');
$('.adminBody').append(box);
		$('.clearBox').show();
		//确认弹出框绑定事件
$('.alertMange .sure').click(function(){
    callback($('.alertMange input').val());
    $(this).parent().hide();
});
$('.alertMange .closeAlert').on('click',function(){
    $(this).parent().parent().hide();
});
$('.alertMange .cel').on('click',function(){
    $(this).parent().hide();
});
	}; 
	/**提示框*/
	exports.msg = function( content){
		dialog.msg(content);
	}; 
});