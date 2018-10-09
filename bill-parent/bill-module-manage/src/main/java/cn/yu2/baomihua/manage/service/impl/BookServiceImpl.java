package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.toolkit.IdWorker;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.entity.Book;
import cn.yu2.baomihua.manage.entity.BookTag;
import cn.yu2.baomihua.manage.mapper.BookMapper;
import cn.yu2.baomihua.manage.service.IBookService;
import cn.yu2.baomihua.manage.service.IBookTagService;

/**
 *
 * Book 表数据服务层接口实现类
 *
 */
@Service
public class BookServiceImpl extends BaseServiceImpl<BookMapper, Book>implements IBookService {

	@Autowired
	private IBookTagService bookTagService;

	@Override
	public List<Book> getBookListService(Long phId, Long subId) {
		List<Book> data = baseMapper.getBookList(phId, subId);
		return data;
	}

	@Override
	public int insertBookService(String bookName, String ageId, String phId, String subId) {
		Book book = new Book();
		Long l = IdWorker.getId();
		book.setId(l);
		book.setName(bookName);
		book.setCode("1");
		book.setStatus(1);
		int i = baseMapper.insert(book);

		this.bookTagService.insertBookTagService(l, Long.valueOf(phId), 1);
		this.bookTagService.insertBookTagService(l, Long.valueOf(ageId), 2);
		this.bookTagService.insertBookTagService(l, Long.valueOf(subId), 3);

		return i;
	}

	@Override
	public int deleteBookService(Long id) {
		return this.baseMapper.deleteById(id);
	}

	@Override
	public int editBookService(Long id, String bookName, String phaseId, Integer type) {
		Book book = this.baseMapper.selectById(id);
		book.setName(bookName);

		// 修改bookTag
		BookTag bt = this.bookTagService.selectBookTag(Long.valueOf(id), Long.valueOf(phaseId), type);
		bt.setTagId(Long.valueOf(phaseId));
		this.bookTagService.updateById(bt);
		return this.baseMapper.updateById(book);
	}

	@Override
	public Book getBookToName(String name) {
		return baseMapper.getBookToName(name);
	}

	@Override
	public List<Book> getBookList() {
		return baseMapper.selectList(null);
	}
}