/**区-区教学管理-课程管理
*/
require.config({        
	paths: {
		"jqtransform": "../../../lib/jqTransform/jqtransform",
		"headerSlideDown": "../../../common/headerslideDown",
		"highstock":"../../../lib/highstock/2.0.1/highstock",
		"My97DatePicker": "../../../lib/My97DatePicker/WdatePicker"
	}          
});


require(['jqtransform','headerSlideDown','highstock','My97DatePicker'], function(jqtransform,headerSlideDown,highstock,My97DatePicker) {
	 $(".adminBody form").jqTransform();
	var title = '排名';
	var name='数量';
	var param = {};
	var url ='/manage/distric/statistics/getLoginStaChart.html';
	param.startDate =$("#time").val();
	param.endDate = $("#time1").val();
	param.sel1 =$('select[name="sel1"]').val();
	param.sel2 =$('select[name="sel2"]').val();
	param.sort =$('select[name="sort"]').val();
		//$(".search input[name='phaseId']").val();

	ajaxGetChart(param,url);
	function ajaxGetChart(param,url){
		$.ajax({
			url:url,
			type:"get",
			data:param,
			dataType:"json",
			success:function(data){
				if(data.success){
						var categories = data.data.categories;
						var data = 	data.data.data;
						var scrBar = false;
						if(categories.length>10){
							scrBar = true;
						}
						var maxLen = 0;
						if((categories.length-1)>0){
							maxLen=categories.length-1;
						}
						if(maxLen > 10){
							maxLen=10;
						}
						getChar(title,categories,name,data,maxLen,scrBar);
				}else{
					//$('.prompt_box .prompt_text').text(data.message);
				}
			},
			error:function(){
				//$('.prompt_box .prompt_text').text('图标加载失败');
			}
		});
	}

	function getChar(title,categories,name,data,maxLen,scrBar){
		$('#chart').highcharts({ 
			chart: { type: 'area' }, 
			title: { text: title }, 
			scrollbar: {
				enabled: scrBar  //是否出现滚动条
			}, 
			xAxis: { 
				categories: categories, 
				tickmarkPlacement: 'on', 
				gridLineWidth: 1,
				gridLineColor: '#E9F1F3',
				min: 0,
				max:maxLen,
				title: { text: '日期', align: 'high', offset: 8}
			}, 
			yAxis: { 
				title: { text: '数量', align: 'high', rotation: 0, offset: 0, y: -20}, 
				gridLineColor: '#E9F1F3',
				labels: { formatter: function() { return this.value; } } 
			},
			colors: ['#CAEEFF'], 
			tooltip: { 
				backgroundColor: '#0082c8', 
				shared: true, 
				shadow: false,
				borderRadius: 5,
				formatter: function () {
					return  this.y + ' 次';
				},
				style: {
					fontSize: "14px",
					color: "#FFFFFF"
				} 
			}, 
			plotOptions: { 
				area: { 
					stacking: 'normal', 
					lineColor: '#0082c8', 
					lineWidth: 3, 
					marker: { lineWidth: 3, lineColor: '#0082c8' } 
				} 
			},
			series: [
				{ 
					name: name, 
					data:data 
				}
			]
		});
	}

	$(".date>.yesterday").on("click",function(){
		getNewDate(1);	
	 });
	$(".date>.seven").on("click",function(){
		getNewDate(7);	
	 });
	$(".date>.thirty").on("click",function(){
		getNewDate(30);
	 });
	 $("#time").on("click",function(){
		 WdatePicker({
			dchanged:function(dp){
				$("#time").val($dp.cal.getNewDateStr());
				$('#quaForm').submit();
			},
			readOnly:true,
		 	el:'time',
		 })
	 });
	$("#time1").on("click",function(){
		 WdatePicker({
			dchanged:function(dp){
				$("#time1").val($dp.cal.getNewDateStr());
				$('#quaForm').submit();
			},
			readOnly:true,
		 	el:'time1',
		 })
	 });

	$('select[name="sort"]').change(function(){
		$('#quaForm').submit();
	});

	function getNewDate(day){
		var startDate =new Date();
		var endDate =new Date();
		endDate.setDate(endDate.getDate()-1);
		endDate =endDate.getFullYear()+"-"+(endDate.getMonth()+1)+"-"+endDate.getDate();
		startDate.setDate(startDate.getDate()-day);
		startDate=startDate.getFullYear()+"-"+(startDate.getMonth()+1)+"-"+startDate.getDate();
		$("#time").val(startDate);
		$("#time1").val(endDate);
		$('#quaForm').submit();
	}
});
 /**类别下拉*/
function sel1Fun(val){
	$('select[name="sel1"]').val();
	$('select[name="sort"]').val();
	$('#quaForm').submit();	
}
function sel2Fun(val){
	$('select[name="sort"]').val();
	$('#quaForm').submit();	
}

