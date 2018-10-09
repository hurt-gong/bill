require.config({　　　　
	paths: {　　　
		"headerSlideDown": "../common/headerslideDown"
	}　　
});

require(['headerSlideDown'], function(headerSlideDown) {

//点击变换
$("body").on('click','.subj',function(){
	
  	$("#subjectId").val($(this).attr('value'));
 	$("#districtCourseForm").submit();
});
});
