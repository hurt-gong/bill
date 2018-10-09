package cn.yu2.baomihua.web.controller.manage.school;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.ManageConstant;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.core.common.util.POIUtil;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Book;
import cn.yu2.baomihua.manage.entity.Phase;
import cn.yu2.baomihua.manage.entity.Section;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.module.IBookModule;
import cn.yu2.baomihua.manage.module.IPhaseModule;
import cn.yu2.baomihua.manage.module.ISectionModule;
import cn.yu2.baomihua.manage.module.ISubjectModule;
import cn.yu2.baomihua.manage.module.ITeachModule;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/manage/school/section/")
public class SchoolSectionController extends BaseController {

	@Autowired
	private ISectionModule sectionModule;

	@Resource(name = "phaseModuleImpl")
	private IPhaseModule phaseModuleImpl;

	@Resource(name = "teachModuleImpl")
	private ITeachModule teachModuleImpl;

	@Resource(name = "subjectModuleImpl")
	private ISubjectModule subjectModuleImpl;

	@Resource(name = "bookModule")
	private IBookModule bookModule;

	/**
	 * 教材日志维护
	 * 
	 * @param model
	 * @param pId
	 *            目录父Id
	 * @param bookId
	 *            教材Id
	 * @param subjectId
	 *            学科Id
	 * @param phaseId
	 *            学段ID
	 * @return
	 */
	@Permission(PermiConstant.SCHOOL_BOOK)
	@RequestMapping("sectionList")
	public String section(Model model, @RequestParam(value = "phaseId", required = false) Long phaseId,
			@RequestParam(value = "subjectId", required = false) Long subjectId,
			@RequestParam(value = "bookId", required = false) Long bookId,
			@RequestParam(value = "sectionId", required = false) Long sectionId) {
		List<Phase> phaseList = teachModuleImpl.listPhase(StatusEnum.ON.key()).getBody();
		if (null == phaseId && phaseList.size() > 0) {
			phaseId = phaseList.get(0).getId();
		}
		List<Subject> subList = subjectModuleImpl.listSubject(StatusEnum.ON.key(), phaseId, getCurrentSchoolId())
				.getBody();
		List<Book> bookList = null;
		if (null == subjectId && subList.size() > 0) {
			subjectId = subList.get(0).getId();
		}
		if (null != subjectId) {
			bookList = bookModule.getBookListModule(phaseId, subjectId).getBody();
		}
		if (null == bookId && bookList.size() > 0) {
			bookId = bookList.get(0).getId();
		}
		List<Section> firstSectionList = sectionModule.getSectionListModule(ManageConstant.SECTION_PID, bookId)
				.getBody();
		if (null == sectionId && firstSectionList.size() > 0) {
			sectionId = firstSectionList.get(0).getId();
		}
		List<Section> secondSectionList = sectionModule.getSectionListModule(sectionId, bookId).getBody();
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("phaseId", phaseId);
		paramMap.put("subjectId", subjectId);
		paramMap.put("bookId", bookId);
		paramMap.put("sectionId", sectionId);
		model.addAttribute("phaseList", phaseList);
		model.addAttribute("subList", subList);
		model.addAttribute("bookList", bookList);
		model.addAttribute("firstSectionList", firstSectionList);
		model.addAttribute("secondSectionList", secondSectionList);
		model.addAttribute("paramMap", paramMap);
		return "manage/school/book/sectionList";
	}

