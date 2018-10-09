package cn.yu2.baomihua.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.manage.entity.BookTag;

/**
 *
 * BookTag 表数据库控制层接口
 *
 */
public interface BookTagMapper extends AutoMapper<BookTag> {

	public BookTag selectBookTag(@Param ("bookId") Long bookId, @Param ("tagId") Long tagId, @Param ("type") Integer type);
	
	public int deleteBookTag(@Param ("bookId") Long bookId);
	
	public List<BookTag> getBookTagById(@Param("phaseId") Long phaseId,@Param("gradeId") Long gradeId,@Param("subjectId") Long subjectId);
}