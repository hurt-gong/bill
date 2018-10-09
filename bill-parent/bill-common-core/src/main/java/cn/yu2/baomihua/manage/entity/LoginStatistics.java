package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 用户登录浏览页面统计
 *
 */
@TableName(value = "TJ_LOGIN_STATISTICS")
public class LoginStatistics implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId
	private Long id;

	/** 学校Id */
	private Long schoolId;

	/** 学校名称 */
	private String schoolName;

	/** 班级Id */
	private Long gradeId;

	/** 班级id */
	private Long klassId;

	/**  班级名称 */
	private String klassName;

	/** 用户Id */
	private Long userId;

	/** 页面访问次数 */
	private Integer pageView;

	/** 用户登录次数 */
	private Integer loginCount;

	/** 用户登录ip */
	private String loginIp;

	/** 登录日期 */
	private Date crDate;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Long getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getKlassId() {
		return this.klassId;
	}

	public void setKlassId(Long klassId) {
		this.klassId = klassId;
	}

	public String getKlassName() {
		return this.klassName;
	}

	public void setKlassName(String klassName) {
		this.klassName = klassName;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getPageView() {
		return this.pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getCrDate() {
		return this.crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

}
