define(function (require, exports, module) { 

    //prompt输入提示框
    exports.prompt = function(title, content, val, callback){
var box=$('<div class="alertMange"><h2><a href="javascript:;" class="closeAlert">×</a>'+title+'</h2><p><label for="">'+content+':</label><input type="text" name="" id=""></p><button class="sure">确认</button><button class="cel">取消</button></div>');

$('.adminBody').append(box);

$('.alertMange').show();


var new_val='';
// $('.prompt .content input').select().keyup(function(){
//     new_val=$('.prompt_box .content input').val();
// });

//确认弹出框绑定事件
$('.alertMange .sure').click(function(){
    callback($('.alertMange input').val());
    $(this).parent().hide();
});
$('.alertMange .closeAlert').on('click',function(){
    $(this).parent().parent().hide();
});
$('alertMange .cel').on('click',function(){
    $(this).parent().hide();
});
// document.onkeydown=function(event){
//     var e = event || window.event || arguments.callee.caller.arguments[0];  
//     if(e && e.keyCode==13){ // enter 键
// $(".operation .button .ok").click();
//     }
// }; 


    };



});