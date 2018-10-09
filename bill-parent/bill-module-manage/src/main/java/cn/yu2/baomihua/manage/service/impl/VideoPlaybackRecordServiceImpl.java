package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.entity.VideoPlaybackRecord;
import cn.yu2.baomihua.manage.mapper.VideoPlaybackRecordMapper;
import cn.yu2.baomihua.manage.service.IVideoPlaybackRecordService;

/**
 *
 * VideoPlaybackRecord 表数据服务层接口实现类
 *
 */
@Service
public class VideoPlaybackRecordServiceImpl extends BaseServiceImpl<VideoPlaybackRecordMapper, VideoPlaybackRecord>
		implements IVideoPlaybackRecordService {

	@Override
	public List<VideoPlaybackRecordDTO> getRecordPage( Page<VideoPlaybackRecordDTO> page, Long StudentId ) {
		return baseMapper.getRecordPage(page, StudentId);
	}


}