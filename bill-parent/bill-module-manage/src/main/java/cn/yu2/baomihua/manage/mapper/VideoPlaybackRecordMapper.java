package cn.yu2.baomihua.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.entity.VideoPlaybackRecord;

/**
 *
 * VideoPlaybackRecord 表数据库控制层接口
 *
 */
public interface VideoPlaybackRecordMapper extends AutoMapper<VideoPlaybackRecord> {

	public List<VideoPlaybackRecordDTO> getRecordPage( Page<VideoPlaybackRecordDTO> page,
			@Param("studentId") Long studentId );


}