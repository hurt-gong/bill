package cn.yu2.baomihua.manage.service;

import cn.yu2.baomihua.manage.entity.BookTag;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ISuperService;

/**
 *
 * BookTag 表数据服务层接口
 *
 */
public interface IBookTagService extends ISuperService<BookTag> {
	/**
	 * 添加教材
	 */
	public int insertBookTagService(Long bookId, Long tagId, Integer type);
	
	public BookTag selectBookTag(Long bookId, Long tagId, Integer type);
	
	public int deleteBookTag(@Param ("bookId") Long bookId);
	
	public List<BookTag> getBookTagById(@Param ("phaseId") Long phaseId,@Param ("gradeId") Long gradeId,@Param ("subjectId") Long subjectId);
}