	/**
	 * 获取学科下拉
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("subjectJson")
	public String subjectJson(@RequestParam("phaseId") Long phaseId) {
		List<Subject> subjectList = subjectModuleImpl.listSubject(StatusEnum.ON.key(), phaseId, getCurrentSchoolId())
				.getBody();
		return callbackSuccess(subjectList);
	}

	/**
	 * 获取书籍下拉
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("bookJson")
	public String bookJson(Long phaseId, Long subjectId) {
		// 查询教材
		List<Book> bookList = bookModule.getBookListModule(phaseId, subjectId).getBody();
		return callbackSuccess(bookList);
	}

	@ResponseBody
	@RequestMapping("section")
	public String getSection(Model model, Long pId, Long bookId) {
		Response<List<Section>> resp = sectionModule.getSectionListModule(pId, bookId);
		return callbackSuccess(resp.getBody());
	}

	@Permission(PermiConstant.SCHOOL_BOOK)
	@ResponseBody
	@RequestMapping("deleteSection")
	public String deleteSection(Model model, Long id) {
		return callbackSuccess(sectionModule.deleteSectionModule(id).getBody());
	}

	@Permission(PermiConstant.SCHOOL_BOOK)
	@ResponseBody
	@RequestMapping("editSection")
	public String editSection(Model model, Long id, String name) {
		return callbackSuccess(sectionModule.updateSection(id, name).getBody());
	}

	@Permission(PermiConstant.SCHOOL_BOOK)
	@ResponseBody
	@RequestMapping("insertSection")
	public String insertSection(Long bookId, Long pId, String name) {
		if (!"".equals(name)) {
			return callbackSuccess(sectionModule.saveSection(name, bookId, pId));
		}
		return callbackFail("保存失败");
	}

	/**
	 * 下载目录模版
	 */
	@Permission(PermiConstant.SCHOOL_BOOK)
	@RequestMapping("/downloadSection")
	public String downloadSection(HttpServletResponse response) {
		/*
		 * response.setCharacterEncoding("utf-8");
		 * response.setContentType("application/vnd.ms-excel"); String fileName
		 * = ManageConstant.DISTRIC_BOOK_EXCEL +
		 * ManageConstant.EXCEL_EXTENTSION;
		 * response.setHeader("Content-Disposition", "attachment;fileName=" +
		 * fileName);
		 */
		response = POIUtil.excelResponse(request, response, ManageConstant.DISTRIC_BOOK_EXCEL);
		try {
			HSSFWorkbook workbook = POIUtil.createWorkbook();
			HSSFSheet sheet = POIUtil.createSheet(workbook, ManageConstant.DISTRIC_BOOK_EXCEL,
					ManageConstant.DISTRIC_BOOK_EXCEL_TITLES);

			// 查询学科
			List<Phase> phaseList = teachModuleImpl.listPhase(StatusEnum.ON.key()).getBody();
			POIUtil.addDropDownListHSSFDV(workbook, sheet, this.listToString(phaseList), 1, 500, 0, 0, 0);

			// 查询学段
			Page<Subject> subPage = getPage();
			List<Subject> subList = subjectModuleImpl.listSubject(StatusEnum.ON.key(), null, getCurrentSchoolId())
					.getBody();
			POIUtil.addDropDownListHSSFDV(sheet, this.listToString(subList), 1, 500, 1, 1);

			// 查询教材
			List<Book> resp = bookModule.getBookListModule(null, null).getBody();
			POIUtil.addDropDownListHSSFDV(sheet, this.listToString(resp), 1, 500, 2, 2);

			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上传目录
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	@ResponseBody
	@RequestMapping("/uploadSection")
	public String multiPurchaseSubmit(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		InputStream is = null;
		HSSFWorkbook hssf = null;

		List<Section> secList = new ArrayList<Section>();// 目录List
		try {
			// 输出
			CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			MultipartHttpServletRequest mRequest = resolver.resolveMultipart(request);
			Iterator<String> it = mRequest.getFileNames();

			while (it.hasNext()) {
				String name = it.next();
				CommonsMultipartFile file = (CommonsMultipartFile) mRequest.getFile(name);// 获取文件名字
				// String fileName = file.getOriginalFilename();
				is = file.getInputStream();
			}

			hssf = new HSSFWorkbook(is);
			HSSFSheet hssfSheet = hssf.getSheetAt(0);

			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				Section sec = new Section();// 目录对象
				Section sec2 = new Section();// 二级目录对象

				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				// XSSFCell cell;
				// 验证学段
				Phase ph = this.phaseModuleImpl.getPhase(hssfRow.getCell(0) + "");
				if (ph != null) {
					// 验证学科
					List<Subject> subList = subjectModuleImpl
							.listSubject(StatusEnum.ON.key(), ph.getId(), getCurrentSchoolId()).getBody();
					Subject sub = this.subjectModuleImpl.getBookToName(hssfRow.getCell(1) + "");
					if (sub != null && subList.size() > 0) {
						for (Subject su : subList) {
							if (su.getId().equals(sub.getId())) {
								// 验证教材
								List<Book> bookPage = bookModule
										.getBookListModule(Long.valueOf(ph.getId()), Long.valueOf(sub.getId()))
										.getBody();
								Book book = this.bookModule.getBookToName(hssfRow.getCell(2) + "");
								if (book != null && bookPage.size() > 0) {
									for (Book b : bookPage) {
										if (b.getId().equals(book.getId())) {
											Long id = IdWorker.getId();
											sec.setId(id);
											sec.setName(hssfRow.getCell(3) + "");
											sec.setBookId(book.getId());
											sec.setPid(0l);
											secList.add(sec);
											if (!"".equals(hssfRow.getCell(4)) && null != hssfRow.getCell(4)) {
												sec2.setName(hssfRow.getCell(4) + "");
												sec2.setBookId(book.getId());
												sec2.setPid(id);
												secList.add(sec2);
											} else {
												return callbackFail("失败");
											}

										}
										break;
									}
								} else {
									return callbackFail("失败");
								}
								break;
							} else {
								return callbackFail("失败");
							}
						}
					} else {
						return callbackFail("失败");
					}
				} else {
					return callbackFail("失败");
				}
			}
			this.sectionModule.saveSectionList(secList);
			hssf.close();

			return callbackSuccess("");
		} catch (Exception e) {
			logger.error(":error", e);
			return callbackFail("失败");
		}
	}

	public String[] listToString(List list) {
		List<String> clazzs = new ArrayList<String>();
		String[] strArr = new String[list.size()];
		for (Object obj : list) {
			String str = JSONObject.toJSONString(obj);
			JSONObject json = JSONObject.parseObject(str);
			// System.out.println(json.get("name"));
			clazzs.add(json.get("name") + "");
		}
		clazzs.toArray(strArr);
		return strArr;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		String[] str = new SchoolSectionController().listToString(list);
		System.out.println(str);
	}
}
