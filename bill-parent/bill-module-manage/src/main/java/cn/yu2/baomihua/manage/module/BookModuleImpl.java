package cn.yu2.baomihua.manage.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Book;
import cn.yu2.baomihua.manage.entity.BookTag;
import cn.yu2.baomihua.manage.service.IBookService;
import cn.yu2.baomihua.manage.service.IBookTagService;

/**
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 下午3:16:45
 */
public class BookModuleImpl extends AbstractModule implements IBookModule {

	@Autowired
	private IBookService bookService;

	@Autowired
	private IBookTagService bookTagService;

	@Override
	public Response<List<Book>> getBookListModule(Long phId, Long subId) {
		return success(bookService.getBookListService(phId, subId));
	}

	@Override
	public int insertBookModule(String bookName, String ageId, String phId, String subId) {
		return this.bookService.insertBookService(bookName, ageId, phId, subId);
	}

	@Override
	public int deleteBookModule(Long id) {
		int i = this.bookService.deleteBookService(id);
		int j = this.bookTagService.deleteBookTag(id);
		return i;
	}

	@Override
	public int editBookModule(Long id, String bookName, String phaseId, Integer type) {
		return this.bookService.editBookService(id, bookName, phaseId, type);
	}

	@Override
	public BookTag selectBookTag(Long bookId, Long tagId, Integer type) {
		return this.bookTagService.selectBookTag(bookId, tagId, type);
	}

	@Override
	public Book getBookToName(String name) {
		return this.bookService.getBookToName(name);
	}

	@Override
	public List<Book> getBookList() {
		return bookService.getBookList();
	}

	@Override
	public List<BookTag> getBookTagById(Long phaseId, Long gradeId, Long subjectId) {
		return bookTagService.getBookTagById(phaseId, gradeId, subjectId);
	}

}
