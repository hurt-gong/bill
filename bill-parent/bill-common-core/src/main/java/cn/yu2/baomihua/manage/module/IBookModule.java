package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Book;
import cn.yu2.baomihua.manage.entity.BookTag;

/**
 * @Description:教材module
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 下午3:09:19
 */
public interface IBookModule {

	/**
	 * 获取教材列表
	 */
	public Response<List<Book>> getBookListModule(Long phId, Long subId);

	/**
	 * 添加教材
	 */
	public int insertBookModule(String bookName, String ageId, String phId, String subId);

	/**
	 * 删除教材
	 */
	public int deleteBookModule(Long id);

	/**
	 * 查询单条记录
	 */
	public int editBookModule(Long id, String bookName, String phaseId, Integer type);

	public BookTag selectBookTag(Long bookId, Long tagId, Integer type);

	public Book getBookToName(String name);

	public List<Book> getBookList();

	public List<BookTag> getBookTagById(Long phaseId, Long gradeId, Long subjectId);
}
