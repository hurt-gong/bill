package cn.yu2.baomihua.manage.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.manage.entity.FileMd5;

/**
 *
 * FileMd5表数据库控制层接口
 *
 */
public interface FileMd5Mapper extends AutoMapper<FileMd5> {


	FileMd5 selectById( @Param("md5" ) String md5);


	int addFileMd5( FileMd5 fileMd5 );

}