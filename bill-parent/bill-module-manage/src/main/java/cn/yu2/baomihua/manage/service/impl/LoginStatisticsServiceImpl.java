package cn.yu2.baomihua.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.entity.LoginStatistics;
import cn.yu2.baomihua.manage.mapper.LoginStatisticsMapper;
import cn.yu2.baomihua.manage.service.ILoginStatisticsService;

/**
 *
 * LoginStatistics 表数据服务层接口实现类
 *
 */
@Service
public class LoginStatisticsServiceImpl extends BaseServiceImpl<LoginStatisticsMapper, LoginStatistics>
		implements ILoginStatisticsService {

	@Override
	public List<LoginStatistics> getLoginCount( Date startDate, Date endDate, Long schoolId, String sort ) {
		return baseMapper.getLoginCount(startDate, endDate, schoolId, sort);
	}


	@Override
	public List<LoginStatistics> getLoginCountChart( Date startDate, Date endDate, Long schoolId ) {
		return baseMapper.getLoginCountChart(startDate, endDate, schoolId);
	}


	@Override
	public List<LoginStatistics> getSchoolLoginCount( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			String sort ) {
		return baseMapper.getSchoolLoginCount(startDate, endDate, schoolId, gradeId, sort);
	}


	@Override
	public List<LoginStatistics> getSchoolLoginCountChart( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			String sort ) {
		return baseMapper.getSchoolLoginCountChart(startDate, endDate, schoolId, gradeId, sort);
	}

}