package cn.yu2.baomihua.openapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.entity.MsgHistory;
import cn.yu2.baomihua.openapi.mapper.MsgHistoryMapper;
import cn.yu2.baomihua.openapi.service.IMsgHistoryService;

/**
 *
 * 表数据服务层接口实现类
 *
 */
@Service
public class MsgHistoryServiceImpl extends BaseServiceImpl<MsgHistoryMapper, MsgHistory>implements IMsgHistoryService {

	/**
	 * 保存消息记录
	 * <p>
	 * Title: saveMsgHistory
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param msgHistory
	 */
	public void saveMsgHistory(MsgHistory msgHistory) {
		baseMapper.insert(msgHistory);
	}
}