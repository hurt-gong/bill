/********学案预览******************/  
$('#viewerPlaceHolder').FlexPaperViewer(
       { config : {
   SWFFile : 'flexpaper/3.swf',
   //SWFFile : 'http://js.bjjh.org.cn/sea-modules/flexpaper/pdf/11.swf',
   jsDirectory:'flexpaper/js/',
   Scale : 0.6,
   ZoomTransition : 'easeOut',
   ZoomTime : 0.5,
   ZoomInterval : 0.2,
   FitPageOnLoad : true,
   FitWidthOnLoad : false,
   FullScreenAsMaxWindow : false,
   ProgressiveLoading : false,
   MinZoomSize : 0.2,
   MaxZoomSize : 5,
   SearchMatchAll : false,
   InitViewMode : 'Portrait',
   RenderingOrder : 'flash',
   StartAtPage : '',
   ViewModeToolsVisible : true,
   ZoomToolsVisible : true,
   NavToolsVisible : true,
   CursorToolsVisible : true,
   SearchToolsVisible : true,
   WMode : 'window',
   localeChain: 'en_US'
       }}
  );