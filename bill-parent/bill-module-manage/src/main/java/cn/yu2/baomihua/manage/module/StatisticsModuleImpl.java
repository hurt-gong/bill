package cn.yu2.baomihua.manage.module;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.constant.ManageConstant;
import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.ChartDto;
import cn.yu2.baomihua.manage.dto.GradeSubjectDTO;
import cn.yu2.baomihua.manage.dto.HomeworkStatisticsDTO;
import cn.yu2.baomihua.manage.dto.TaskStatisticsDTO;
import cn.yu2.baomihua.manage.dto.VideoPlaybackRecordDTO;
import cn.yu2.baomihua.manage.dto.VideoStatisticsDTO;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.entity.LoginStatistics;
import cn.yu2.baomihua.manage.entity.StudentParent;
import cn.yu2.baomihua.manage.entity.Subject;
import cn.yu2.baomihua.manage.entity.TaskStatistics;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.util.DateUtils;
import cn.yu2.baomihua.manage.service.IGradeService;
import cn.yu2.baomihua.manage.service.ILoginStatisticsService;
import cn.yu2.baomihua.manage.service.IStudentParentService;
import cn.yu2.baomihua.manage.service.ISubjectService;
import cn.yu2.baomihua.manage.service.ITaskStatisticsService;
import cn.yu2.baomihua.manage.service.IVideoPlaybackRecordService;

/**
 * 资源使用统计
 * 
 * @version 1.0
 * @author lizhixiao
 * @date 2016-10-31
 */
public class StatisticsModuleImpl extends AbstractModule implements IStatisticsModule {

	@Resource(name = "loginStatisticsServiceImpl")
	private ILoginStatisticsService loginStatisticsServiceImpl;

	@Resource(name = "taskStatisticsServiceImpl")
	private ITaskStatisticsService taskStatisticsServiceImpl;

	@Resource(name = "videoPlaybackRecordServiceImpl")
	private IVideoPlaybackRecordService videoPlaybackRecordServiceImpl;

	@Resource(name = "studentParentServiceImpl")
	private IStudentParentService studentParentServiceImpl;

	@Resource(name = "gradeServiceImpl")
	private IGradeService gradeServiceImpl;

	@Resource(name = "subjectServiceImpl")
	private ISubjectService subjectServiceImpl;


	@Override
	public Response<Page<TaskStatistics>> getTaskRanking( Page<TaskStatistics> page ) {
		page.setRecords(taskStatisticsServiceImpl.getTaskRanking(page));
		return success(page);
	}


	@Override
	public Response<List<LoginStatistics>> getLoginCount( Date startDate, Date endDate, Long schoolId, String sort ) {
		List<LoginStatistics> loginList = loginStatisticsServiceImpl.getLoginCount(startDate, endDate, schoolId, sort);
		return success(loginList);
	}


	@Override
	public Response<ChartDto> getLoginCountChart( Date startDate, Date endDate, Long schoolId, String sort ) {
		List<LoginStatistics> loginList = loginStatisticsServiceImpl.getLoginCountChart(startDate, endDate, schoolId);
		return success(createLoginCharData(loginList, startDate, endDate, sort));
	}


	@Override
	public Response<List<VideoStatisticsDTO>> getVideoCount( Date startDate, Date endDate, String sort ) {
		List<VideoStatisticsDTO> loginList = taskStatisticsServiceImpl.getVideoCount(startDate, endDate, sort);
		return success(loginList);
	}


	@Override
	public Response<ChartDto> getVideoCountChart( Date startDate, Date endDate, String sort ) {
		List<VideoStatisticsDTO> videoList = taskStatisticsServiceImpl.getVideoCountChart(startDate, endDate);
		return success(createVideoCharData(videoList, startDate, endDate, sort));
	}


	@Override
	public Response<List<HomeworkStatisticsDTO>> getHomeworkQty( Date startDate, Date endDate, String sort ) {
		List<HomeworkStatisticsDTO> homeworkList = taskStatisticsServiceImpl.getHomeworkQty(startDate, endDate, sort);
		return success(homeworkList);
	}


	@Override
	public Response<ChartDto> getHomeworkQtyChart( Date startDate, Date endDate, String sort ) {
		List<HomeworkStatisticsDTO> homeworkList = taskStatisticsServiceImpl.getHomeworkQtyChart(startDate, endDate);
		return success(createHomeworkCharData(homeworkList, startDate, endDate, sort));
	}


