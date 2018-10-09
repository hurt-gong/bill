package cn.yu2.baomihua.constant;

public class ManageConstant {

	// 区学校Id
	public static final Long DISTRIC_SCHOOLID = 0L;

	// 默认密码
	public static final String DEFULT_PASS = "123456";

	// module层错误返回信息
	public final static String SYSTEM_ERROR = "系统错误";

	// 导出excel扩展名
	public static final String EXCEL_EXTENTSION = ".xls";

	// 区人员信息导出excel名
	public static final String DISTRIC_STAFF_EXCEL = "区人员信息";

	// 学生
	public static final String SCHOOL_STUDENT_EXCEL = "学生信息";

	// 书记一级目录默认父ID
	public static final long SECTION_PID = 0l;
	// 区人员信息导出excel标题
	public static final String[] DISTRIC_STAFF_EXCEL_TITLES = new String[] { "序号", "姓名", "性别", "身份证号", "手机号" };

	// 学生
	public static final String[] SCHOOL_STUDENT_EXCEL_TITLES = new String[] { "序号", "学籍号", "姓名", "性别", "年级", "班级", "民族",
			"家长姓名", "家长联系方式" };

	// 区人员信息导出excel名
	public static final String DISTRIC_BOOK_EXCEL = "教材目录模板";

	// 区人员信息导出excel标题
	public static final String[] DISTRIC_BOOK_EXCEL_TITLES = new String[] { "学段", "学科", "教材", "一级目录", "二级目录" };

}
