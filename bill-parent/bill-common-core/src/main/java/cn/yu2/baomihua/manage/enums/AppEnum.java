package cn.yu2.baomihua.manage.enums;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.framework.common.IEnum;

/**
 * 系统应用
 * 
 * @author lizhixiao
 * @version 1.0
 * @date 2016-11-01
 */
public final class AppEnum {

	private static final Logger logger = LoggerFactory.getLogger(AppEnum.class);

	public enum StatisticsType implements IEnum {

		STADY_TASK(1, "学习任务"), BASE_STATISTICS(2, "基础统计");

		private final int key;

		private final String desc;


		private StatisticsType( int key, String desc ) {
			this.key = key;
			this.desc = desc;
		}


		@Override
		public int key() {
			return key;
		}


		@Override
		public String desc() {
			return desc;
		}

	}

	public enum SchoolSttQuota implements IEnum {

		VIDEO_DIBBLE_QTY(1, "课程播放次数"), VIDEO_COMMENT_QTY(2, "课程评论次数"), HOMEWORK_FINISH_QTY(3,
				"完成作业数"), HOMEWORK_UPLOAD_QTY(4, "上传作业次数"), FINISH_TASK(5, "学习任务完成数数");

		private final int key;

		private final String desc;


		private SchoolSttQuota( int key, String desc ) {
			this.key = key;
			this.desc = desc;
		}


		@Override
		public int key() {
			return key;
		}


		@Override
		public String desc() {
			return desc;
		}

	}

	public enum SelType implements IEnum {

		SELECT_SCHOOL(1, "按学校查询"), SELECT_APP(2, "按应用查询");

		private final int key;

		private final String desc;


		private SelType( int key, String desc ) {
			this.key = key;
			this.desc = desc;
		}


		@Override
		public int key() {
			return key;
		}


		@Override
		public String desc() {
			return desc;
		}

	}

	/**应用类型*/
	public enum AppType implements IEnum {

		PUBLIC_COURSE(1, "公开课"), HOMEWORK_SYSTEM(2, "作业系统"), STUDY_TASK(3, "学习任务");

		private final int key;

		private final String desc;


		private AppType( int key, String desc ) {
			this.key = key;
			this.desc = desc;
		}


		@Override
		public int key() {
			return key;
		}


		@Override
		public String desc() {
			return desc;
		}

	}

	/**PV，login指标*/
	public enum LoginQuota implements IEnum {

		PAGE_VIEW(1, "浏览量"), LOGIN_COUNT(2, "登录次数"), LOGIN_IP(3, "登录Ip数");

		private final int key;

		private final String desc;


		private LoginQuota( int key, String desc ) {
			this.key = key;
			this.desc = desc;
		}


		@Override
		public int key() {
			return key;
		}


		@Override
		public String desc() {
			return desc;
		}

	}

	/**公开课*/
	public enum PublicCource implements IEnum {

		VIDEO_DIBBLE_QTY(1, "视频点播次数"), VIDEO_UPLOAD_QTY(2, "视频上传次数"), VIDEO_COMMENT_QTY(3, "视频评论次数");

		private final int key;

		private final String desc;


		private PublicCource( int key, String desc ) {
			this.key = key;
			this.desc = desc;
		}


		@Override
		public int key() {
			return key;
		}


		@Override
		public String desc() {
			return desc;
		}

	}

	/**作业上传*/
	public enum HomworkSystem implements IEnum {

		HOMEWORK_UPLOAD_QTY(1, "上传作业次数"), HOMEWORK_FINISH_QTY(2, "完成作业次数");

		private final int key;

		private final String desc;


		private HomworkSystem( int key, String desc ) {
			this.key = key;
			this.desc = desc;
		}


		@Override
		public int key() {
			return key;
		}


		@Override
		public String desc() {
			return desc;
		}

	}

	/**学习任务*/
	public enum StudyTask implements IEnum {

		PUBLISH_TASK(1, "发布学习任务次数"), FINISH_TASK(2, "完成学习任务次数");

		private final int key;

		private final String desc;


		private StudyTask( int key, String desc ) {
			this.key = key;
			this.desc = desc;
		}


		@Override
		public int key() {
			return key;
		}


		@Override
		public String desc() {
			return desc;
		}

	}


	// 对枚举封装下拉列表,为添加修改页面做下拉列表用
	public static List<Map<String, String>> getList( Class<? extends IEnum> clazz ) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			for ( Object obj : Arrays.asList(clazz.getEnumConstants()) ) {
				Map<String, String> map = new HashMap<String, String>();
				for ( Method method : clazz.getDeclaredMethods() ) {
					if ( method.getName().equals("key") ) {
						map.put("key", method.invoke(obj) + "");
					}
					if ( method.getName().equals("desc") ) {
						map.put("desc", method.invoke(obj) + "");
					}
				}

				list.add(map);
			}
		} catch ( Exception e ) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}


	// 对枚举封装下拉列表,为添加修改页面做下拉列表用
	public static Map<Integer, String> getEnumMap( Class<? extends IEnum> clazz ) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			Method key = clazz.getMethod("key");
			Method desc = clazz.getMethod("desc");
			for ( Object obj : Arrays.asList(clazz.getEnumConstants()) ) {
				map.put((Integer) key.invoke(obj), (String) desc.invoke(obj));
			}

		} catch ( Exception e ) {
			logger.error(e.getMessage(), e);
		}
		return map;
	}
}