	@Override
	public Response<List<TaskStatisticsDTO>> getTaskQty( Date startDate, Date endDate, String sort ) {
		List<TaskStatisticsDTO> taskList = taskStatisticsServiceImpl.getTaskQty(startDate, endDate, sort);
		return success(taskList);
	}


	@Override
	public Response<ChartDto> getTaskQtyChart( Date startDate, Date endDate, String sort ) {
		List<TaskStatisticsDTO> taskList = taskStatisticsServiceImpl.getTaskQtyChart(startDate, endDate);
		return success(createTaskCharData(taskList, startDate, endDate, sort));
	}


	@Override
	public Response<Page<VideoPlaybackRecordDTO>> getVideoPlayBackRecord( Page<VideoPlaybackRecordDTO> page,
			Long studentId ) {
		List<VideoPlaybackRecordDTO> video = videoPlaybackRecordServiceImpl.getRecordPage(page, studentId);
		for ( int i = 0 ; i < video.size() ; i++ ) {
			video.get(i).setDate(DateUtils.getTimeFormatText(video.get(i).getCrDate()));
		}
		page.setRecords(video);
		return success(page);
	}


	@Override
	public Response<Page<VideoPlaybackRecordDTO>> getVideoPlayBackByParent( Page<VideoPlaybackRecordDTO> page,
			Long parentId ) {
		StudentParent studentParent = new StudentParent();
		studentParent.setParentId(parentId);
		StudentParent stuParent = studentParentServiceImpl.selectOne(studentParent);
		if ( null != stuParent ) return getVideoPlayBackRecord(page, stuParent.getStudentId());
		return success(page);
	}


	/**
	 * 创建登录次数图表数据
	 * @param loginList 用户登录次数List
	 */
	private ChartDto createLoginCharData( List<LoginStatistics> loginList, Date startDate, Date endDate, String sort ) {
		Map<String, Integer> map = new HashMap<>();
		for ( int i = 0 ; i < loginList.size() ; i++ ) {
			String logDate = DateUtils.getMonthDay(loginList.get(i).getCrDate());
			if ( "pageView".equals(sort) ) {
				map.put(logDate, loginList.get(i).getPageView());
			} else if ( "loginCount".equals(sort) ) {
				map.put(logDate, loginList.get(i).getLoginCount());
			} else {
				map.put(logDate, Integer.parseInt(loginList.get(i).getLoginIp()));
			}
		}
		return createCharDto(startDate, endDate, map);

	}


	/**
	 * 创建视频图表数据
	 */
	private ChartDto createVideoCharData( List<VideoStatisticsDTO> videoList,
			Date startDate,
			Date endDate,
			String sort ) {
		Map<String, Integer> map = new HashMap<>();
		for ( int i = 0 ; i < videoList.size() ; i++ ) {
			String logDate = DateUtils.getMonthDay(videoList.get(i).getCrDate());
			if ( "videoDibbleQty".equals(sort) ) {
				map.put(logDate, videoList.get(i).getVideoDibbleQty());
			} else if ( "videoCommentQty".equals(sort) ) {
				map.put(logDate, videoList.get(i).getVideoCommentQty());
			} else {
				map.put(logDate, videoList.get(i).getVideoUploadQty());
			}
		}
		return createCharDto(startDate, endDate, map);

	}


	/**
	 * 创建作业图表数据
	 */
	private ChartDto createHomeworkCharData( List<HomeworkStatisticsDTO> homeworkList,
			Date startDate,
			Date endDate,
			String sort ) {
		Map<String, Integer> map = new HashMap<>();
		for ( int i = 0 ; i < homeworkList.size() ; i++ ) {
			String logDate = DateUtils.getMonthDay(homeworkList.get(i).getCrDate());
			if ( "homwkUploadQty".equals(sort) ) {
				map.put(logDate, homeworkList.get(i).getHomwkUploadQty());
			} else {
				map.put(logDate, homeworkList.get(i).getHomwkFinishQty());
			}
		}
		return createCharDto(startDate, endDate, map);
	}


	/**
	 * 创建任务图表数据
	 */
	private ChartDto createTaskCharData( List<TaskStatisticsDTO> taskList, Date startDate, Date endDate, String sort ) {
		Map<String, Integer> map = new HashMap<>();
		for ( int i = 0 ; i < taskList.size() ; i++ ) {
			String logDate = DateUtils.getMonthDay(taskList.get(i).getCrDate());
			if ( "finishStudentQty".equals(sort) ) {
				map.put(logDate, taskList.get(i).getFinishStudentQty());
			} else {
				map.put(logDate, taskList.get(i).getPublishTaskQty());
			}
		}
		return createCharDto(startDate, endDate, map);

	}


