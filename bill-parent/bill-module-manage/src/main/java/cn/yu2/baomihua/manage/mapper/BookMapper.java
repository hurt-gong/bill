package cn.yu2.baomihua.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.manage.entity.Book;

/**
 *
 * Book 表数据库控制层接口
 *
 */
public interface BookMapper extends AutoMapper<Book> {
	// , @Param("name")String name
	public List<Book> getBookList(@Param("phId") Long phId, @Param("subId") Long subId);

	public Book getBookToName(@Param("name") String name);
}