package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yu2.baomihua.manage.entity.BookTag;
import cn.yu2.baomihua.manage.mapper.BookTagMapper;
import cn.yu2.baomihua.manage.service.IBookTagService;
import cn.yu2.baomihua.core.service.BaseServiceImpl;

/**
 *
 * BookTag 表数据服务层接口实现类
 *
 */
@Service
public class BookTagServiceImpl extends BaseServiceImpl<BookTagMapper, BookTag> implements IBookTagService {

	@Autowired
	private BookTagMapper bookTagMapper;
	
	@Override
	public int insertBookTagService(Long bookId, Long tagId, Integer type) {
		BookTag book = new BookTag();
		book.setBookId(bookId);
		book.setTagId(tagId);
		book.setType(type);
		return baseMapper.insert(book);
	}

	@Override
	public BookTag selectBookTag(Long bookId, Long tagId, Integer type) {
		return this.baseMapper.selectBookTag(bookId, tagId, type);
	}

	@Override
	public int deleteBookTag(Long bookId) {
		return this.baseMapper.deleteBookTag(bookId);
	}

	@Override
	public List<BookTag> getBookTagById(Long phaseId, Long gradeId, Long subjectId) {
		return bookTagMapper.getBookTagById(phaseId, gradeId, subjectId);
	}
}

