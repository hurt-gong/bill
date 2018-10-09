/**∑÷“≥js*/
define(["jquery"], function($){ 
	function page(){
		$(".pageNum a").on('click', function(){
			var formId = $('#vkoPagerFormId').val();
			var pagerForm = $('#' + formId);
			pagerForm.find(':input[name=_index]').val($(this).attr('pn'));
			pagerForm.submit(); 
		})
	}
	return page();
});