	/**
	 * 创建图表
	 * @param startDate	开始日期
	 * @param endDate	结束日期
	 * @param map		数据
	 */
	private ChartDto createCharDto( Date startDate, Date endDate, Map<String, Integer> map ) {
		ChartDto charDto = new ChartDto();
		List<Date> dateList = null;
		try {
			dateList = DateUtils.generateDate(startDate, endDate);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		Collections.reverse(dateList);
		String[] categories = new String[dateList.size()];
		int[] data = new int[dateList.size()];
		for ( int i = 0 ; i < dateList.size() ; i++ ) {
			categories[i] = DateUtils.getMonthDay(dateList.get(i));
			if ( map.containsKey(categories[i]) ) {
				data[i] = map.get(categories[i]);
			} else {
				data[i] = 0;
			}

		}
		charDto.setCategories(categories);
		charDto.setData(data);
		return charDto;
	}


	@Override
	public Response<List<LoginStatistics>> getSchoolLoginCount( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			String sort ) {
		return success(loginStatisticsServiceImpl.getSchoolLoginCount(startDate, endDate, schoolId, gradeId, sort));
	}


	@Override
	public Response<ChartDto> getSchoolLoginCountChart( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			String sort ) {
		List<LoginStatistics> loginStaList = loginStatisticsServiceImpl.getSchoolLoginCountChart(startDate, endDate,
			schoolId, gradeId, sort);
		return success(createLoginCharData(loginStaList, startDate, endDate, sort));
	}


	@Override
	public Response<GradeSubjectDTO> getGradeSubjectByGradeId( Long schoolId, Long gradeId ) {
		GradeSubjectDTO gradeSubDto = new GradeSubjectDTO();
		Grade grade = new Grade();
		grade.setStatus(StatusEnum.ON.key());
		List<Grade> gradeList = gradeServiceImpl.selectList(grade);
		gradeSubDto.setGradeList(gradeList);
		List<Subject> subjectList = null;
		if ( null == gradeId && gradeList.size() > 0 ) gradeId = gradeList.get(0).getId();
		if ( null != gradeId ) {
			subjectList = subjectServiceImpl.getSubjectByGradeId(gradeId, schoolId);
			List<Subject> list = subjectServiceImpl.getSubjectByGradeId(gradeId, ManageConstant.DISTRIC_SCHOOLID);
			subjectList.addAll(list);
		}
		gradeSubDto.setSubjectList(subjectList);
		return success(gradeSubDto);
	}

 


	@Override
	public Response<ChartDto> getSchoolVideoCountChart( Date startDate,
			Date endDate,
			Long schoolId,
			Long gradeId,
			Long subjectId,
			String sort ) {
		List<VideoStatisticsDTO> videoList = taskStatisticsServiceImpl.getSchoolVideoCountChart(startDate, endDate,
			schoolId, gradeId, subjectId, sort);
		return success(createVideoCharData(videoList, startDate, endDate, sort));
	}


	@Override
	public Response<Boolean> updateLoginStatistics( long userId, String ip, Date date ) {
		LoginStatistics loginSta = new LoginStatistics();
		loginSta.setUserId(userId);
		loginSta.setCrDate(DateUtils.parseyyyyMMddDate(""));
		loginSta.setLoginIp(ip);
		LoginStatistics loginStatistics = loginStatisticsServiceImpl.selectOne(loginSta);
		if ( null != loginStatistics ) {
			loginStatistics.setPageView(loginStatistics.getPageView() + 1);
			return success(loginStatisticsServiceImpl.updateById(loginStatistics));
		}
		return success(false);
	}


 

	@Override
	public Response<ChartDto> getSchoolHomwkAndTaskChart( Date startDate,
			Date endDate,
			Integer taskType,
			Long schoolId,
			Long gradeId,
			Long subjectId,
			String sort ) {
		List<HomeworkStatisticsDTO> taskList = taskStatisticsServiceImpl.getSchoolHomwkAndTaskChart(startDate, endDate,
			taskType, schoolId, gradeId, subjectId, sort);
		return success(createHomeworkCharData(taskList, startDate, endDate, sort));
	}
}
