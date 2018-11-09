require.config({        
	paths: {          
		"headerSlideDown": "../../common/headerslideDown"
	}          
});

require(['headerSlideDown'], function(headerSlideDown) {
	$('.planName_top li').on('click',function(){
		$('#taskForm input[name="taskType"]').val($(this).attr('value'));
		$('#taskForm').submit();
	});
});
