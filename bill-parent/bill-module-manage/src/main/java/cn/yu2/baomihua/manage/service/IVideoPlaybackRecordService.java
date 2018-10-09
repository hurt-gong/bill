package cn.yu2.baomihua.manage.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.entity.VideoPlaybackRecord;

/**
 *
 * VideoPlaybackRecord 表数据服务层接口
 *
 */
public interface IVideoPlaybackRecordService extends ISuperService<VideoPlaybackRecord> {

	/*获取播放记录*/
	public List<VideoPlaybackRecordDTO> getRecordPage( Page<VideoPlaybackRecordDTO> page, Long StudentId );

}