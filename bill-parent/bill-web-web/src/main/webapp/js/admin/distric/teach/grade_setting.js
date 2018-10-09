/**区-区教学管理-年级设置
*/
require.config({　　　　
	paths: {　　　　　　
		"dialog": "../../../lib/layer/layer",　　　　
		"headerSlideDown": "../../../common/headerslideDown",　
	}　　
});


require(['headerSlideDown','dialog'], function(aaaa,dialog) {$('.twoRowLeft li span').on('click',function(){
		var phaseId = $(this).attr('value');
		$(this).parent().addClass('active');
		$(this).parent().siblings().removeClass('active');
		$.ajax({
			url:'/manage/distric/teach/getGradeByPhaseId.html',
			type:"get",
			data:{"phaseId":phaseId},
			dataType:"json",
			success:function(response){
				$('.twoRowRight .bul').html(response.data.data);
			},
			error:function(){
				dialog.msg('请求失败', {time: 3000, icon:2});
			}
		});
	});
})