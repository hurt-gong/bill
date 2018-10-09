package cn.yu2.baomihua.manage.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.manage.entity.Book;

/**
 *
 * Book 表数据服务层接口
 *
 */
public interface IBookService extends ISuperService<Book> {

	/**
	 * 获取教材列表
	 */
	public List<Book> getBookListService(Long phId, Long subId);

	/**
	 * 添加教材
	 */
	public int insertBookService(String bookName, String ageId, String phId, String subId);

	/**
	 * 删除教材
	 */
	public int deleteBookService(Long id);

	/**
	 * 查询单条记录
	 */
	public int editBookService(Long id, String bookName, String phaseId, Integer type);

	public Book getBookToName(String name);

	public List<Book> getBookList();
}