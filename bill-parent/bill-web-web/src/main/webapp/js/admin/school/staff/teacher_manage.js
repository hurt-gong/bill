/**校-课程管理-课程管理*/
require.config({        
	paths: {
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown",　
	}          
});


require(['jqtransform','headerSlideDown'], function(jqtransform,aaaa) {
	/**效果*/
	$(".screenBar form").jqTransform();
	/**搜索*/
	$('.seach .sub').on('click',function(){
		$('#teacherForm input[name="positionId"]').val($('.screenBar .mySelect option:selected').val());
		$('#teacherForm input[name="name"]').val($('.seach input[name="teacherName"]').val().trim());
		$('#teacherForm').submit();
	 });
	$('.seach a').on('click',function(){
		$('#teacherForm input[name="positionId"]').val($('.screenBar .mySelect option:selected').val());
		$('#teacherForm input[name="name"]').val($('.seach input[name="teacherName"]').val().trim());
		$('#teacherForm').submit();
	 });
})
	/**年级下拉*/
	function selOption(val){
		$('#teacherForm input[name="positionId"]').val($('.screenBar .mySelect option:selected').val());
		$('#teacherForm input[name="name"]').val($('.seach input[name="teacherName"]').val().trim());
		$('#teacherForm').submit();
	};