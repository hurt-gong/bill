define(function() {
	// $(".adminBody form").jqTransform();
	// a.alert1()
	$('.fristChecked a').addClass('jqTransformChecked')
	$('#btn').click(function() {
		$('.alertBox').show();
	});
	$("#close").click(function() {
		$('.alertBox').hide();
	});
	$('#nav li').mouseover(function(){
		$(this).removeClass('bgLi').addClass('li').siblings().removeClass('li').addClass('bgLi');
	});
})