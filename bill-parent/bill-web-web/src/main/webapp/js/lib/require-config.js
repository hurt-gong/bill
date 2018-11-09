// var base = (www_head == '' ? window.location.protocol+'//'+window.location.host : www_head);
var baseDomain = "/js";
var uploadDomain = "http://upload.bjhd.gov.cn";

require.config({
	baseUrl: baseDomain,
	paths: {
		"jquery": "lib/jquery/1.8.3/jquery",
		"dialog": "lib/layer/layer",        
		"jqtransform": "lib/jqTransform/jqtransform",
		"headerSlideDown": "common/headerslideDown",
		"popup_box": "common/popup_box"
	},
	charset: "utf-8",
	preload: ["jquery","dialog"]
});
var admin_base = baseDomain + "admin/";
//去除首尾空格
String.prototype.trim = function() {
	return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
};

//去除所有空格
String.prototype.trimAll = function() {
	return this.replace(/\s\s*/g, '');
};
