package cn.yu2.baomihua.manage.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.manage.entity.LoginStatistics;

/**
 *
 * LoginStatistics 表数据库控制层接口
 *
 */
public interface LoginStatisticsMapper extends AutoMapper<LoginStatistics> {

	/**
	 * 获取登录次数页面浏览量
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @param sort 		排序字段
	 * @return List<LoginStatistics>
	 */
	public List<LoginStatistics> getLoginCount( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("schoolId") Long schoolId,
			@Param("sort") String sort );


	/**
	 * 获取登录次数页面浏览量图表
	 * 
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param schoolId	学校Id
	 * @return List<LoginStatistics>
	 */
	public List<LoginStatistics> getLoginCountChart( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("schoolId") Long schoolId );


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
	public List<LoginStatistics> getSchoolLoginCount( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("schoolId") Long schoolId,
			@Param("gradeId") Long gradeId,
			@Param("sort") String sort );


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
	public List<LoginStatistics> getSchoolLoginCountChart( @Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("schoolId") Long schoolId,
			@Param("gradeId") Long gradeId,
			@Param("sort") String sort );
}