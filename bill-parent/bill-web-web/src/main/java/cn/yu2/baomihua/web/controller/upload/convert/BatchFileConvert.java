package cn.yu2.baomihua.web.controller.upload.convert;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BatchFileConvert {

	@Autowired
	FileConverterFactory fileConverterFactory;

	@RequestMapping("convert")
	public String convert(@RequestParam(required = false, defaultValue = "") String fileDir, Model model)
			throws Exception {
		fileDir = fileDir.trim();
		List<File> list = new ArrayList<File>();
		File sourceFile = new File(fileDir);
		if (sourceFile.isDirectory()) {
			getAllFiles(sourceFile, list);
			System.err.println(list.size());
			HashSet<File> h = new HashSet<File>(list);
			list.clear();
			list.addAll(h);
			System.err.println(list.size());
		} else if (sourceFile.isFile()) {
			list.add(sourceFile);
		} else
			model.addAttribute("errorMsg", "请填写正确的文件目录或者文件地址");
		for (int i = 0; i < list.size(); i++) {
			if (i % 100 == 0)
				new Thread().sleep(1000);
			else {
				fileConverterFactory.doConvert(list.get(i).getPath());
			}
			model.addAttribute("errorMsg", "正在对" + list.size() + "个文件进行转码操作，请耐心等待");
		}
		model.addAttribute("fileDir", fileDir);
		model.addAttribute("unconvertList", new ArrayList<String>());
		return "convert";
	}

	public static void getAllFiles(File sourceDir, List<File> list) {
		File[] files = sourceDir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getAllFiles(file, list);
			} else {
				list.add(file);
			}
		}
	}

	@RequestMapping("unConvert")
	public String unConvert(@RequestParam(required = false, defaultValue = "") String fileDir, Model model)
			throws Exception {
		fileDir = fileDir.trim();
		List<String> list = new ArrayList<String>();
		File sourceFile = new File(fileDir);
		if (sourceFile.isDirectory()) {
			getUnConvert(sourceFile, list);
			HashSet<String> h = new HashSet<String>(list);
			list.clear();
			list.addAll(h);
			Collections.sort(list);
		} else if (sourceFile.isFile()) {
			list.add(sourceFile.getPath());
		} else
			model.addAttribute("errorMsg", "请填写正确的文件目录或者文件地址");
		model.addAttribute("fileDir", fileDir);
		model.addAttribute("unconvertList", list);
		return "convert";
	}

	public static void getUnConvert(File sourceDir, List<String> list) {
		File[] files = sourceDir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getUnConvert(file, list);
			} else {
				String filePath = file.getPath();
				String[] fileNameAndExt = filePath.split("\\.");
				if (Arrays.asList(new String[] { "DOC", "DOCX", "PPT", "PPTX", "XLS", "XLSX" })
						.contains(fileNameAndExt[1].toUpperCase())) {
					if (!new File(fileNameAndExt[0] + ".swf").exists()) {
						if (new File(fileNameAndExt[0] + ".pdf").exists()) {
							list.add((fileNameAndExt[0] + ".pdf"));
						} else {
							if(!filePath.contains("_as."))
								list.add(filePath);
						}
					}
				} else if ((Arrays.asList(new String[] { "WAV", "WMA" }).contains(fileNameAndExt[1].toUpperCase()))) {
					if (!new File(fileNameAndExt[0] + ".mp3").exists())
						list.add(filePath);
				} else if ((Arrays.asList(new String[] { "PDF" }).contains(fileNameAndExt[1].toUpperCase())))
					if (!new File(fileNameAndExt[0] + ".swf").exists())
						list.add(filePath);
			}
		}

	}
}
