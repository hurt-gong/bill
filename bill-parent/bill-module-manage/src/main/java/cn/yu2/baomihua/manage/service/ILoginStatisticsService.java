package cn.yu2.baomihua.manage.service;

import java.util.Date;
import java.util.List;

import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.manage.entity.LoginStatistics;

/**
 *
 * LoginStatistics 表数据服务层接口
 *
 */
public interface ILoginStatisticsService extends ISuperService<LoginStatistics> {

	/**
	 * 获取登录次数页面浏览量
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public List<LoginStatistics> getLoginCount( Date startDate, Date endDate, Long schoolId, String sort );


	/**
	 * 获取登录次数页面浏览量图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @return List<LoginStatistics>
	 */
	public List<LoginStatistics> getLoginCountChart( Date startDate, Date endDate, Long schoolId );


	/**
	 * 获取校登录次数页面浏览量
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param klassId	班级Id
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public List<LoginStatistics> getSchoolLoginCount( Date startDate,
			Date endDate,
			Long schoolId,
			Long klassId,
			String sort );


	/**
	 * 获取校登录次数页面浏览量图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param klassId	班级Id
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public List<LoginStatistics> getSchoolLoginCountChart( Date startDate,
			Date endDate,
			Long schoolId,
			Long klassId,
			String sort );
}