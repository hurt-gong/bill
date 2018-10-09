define('jqtransform',[],function (){
/*
 *
 * jqTransform
 * by mathieu vilaplana mvilaplana@dfc-e.com
 * Designer ghyslain armand garmand@dfc-e.com
 *
 *
 * Version 1.0 25.09.08
 * Version 1.1 06.08.09
 * Add event click on Checkbox and Radio
 * Auto calculate the size of a select element
 * Can now, disabled the elements
 * Correct bug in ff if click on select (overflow=hidden)
 * No need any more preloading !!
 * 
 ******************************************** */
 
(function($){
	var defaultOptions = {preloadImg:true};
	var jqTransformImgPreloaded = false;

	var jqTransformPreloadHoverFocusImg = function(strImgUrl) {
		//guillemets to remove for ie
		strImgUrl = strImgUrl.replace(/^url\((.*)\)/,'$1').replace(/^\"(.*)\"$/,'$1');
		var imgHover = new Image();
		imgHover.src = strImgUrl.replace(/\.([a-zA-Z]*)$/,'-hover.$1');
		var imgFocus = new Image();
		imgFocus.src = strImgUrl.replace(/\.([a-zA-Z]*)$/,'-focus.$1');				
	};

	
	/***************************
	  Labels
	***************************/
	var jqTransformGetLabel = function(objfield){
		var selfForm = $(objfield.get(0).form);
		var oLabel = objfield.next();
		if(!oLabel.is('label')) {
			oLabel = objfield.prev();
			if(oLabel.is('label')){
				var inputname = objfield.attr('id');
				if(inputname){
					oLabel = selfForm.find('label[for="'+inputname+'"]');
				} 
			}
		}
		if(oLabel.is('label')){return oLabel.css('cursor','pointer');}
		return false;
	};
	
	/* Hide all open selects */
	var jqTransformHideSelect = function(oTarget){
		var ulVisible = $('.jqTransformSelectWrapper ul:visible');
		ulVisible.each(function(){
			var oSelect = $(this).parents(".jqTransformSelectWrapper:first").find("select").get(0);
			//do not hide if click on the label object associated to the select
			if( !(oTarget && oSelect.oLabel && oSelect.oLabel.get(0) == oTarget.get(0)) ){$(this).hide();}
		});
	};
	/* Check for an external click */
	var jqTransformCheckExternalClick = function(event) {
		if ($(event.target).parents('.jqTransformSelectWrapper').length === 0) { jqTransformHideSelect($(event.target)); }
	};

	/* Apply document listener */
	var jqTransformAddDocumentListener = function (){
		$(document).mousedown(jqTransformCheckExternalClick);
	};	
			
	/* Add a new handler for the reset action */
	var jqTransformReset = function(f){
		var sel;
		$('.jqTransformSelectWrapper select', f).each(function(){sel = (this.selectedIndex<0) ? 0 : this.selectedIndex; $('ul', $(this).parent()).each(function(){$('a:eq('+ sel +')', this).click();});});
		$('a.jqTransformCheckbox, a.jqTransformRadio', f).removeClass('jqTransformChecked');
		$('input:checkbox, input:radio', f).each(function(){if(this.checked){$('a', $(this).parent()).addClass('jqTransformChecked');}});
	};

	/***************************
	  Buttons
	 ***************************/
	$.fn.jqTransInputButton = function(){
		return this.each(function(){
			var newBtn = $('<button id="'+ this.id +'" name="'+ this.name +'" type="'+ this.type +'" class="'+ this.className +' jqTransformButton"><span><span>'+ $(this).attr('value') +'</span></span>')
				.hover(function(){newBtn.addClass('jqTransformButton_hover');},function(){newBtn.removeClass('jqTransformButton_hover')})
				.mousedown(function(){newBtn.addClass('jqTransformButton_click')})
				.mouseup(function(){newBtn.removeClass('jqTransformButton_click')})
			;
			$(this).replaceWith(newBtn);
		});
	};
	
	/***************************
	  Text Fields 
	 ***************************/
	$.fn.jqTransInputText = function(){
		return this.each(function(){
			var $input = $(this);
	
			if($input.hasClass('jqtranformdone') || !$input.is('input')) {return;}
			$input.addClass('jqtranformdone');
	
			var oLabel = jqTransformGetLabel($(this));
			oLabel && oLabel.bind('click',function(){$input.focus();});
	
			var inputSize=$input.width();
			if($input.attr('size')){
				inputSize = $input.attr('size')*10;
				$input.css('width',inputSize);
			}
			
			$input.addClass("jqTransformInput").wrap('<div class="jqTransformInputWrapper"><div class="jqTransformInputInner"><div></div></div></div>');
			var $wrapper = $input.parent().parent().parent();
			$wrapper.css("width", inputSize+10);
			$input
				.focus(function(){$wrapper.addClass("jqTransformInputWrapper_focus");})
				.blur(function(){$wrapper.removeClass("jqTransformInputWrapper_focus");})
				.hover(function(){$wrapper.addClass("jqTransformInputWrapper_hover");},function(){$wrapper.removeClass("jqTransformInputWrapper_hover");})
			;
	
			/* If this is safari we need to add an extra class */
			if(undefined==$.browser){
				$.browser=$.support;
			}
			$.browser.safari && $wrapper.addClass('jqTransformSafari');
			$.browser.safari && $input.css('width',$wrapper.width()+16);
			this.wrapper = $wrapper;
			
		});
	};
	
	/***************************
	  Check Boxes 
	 ***************************/	
	$.fn.jqTransCheckBox = function(){
		return this.each(function(){
			if($(this).hasClass('jqTransformHidden')) {return;}

			var $input = $(this);
			var inputSelf = this;

			//set the click on the label
			var oLabel=jqTransformGetLabel($input);
			oLabel && oLabel.click(function(){aLink.trigger('click');});
			
			var aLink = $('<a href="#" class="jqTransformCheckbox"></a>');
			//wrap and add the link
			$input.addClass('jqTransformHidden').wrap('<span class="jqTransformCheckboxWrapper"></span>').parent().prepend(aLink);
			//on change, change the class of the link
			$input.change(function(){
				this.checked && aLink.addClass('jqTransformChecked') || aLink.removeClass('jqTransformChecked');
				return true;
			});
			// Click Handler, trigger the click and change event on the input
			aLink.click(function(){
				//do nothing if the original input is disabled
				if($input.attr('disabled')){return false;}
				//trigger the envents on the input object
				$input.trigger('click').trigger("change");	
				return false;
			});

			// set the default state
			this.checked && aLink.addClass('jqTransformChecked');		
		});
	};
	/***************************
	  Radio Buttons 
	 ***************************/	
	$.fn.jqTransRadio = function(){
		return this.each(function(){
			if($(this).hasClass('jqTransformHidden')) {return;}

			var $input = $(this);
			var inputSelf = this;
				
			oLabel = jqTransformGetLabel($input);
			oLabel && oLabel.click(function(){aLink.trigger('click');});
	
			var aLink = $('<a href="#" class="jqTransformRadio" rel="'+ this.name +'"></a>');
			$input.addClass('jqTransformHidden').wrap('<span class="jqTransformRadioWrapper"></span>').parent().prepend(aLink);
			
			$input.change(function(){
				inputSelf.checked && aLink.addClass('jqTransformChecked') || aLink.removeClass('jqTransformChecked');
				return true;
			});
			// Click Handler
			aLink.click(function(){
				if($input.attr('disabled')){return false;}
				$input.trigger('click').trigger('change');
	
				// uncheck all others of same name input radio elements
				$('input[name="'+$input.attr('name')+'"]',inputSelf.form).not($input).each(function(){
					$(this).attr('type')=='radio' && $(this).trigger('change');
				});
	
				return false;					
			});
			// set the default state
			inputSelf.checked && aLink.addClass('jqTransformChecked');
		});
	};
	
	/***************************
	  TextArea 
	 ***************************/	
	$.fn.jqTransTextarea = function(){
		return this.each(function(){
			var textarea = $(this);
	
			if(textarea.hasClass('jqtransformdone')) {return;}
			textarea.addClass('jqtransformdone');
	
			oLabel = jqTransformGetLabel(textarea);
			oLabel && oLabel.click(function(){textarea.focus();});
			
			var strTable = '<table cellspacing="0" cellpadding="0" border="0" class="jqTransformTextarea">';
			strTable +='<tr><td id="jqTransformTextarea-tl"></td><td id="jqTransformTextarea-tm"></td><td id="jqTransformTextarea-tr"></td></tr>';
			strTable +='<tr><td id="jqTransformTextarea-ml">&nbsp;</td><td id="jqTransformTextarea-mm"><div></div></td><td id="jqTransformTextarea-mr">&nbsp;</td></tr>';	
			strTable +='<tr><td id="jqTransformTextarea-bl"></td><td id="jqTransformTextarea-bm"></td><td id="jqTransformTextarea-br"></td></tr>';
			strTable +='</table>';					
			var oTable = $(strTable)
					.insertAfter(textarea)
					.hover(function(){
						!oTable.hasClass('jqTransformTextarea-focus') && oTable.addClass('jqTransformTextarea-hover');
					},function(){
						oTable.removeClass('jqTransformTextarea-hover');					
					})
				;
				
			textarea
				.focus(function(){oTable.removeClass('jqTransformTextarea-hover').addClass('jqTransformTextarea-focus');})
				.blur(function(){oTable.removeClass('jqTransformTextarea-focus');})
				.appendTo($('#jqTransformTextarea-mm div',oTable))
			;
			this.oTable = oTable;
			if($.browser.safari){
				$('#jqTransformTextarea-mm',oTable)
					.addClass('jqTransformSafariTextarea')
					.find('div')
						.css('height',textarea.height())
						.css('width',textarea.width())
				;
			}
		});
	};
	
	/***************************
	  Select 
	 ***************************/	
	$.fn.jqTransSelect = function(){
		return this.each(function(index){
			var $select = $(this);

			if($select.hasClass('jqTransformHidden')) {return;}
			if($select.attr('multiple')) {return;}

			var oLabel  =  jqTransformGetLabel($select);
			/* First thing we do is Wrap it */
			var $wrapper = $select
				.addClass('jqTransformHidden')
				.wrap('<div class="jqTransformSelectWrapper"></div>')
				.parent()
				.css({zIndex: 10-index})
			;
			
			/* Now add the html for the select */
			$wrapper.prepend('<div><span></span><a href="#" class="jqTransformSelectOpen"></a></div><ul></ul>');
			var $ul = $('ul', $wrapper).css('width',$select.width()).hide();
			/* Now we add the options */
			$('option', this).each(function(i){
				var oLi = $('<li><a href="#" index="'+ i +'">'+ $(this).html() +'</a></li>');
				$ul.append(oLi);
			});
			
			/* Add click handler to the a */
			$ul.find('a').click(function(){
					$('a.selected', $wrapper).removeClass('selected');
					$(this).addClass('selected');	
					/* Fire the onchange event */
					if ($select[0].selectedIndex != $(this).attr('index') && $select[0].onchange) { $select[0].selectedIndex = $(this).attr('index'); $select[0].onchange(); }
					$select[0].selectedIndex = $(this).attr('index');
					$('span:eq(0)', $wrapper).html($(this).html());
					$ul.hide();
					return false;
			});
			/* Set the default */
			$('a:eq('+ this.selectedIndex +')', $ul).click();
			$('span:first', $wrapper).click(function(){$("a.jqTransformSelectOpen",$wrapper).trigger('click');});
			oLabel && oLabel.click(function(){$("a.jqTransformSelectOpen",$wrapper).trigger('click');});
			this.oLabel = oLabel;
			
			/* Apply the click handler to the Open */
			var oLinkOpen = $('a.jqTransformSelectOpen', $wrapper)
				.click(function(){
					//Check if box is already open to still allow toggle, but close all other selects
					if( $ul.css('display') == 'none' ) {jqTransformHideSelect();} 
					if($select.attr('disabled')){return false;}

					$ul.slideToggle('fast', function(){					
						var offSet = ($('a.selected', $ul).offset().top - $ul.offset().top);
						$ul.animate({scrollTop: offSet});
					});
					return false;
				})
			;

			// Set the new width
			var iSelectWidth = $select.outerWidth();
			var oSpan = $('span:first',$wrapper);
			var newWidth = (iSelectWidth > oSpan.innerWidth())?iSelectWidth+oLinkOpen.outerWidth():$wrapper.width();
			$wrapper.css('width',newWidth);
			$ul.css('width',newWidth-2);
			oSpan.css({width:iSelectWidth});
		
			// Calculate the height if necessary, less elements that the default height
			//show the ul to calculate the block, if ul is not displayed li height value is 0
			$ul.css({display:'block',visibility:'hidden'});
			var iSelectHeight = ($('li',$ul).length)*($('li:first',$ul).height());//+1 else bug ff
			(iSelectHeight < $ul.height()) && $ul.css({height:iSelectHeight,'overflow':'hidden'});//hidden else bug with ff
			$ul.css({display:'none',visibility:'visible'});
			
		});
	};
	$.fn.jqTransform = function(options){
		var opt = $.extend({},defaultOptions,options);
		
		/* each form */
		 return this.each(function(){
			var selfForm = $(this);
			if(selfForm.hasClass('jqtransformdone')) {return;}
			selfForm.addClass('jqtransformdone');
			
			$('input:submit, input:reset, input[type="button"]', this).jqTransInputButton();			
			$('input:text, input:password', this).jqTransInputText();			
			$('input:checkbox', this).jqTransCheckBox();
			$('input:radio', this).jqTransRadio();
			$('textarea', this).jqTransTextarea();
			
			if( $('select', this).jqTransSelect().length > 0 ){jqTransformAddDocumentListener();}
			selfForm.bind('reset',function(){var action = function(){jqTransformReset(this);}; window.setTimeout(action, 10);});
			
			//preloading dont needed anymore since normal, focus and hover image are the same one
			/*if(opt.preloadImg && !jqTransformImgPreloaded){
				jqTransformImgPreloaded = true;
				var oInputText = $('input:text:first', selfForm);
				if(oInputText.length > 0){
					//pour ie on eleve les ""
					var strWrapperImgUrl = oInputText.get(0).wrapper.css('background-image');
					jqTransformPreloadHoverFocusImg(strWrapperImgUrl);					
					var strInnerImgUrl = $('div.jqTransformInputInner',$(oInputText.get(0).wrapper)).css('background-image');
					jqTransformPreloadHoverFocusImg(strInnerImgUrl);
				}
				
				var oTextarea = $('textarea',selfForm);
				if(oTextarea.length > 0){
					var oTable = oTextarea.get(0).oTable;
					$('td',oTable).each(function(){
						var strImgBack = $(this).css('background-image');
						jqTransformPreloadHoverFocusImg(strImgBack);
					});
				}
			}*/
			
			
		}); /* End Form each */
				
	};/* End the Plugin */

})(jQuery);
})
				   ;
/*
 * My97 DatePicker 4.8 Beta4
 * License: http://www.my97.net/dp/license.asp
 */
var $dp,WdatePicker;(function(){var $={
$langList:[
 {name:"en",charset:"UTF-8"},
 {name:"zh-cn",charset:"gb2312"},
 {name:"zh-tw",charset:"GBK"}],
$skinList:[
 {name:"default",charset:"gb2312"},
 {name:"whyGreen",charset:"gb2312"},
 {name:"blue",charset:"gb2312"},
 {name:"green",charset:"gb2312"},
 {name:"simple",charset:"gb2312"},
 {name:"ext",charset:"gb2312"},
 {name:"blueFresh",charset:"gb2312"},
 {name:"twoer",charset:"gb2312"},
 {name:"YcloudRed",charset:"gb2312"}],
$wdate:true,
$crossFrame:true,
$preLoad:false,
$dpPath:"",
doubleCalendar:false,
enableKeyboard:true,
enableInputMask:true,
autoUpdateOnChanged:null,
weekMethod:"ISO8601",
position:{},
lang:"auto",
skin:"default",
dateFmt:"yyyy-MM-dd",
realDateFmt:"yyyy-MM-dd",
realTimeFmt:"HH:mm:ss",
realFullFmt:"%Date %Time",
minDate:"1900-01-01 00:00:00",
maxDate:"2099-12-31 23:59:59",
startDate:"",
alwaysUseStartDate:false,
yearOffset:1911,
firstDayOfWeek:0,
isShowWeek:false,
highLineWeekDay:true,
isShowClear:true,
isShowToday:true,
isShowOK:true,
isShowOthers:true,
readOnly:false,
errDealMode:0,
autoPickDate:null,
qsEnabled:true,
autoShowQS:false,
opposite:false,
hmsMenuCfg:{H:[1,6],m:[5,6],s:[15,4]},
opposite:false,

specialDates:null,specialDays:null,disabledDates:null,disabledDays:null,onpicking:null,onpicked:null,onclearing:null,oncleared:null,ychanging:null,ychanged:null,Mchanging:null,Mchanged:null,dchanging:null,dchanged:null,Hchanging:null,Hchanged:null,mchanging:null,mchanged:null,schanging:null,schanged:null,eCont:null,vel:null,elProp:"",errMsg:"",quickSel:[],has:{},getRealLang:function(){var _=$.$langList;for(var A=0;A<_.length;A++)if(_[A].name==this.lang)return _[A];return _[0]}};WdatePicker=U;var Y=window,T={innerHTML:""},N="document",H="documentElement",C="getElementsByTagName",V,A,S,G,c,X=navigator.appName;if(X=="Microsoft Internet Explorer")S=true;else if(X=="Opera")c=true;else G=true;A=$.$dpPath||J();if($.$wdate)K(A+"skin/WdatePicker.css");V=Y;if($.$crossFrame){try{while(V.parent!=V&&V.parent[N][C]("frameset").length==0)V=V.parent}catch(O){}}if(!V.$dp)V.$dp={ff:G,ie:S,opera:c,status:0,defMinDate:$.minDate,defMaxDate:$.maxDate};B();if($.$preLoad&&$dp.status==0)E(Y,"onload",function(){U(null,true)});if(!Y[N].docMD){E(Y[N],"onmousedown",D,true);Y[N].docMD=true}if(!V[N].docMD){E(V[N],"onmousedown",D,true);V[N].docMD=true}E(Y,"onunload",function(){if($dp.dd)P($dp.dd,"none")});function B(){try{V[N],V.$dp=V.$dp||{}}catch($){V=Y;$dp=$dp||{}}var A={win:Y,$:function($){return(typeof $=="string")?Y[N].getElementById($):$},$D:function($,_){return this.$DV(this.$($).value,_)},$DV:function(_,$){if(_!=""){this.dt=$dp.cal.splitDate(_,$dp.cal.dateFmt);if($)for(var B in $)if(this.dt[B]===undefined)this.errMsg="invalid property:"+B;else{this.dt[B]+=$[B];if(B=="M"){var C=$["M"]>0?1:0,A=new Date(this.dt["y"],this.dt["M"],0).getDate();this.dt["d"]=Math.min(A+C,this.dt["d"])}}if(this.dt.refresh())return this.dt}return""},show:function(){var A=V[N].getElementsByTagName("div"),$=100000;for(var B=0;B<A.length;B++){var _=parseInt(A[B].style.zIndex);if(_>$)$=_}this.dd.style.zIndex=$+2;P(this.dd,"block");P(this.dd.firstChild,"")},unbind:function($){$=this.$($);if($.initcfg){L($,"onclick",function(){U($.initcfg)});L($,"onfocus",function(){U($.initcfg)})}},hide:function(){P(this.dd,"none")},attachEvent:E};for(var _ in A)V.$dp[_]=A[_];$dp=V.$dp}function E(B,_,A,$){if(B.addEventListener){var C=_.replace(/on/,"");A._ieEmuEventHandler=function($){return A($)};B.addEventListener(C,A._ieEmuEventHandler,$)}else B.attachEvent(_,A)}function L(A,$,_){if(A.removeEventListener){var B=$.replace(/on/,"");_._ieEmuEventHandler=function($){return _($)};A.removeEventListener(B,_._ieEmuEventHandler,false)}else A.detachEvent($,_)}function a(_,$,A){if(typeof _!=typeof $)return false;if(typeof _=="object"){if(!A)for(var B in _){if(typeof $[B]=="undefined")return false;if(!a(_[B],$[B],true))return false}return true}else if(typeof _=="function"&&typeof $=="function")return _.toString()==$.toString();else return _==$}function J(){var _,A,$=Y[N][C]("script");for(var B=0;B<$.length;B++){_=$[B].getAttribute("src")||"";_=_.substr(0,_.toLowerCase().indexOf("wdatepicker.js"));A=_.lastIndexOf("/");if(A>0)_=_.substring(0,A+1);if(_)break}return _}function K(A,$,B){var D=Y[N][C]("HEAD").item(0),_=Y[N].createElement("link");if(D){_.href=A;_.rel="stylesheet";_.type="text/css";if($)_.title=$;if(B)_.charset=B;D.appendChild(_)}}function F($){$=$||V;var A=0,_=0;while($!=V){var D=$.parent[N][C]("iframe");for(var F=0;F<D.length;F++){try{if(D[F].contentWindow==$){var E=W(D[F]);A+=E.left;_+=E.top;break}}catch(B){}}$=$.parent}return{"leftM":A,"topM":_}}function W(G,F){if(G.getBoundingClientRect)return G.getBoundingClientRect();else{var A={ROOT_TAG:/^body|html$/i,OP_SCROLL:/^(?:inline|table-row)$/i},E=false,I=null,_=G.offsetTop,H=G.offsetLeft,D=G.offsetWidth,B=G.offsetHeight,C=G.offsetParent;if(C!=G)while(C){H+=C.offsetLeft;_+=C.offsetTop;if(R(C,"position").toLowerCase()=="fixed")E=true;else if(C.tagName.toLowerCase()=="body")I=C.ownerDocument.defaultView;C=C.offsetParent}C=G.parentNode;while(C.tagName&&!A.ROOT_TAG.test(C.tagName)){if(C.scrollTop||C.scrollLeft)if(!A.OP_SCROLL.test(P(C)))if(!c||C.style.overflow!=="visible"){H-=C.scrollLeft;_-=C.scrollTop}C=C.parentNode}if(!E){var $=b(I);H-=$.left;_-=$.top}D+=H;B+=_;return{"left":H,"top":_,"right":D,"bottom":B}}}function M($){$=$||V;var B=$[N],A=($.innerWidth)?$.innerWidth:(B[H]&&B[H].clientWidth)?B[H].clientWidth:B.body.offsetWidth,_=($.innerHeight)?$.innerHeight:(B[H]&&B[H].clientHeight)?B[H].clientHeight:B.body.offsetHeight;return{"width":A,"height":_}}function b($){$=$||V;var B=$[N],A=B[H],_=B.body;B=(A&&A.scrollTop!=null&&(A.scrollTop>_.scrollTop||A.scrollLeft>_.scrollLeft))?A:_;return{"top":B.scrollTop,"left":B.scrollLeft}}function D($){try{var _=$?($.srcElement||$.target):null;if($dp.cal&&!$dp.eCont&&$dp.dd&&_!=$dp.el&&$dp.dd.style.display=="block")$dp.cal.close()}catch($){}}function Z(){$dp.status=2}var Q,_;function U(K,C){if(!$dp)return;B();var L={};for(var H in K)L[H]=K[H];for(H in $)if(H.substring(0,1)!="$"&&L[H]===undefined)L[H]=$[H];if(C){if(!J()){_=_||setInterval(function(){if(V[N].readyState=="complete")clearInterval(_);U(null,true)},50);return}if($dp.status==0){$dp.status=1;L.el=T;I(L,true)}else return}else if(L.eCont){L.eCont=$dp.$(L.eCont);L.el=T;L.autoPickDate=true;L.qsEnabled=false;I(L)}else{if($.$preLoad&&$dp.status!=2)return;var F=D();if(Y.event===F||F){L.srcEl=F.srcElement||F.target;F.cancelBubble=true}L.el=L.el=$dp.$(L.el||L.srcEl);if(!L.el||L.el["My97Mark"]===true||L.el.disabled||($dp.dd&&P($dp.dd)!="none"&&$dp.dd.style.left!="-970px")){try{if(L.el["My97Mark"])L.el["My97Mark"]=false}catch(A){}return}if(F&&L.el.nodeType==1&&!a(L.el.initcfg,K)){$dp.unbind(L.el);E(L.el,F.type=="focus"?"onclick":"onfocus",function(){U(K)});L.el.initcfg=K}I(L)}function J(){if(S&&V!=Y&&V[N].readyState!="complete")return false;return true}function D(){if(G){func=D.caller;while(func!=null){var $=func.arguments[0];if($&&($+"").indexOf("Event")>=0)return $;func=func.caller}return null}return event}}function R(_,$){return _.currentStyle?_.currentStyle[$]:document.defaultView.getComputedStyle(_,false)[$]}function P(_,$){if(_)if($!=null)_.style.display=$;else return R(_,"display")}function I(G,_){var D=G.el?G.el.nodeName:"INPUT";if(_||G.eCont||new RegExp(/input|textarea|div|span|p|a/ig).test(D))G.elProp=D=="INPUT"?"value":"innerHTML";else return;if(G.lang=="auto")G.lang=S?navigator.browserLanguage.toLowerCase():navigator.language.toLowerCase();if(!G.eCont)for(var C in G)$dp[C]=G[C];if(!$dp.dd||G.eCont||($dp.dd&&(G.getRealLang().name!=$dp.dd.lang||G.skin!=$dp.dd.skin))){if(G.eCont)E(G.eCont,G);else{$dp.dd=V[N].createElement("DIV");$dp.dd.style.cssText="position:absolute";V[N].body.appendChild($dp.dd);E($dp.dd,G);if(_)$dp.dd.style.left=$dp.dd.style.top="-970px";else{$dp.show();B($dp)}}}else if($dp.cal){$dp.show();$dp.cal.init();if(!$dp.eCont)B($dp)}function E(K,J){var I=V[N].domain,F=false,G="<iframe hideFocus=true width=9 height=7 frameborder=0 border=0 scrolling=no src=\"about:blank\"></iframe>";K.innerHTML=G;var _=$.$langList,D=$.$skinList,H;try{H=K.lastChild.contentWindow[N]}catch(E){F=true;K.removeChild(K.lastChild);var L=V[N].createElement("iframe");L.hideFocus=true;L.frameBorder=0;L.scrolling="no";L.src="javascript:(function(){var d=document;d.open();d.domain='"+I+"';})()";K.appendChild(L);setTimeout(function(){H=K.lastChild.contentWindow[N];C()},97);return}C();function C(){var _=J.getRealLang();K.lang=_.name;K.skin=J.skin;var $=["<head><script>","","var doc=document, $d, $dp, $cfg=doc.cfg, $pdp = parent.$dp, $dt, $tdt, $sdt, $lastInput, $IE=$pdp.ie, $FF = $pdp.ff,$OPERA=$pdp.opera, $ny, $cMark = false;","if($cfg.eCont){$dp = {};for(var p in $pdp)$dp[p]=$pdp[p];}else{$dp=$pdp;};for(var p in $cfg){$dp[p]=$cfg[p];}","doc.oncontextmenu=function(){try{$c._fillQS(!$dp.has.d,1);showB($d.qsDivSel);}catch(e){};return false;};","</script><script src=",A,"lang/",_.name,".js charset=",_.charset,"></script>"];if(F)$[1]="document.domain=\""+I+"\";";for(var C=0;C<D.length;C++)if(D[C].name==J.skin)$.push("<link rel=\"stylesheet\" type=\"text/css\" href=\""+A+"skin/"+D[C].name+"/datepicker.css\" charset=\""+D[C].charset+"\"/>");$.push("<script src=\""+A+"calendar.js\"></script>");$.push("</head><body leftmargin=\"0\" topmargin=\"0\" tabindex=0></body></html>");$.push("<script>var t;t=t||setInterval(function(){if(doc.ready){new My97DP();$cfg.onload();$c.autoSize();$cfg.setPos($dp);clearInterval(t);}},20);</script>");J.setPos=B;J.onload=Z;H.write("<html>");H.cfg=J;H.write($.join(""));H.close()}}function B(J){var H=J.position.left,C=J.position.top,D=J.el;if(D==T)return;if(D!=J.srcEl&&(P(D)=="none"||D.type=="hidden"))D=J.srcEl;var I=W(D),$=F(Y),E=M(V),B=b(V),G=$dp.dd.offsetHeight,A=$dp.dd.offsetWidth;if(isNaN(C))C=0;if(($.topM+I.bottom+G>E.height)&&($.topM+I.top-G>0))C+=B.top+$.topM+I.top-G-2;else{C+=B.top+$.topM+I.bottom;var _=C-B.top+G-E.height;if(_>0)C-=_}if(isNaN(H))H=0;H+=B.left+Math.min($.leftM+I.left,E.width-A-5)-(S?2:0);J.dd.style.top=C+"px";J.dd.style.left=H+"px"}}})();
define("My97DatePicker", function(){});

define('common/adminAlertBox',[],function() {
	$(".adminBody form").jqTransform();
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
});
define('headerSlideDown',[],function(){
	//console.log(123123123)
	
	
/*$(function(){
    $("ul li a").click(function(){
		var Ah = $(this).parent("li").height()*($(this).parent("li").index());
		$("#scr").stop().animate({scrollTop:Ah},10);
    })
	
	$('div.userArea').on('mouseenter',function(){
		//alert(11111);
		var boxHeight=$('div.userArea').height();
		if(boxHeight==65){
			$('div.userArea').stop().animate({'height':144},500);
		}
		
	});
	$('div.userArea').on('mouseleave',function(){
		var boxHeight=$('div.userArea').height();
		if(boxHeight==144){
			//$('div.userArea').stop().animate({'height':144},500);
			$(this).stop().animate({'height':65},500);
		}
			
	});
})*/
$('div.userArea').hover(function(){
		$(this).stop().animate({'height':144},500);	
	},function(){
		$(this).stop().animate({'height':65},500);
	})
})
;
require.config({　　　　
	paths: {　　　　　　
		"dialog": "../lib/layer/layer",　　　　
		"jqtransform": "../lib/jqTransform/jqtransform",
		"My97DatePicker": "../lib/My97DatePicker/WdatePicker",　
		"headerSlideDown": "../common/headerslideDown",　　　
	}　　
});


require(['jqtransform','My97DatePicker','./common/adminAlertBox','headerSlideDown'], function(jqtransform,m97,adminAlertBox,aaaa) {
	
$(".adminBody form").jqTransform();
	 // a.alert1()
	 require(['http://edu.bjhd.gov.cn/js/lib/My97DatePicker/WdatePicker.js'],function(){
	 $("#time").on("click",function(){
	 	WdatePicker({
	 		el:'time',
	 	})
	 });
	 $("#time1").on("click",function(){
	 	WdatePicker({
	 		el:'time1',
	 	})
	 })
	 $(".mysql button").on('click',function(){
	 	//$(this).addClass('btnStyle').parent().siblings().find('button').removeClass('btnStyle');
	 	var val=$(this).text();
	 	if(val=='上架'){
	 		$(this).removeClass('btnStyle');
	 		$(this).text('下架');
	 		
	 	}else if(val=='下架'){
	 		$(this).text('上架');
	 		$(this).addClass('btnStyle');
	 		$('div.sqlBox').show();
	 	}
	 	$('#close').click(function(){
	 		$('div.sqlBox').hide();
	 		$('.mysql button').eq($(this).index()).removeClass('btnStyle')

	 	});
	 })

	 $('.timeChange a').on('click',function(){
	 	$(this).addClass('change').siblings().removeClass('change');
	 })
})
});
define("districtMysql_init.js", function(){});

