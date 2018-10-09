package cn.yu2.baomihua.web.controller.upload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;

import cn.yu2.baomihua.core.common.cos.CosFile;
import cn.yu2.baomihua.core.common.cos.CosMultipartRequest;
import cn.yu2.baomihua.core.common.cos.UploadMsg;
import cn.yu2.baomihua.core.common.upload.AjaxUtil;
import cn.yu2.baomihua.core.common.upload.FileUtil;
import cn.yu2.baomihua.core.common.upload.UploadConfig;
import cn.yu2.baomihua.manage.entity.FileMd5;
import cn.yu2.baomihua.manage.module.IFileMd5Module;
import cn.yu2.baomihua.web.controller.BaseController;
import cn.yu2.baomihua.web.controller.upload.convert.FileConverterFactory;

@Controller
@RequestMapping("/sourceUpload")
public class SourceUploadController extends BaseController {

	@Resource(name = "fileMd5ModuleImpl")
	private IFileMd5Module fileMd5Module;

	@Autowired
	private FileConverterFactory fileConverterFactory;

	/* 限制最大上传 500M */
	private final static int MAX_POST_SIZE = 500 * 1024 * 1024;

	/**
	 * 上传文件
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@Login(action = Action.Skip)
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String file(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		AjaxUtil.allowJsCrossdoamin(response);
		ArrayList<UploadMsg> msgList = new ArrayList<UploadMsg>();
		String dir = UploadConfig.getSaveDir();
		String saveDir = FileUtil.getSystemDir(dir);
		System.out.println(saveDir);
		try {
			CosMultipartRequest cos = new CosMultipartRequest(request, saveDir, MAX_POST_SIZE);
			cos.setFileTypeExts(".pdf;.xls;.xlsx;.doc;.docx;.ppt;.pptx");
			cos.upload();
			Enumeration<?> files = cos.getFileNames();
			while (files.hasMoreElements()) {
				UploadMsg msg = new UploadMsg();
				String name = (String) files.nextElement();
				CosFile cf = cos.getCosFile(name);
				if (cf != null) {
					/**
					 * 上传成功
					 */
					if (cf.isSuccess()) {
						// 上传至 FastDfs
						// msg = FastDfsHelper.uploadFastDfs(request, saveDir,
						// cf,dfsClient, fileMd5Module);
						msg.setSize(cf.getSize());
						msg.setUrl("http://edu.bjhd.gov.cn/up/mooc/" + cf.getFilename());
						String newFileStr = saveDir + "/" + cf.getFilename();
						fileConverterFactory.doConvert(newFileStr);
						System.out.println(newFileStr);
						System.out.println(msg.getUrl());
					}

					// 上传提示
					msg.setMsg(cf.getUploadCode().desc());
				}
				msgList.add(msg);
			}
		} catch (IOException e) {
			logger.error("uploadFile error.", e);
		}

		/**
		 * 1、如果只有一个文件返回 UploadMsg 对象 2、其他情况返回 UploadMsg 列表
		 */

		if (msgList.size() == 1) {
			return JSON.toJSONString(msgList.get(0));
		}
		return JSON.toJSONString(msgList);

		/*
		 * //设置编码 request.setCharacterEncoding("utf-8"); //获得磁盘条目文件工厂
		 * DiskFileItemFactory factory = new DiskFileItemFactory();
		 * //获取文件需要上传到的路径 String path = request.getRealPath("/images/upload");
		 * File file = new File(path); if(!file.exists()){ file.mkdirs(); }
		 * //设置储存室，减小内存占用 factory.setRepository(new File(path));
		 * //设置缓存大小，当上传文件的容量超过该缓存时，直接放到储存室
		 * factory.setSizeThreshold(500*1024*1024); //高水平的API文件上传处理
		 * ServletFileUpload upload = new ServletFileUpload(factory); try {
		 * //可以上传多个文件 List<FileItem> list = (List<FileItem>)
		 * upload.parseRequest(request); for (FileItem item : list) {
		 * //获取表单的属性名字 String name = item.getFieldName(); //如果获取的表单信息是普通的文本信息
		 * if(item.isFormField()){ //获取用户具体输入的字符串 String value =
		 * item.getString(); request.setAttribute(name, value); } //对图片、视频等处理
		 * else{ //获取路径名 String value=item.getName(); //索引到最后一个反斜杠 int start =
		 * value.lastIndexOf("\\"); //截取上传文件的字符串名字，加1是去掉反斜杠 String fileName =
		 * value.substring(start+1); request.setAttribute(name, fileName);
		 * //真正写到磁盘上 item.write(new File(path, fileName)); }
		 * 
		 * } response.getWriter().write("{\"code\":200}"); } catch (Exception e)
		 * { e.printStackTrace(); } return "mooc/course/new_class";
		 */
	}

	/**
	 * 上传查询
	 * <p>
	 * 根据md5值支持秒传
	 * </p>
	 */
	@ResponseBody
	@Login(action = Action.Skip)
	@RequestMapping("/search")
	public String search(String md5) {
		boolean exist = true;
		JSONObject jo = new JSONObject();
		FileMd5 fileMd5 = fileMd5Module.getFileMd5ByMd5(md5).getBody();
		if (fileMd5 == null) {
			exist = false;
		}
		jo.put("exist", exist);
		jo.put("data", fileMd5);
		return JSON.toJSONString(jo);
	}

}
