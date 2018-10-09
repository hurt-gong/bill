package cn.yu2.baomihua.web.controller.manage.distric;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Book;
import cn.yu2.baomihua.manage.entity.BookTag;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.Phase;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.enums.BookTagEnum;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.module.IBookModule;
import cn.yu2.baomihua.manage.module.IGradeModule;
import cn.yu2.baomihua.manage.module.ISubjectModule;
import cn.yu2.baomihua.manage.module.ITeachModule;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/manage/distric/book/")
public class BookController extends BaseController {

	@Resource(name = "bookModule")
	private IBookModule bookModule;

	@Resource(name = "teachModuleImpl")
	private ITeachModule teachModuleImpl;

	@Resource(name = "subjectModuleImpl")
	private ISubjectModule subjectModuleImpl;

	@Resource(name = "gradeModuleImpl")
	private IGradeModule gradeModuleImpl;

	/*
	 * @Resource(name = "gradeModuleImpl") private I gradeModuleImpl;
	 */

	/**
	 * 教材管理
	 * 
	 * @param model
	 * @return
	 */
	@Permission(PermiConstant.DISTRIC_BOOK)
	@RequestMapping("list")
	public String list(Model model, @RequestParam(value = "phaseId", required = false) String phaseId,
			@RequestParam(value = "subjectId", required = false) String subjectId) {
		// 查询年级
		Page<Grade> graPage = getPage(50);
		Page<Grade> graList = this.gradeModuleImpl.getGradeList(graPage).getBody();
		model.addAttribute("graList", graList.getRecords());
		System.out.println("1111222222221");
		// 查询学段
		List<Phase> phaseList = teachModuleImpl.listPhase(StatusEnum.ON.key()).getBody();
		Phase ph = phaseList.get(0);
		model.addAttribute("phaseList", phaseList);

		// 查询学科
		if (phaseId == null) {
			phaseId = ph.getId() + "";
		}
		model.addAttribute("phaseId", phaseId);
		List<Subject> subList = subjectModuleImpl
				.listSubject(StatusEnum.ON.key(), Long.valueOf(phaseId), getCurrentSchoolId()).getBody();
		if (subList.size() > 0) {
			Subject sub = subList.get(0);
			model.addAttribute("subId", sub.getId());
			model.addAttribute("subList", subList);
			if (subjectId == null) {
				subjectId = sub.getId() + "";
			}
			Page<Book> page = new Page<>(1, 10);
			Response<List<Book>> resp = bookModule.getBookListModule(Long.valueOf(phaseId), Long.valueOf(subjectId));
			model.addAttribute("subjectId", subjectId);
			model.addAttribute("bookList", resp.getBody());

			// 教材
			BookTag bt = this.bookModule.selectBookTag(Long.valueOf(phaseId), Long.valueOf(subjectId),
					BookTagEnum.AGE.key());
			if (bt != null) {
				model.addAttribute("gra", bt.getTagId());
			}

			/*
			 * BookTag bt1 =
			 * this.bookModule.selectBookTag(Long.valueOf(phaseId),
			 * Long.valueOf(subjectId), BookTagEnum.AGE.key()); if ( bt1 != null
			 * ) { model.addAttribute("gra", bt1.getTagId()); }
			 */
			model.addAttribute("phaseId", phaseId);
			model.addAttribute("subjectId", subjectId);
		}

		return "manage/distric/book/bookList";
	}

	/**
	 * 点击学科显示教材
	 * 
	 * @param model
	 * @param phaseId
	 * @param subjectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("subToList")
	public String subToList(Model model, @RequestParam("phaseId") String phaseId,
			@RequestParam(value = "subjectId", required = false) String subjectId) {
		Response<List<Book>> resp = null;
		resp = bookModule.getBookListModule(Long.valueOf(phaseId), Long.valueOf(subjectId));
		return callbackSuccess(resp.getBody());
	}

	@Permission(PermiConstant.DISTRIC_BOOK)
	@RequestMapping("insertBook")
	public String insertBook(Model model, String bookName, String yuan, String id, String ageId, String phId,
			String subId) {
		if (yuan != null && !"".equals(yuan)) {
			if (yuan != bookName || !yuan.equals(bookName)) {
				this.bookModule.editBookModule(Long.valueOf(id), bookName, ageId, BookTagEnum.AGE.key());
			}
		} else {
			if (!"".equals(bookName)) {
				this.bookModule.insertBookModule(bookName, ageId, phId, subId);
			}
		}
		return redirectTo("/manage/distric/book/list.html?phaseId=" + phId + "&subjectId=" + subId);
	}

	@Permission(PermiConstant.DISTRIC_BOOK)
	@ResponseBody
	@RequestMapping("deleteBook")
	public String deleteBook(Model model, Long id) {
		return callbackSuccess(bookModule.deleteBookModule(id));
	}

	@Permission(PermiConstant.DISTRIC_BOOK)
	@RequestMapping("editBook")
	public String editBook(Model model, Long id) {
		int book = this.bookModule.editBookModule(id, "", "", 0);
		model.addAttribute("b", book);
		return redirectTo("/manage/distric/book/list.html");
	}
}
