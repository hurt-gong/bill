require.config({　　　　
	paths: {　　　
		"headerSlideDown": "../../common/headerslideDown"
	}　　
});

require(['headerSlideDown'], function(headerSlideDown) {
	$('.adminTopBar a').on('click',function(){
		$('#taskForm input[name="isFinished"]').val($(this).attr('value'));
		$('#taskForm').submit();
	});
	$('.planName_top li').on('click',function(){
		$('#taskForm input[name="taskType"]').val($(this).attr('value'));
		$('#taskForm').submit();
	});
});
