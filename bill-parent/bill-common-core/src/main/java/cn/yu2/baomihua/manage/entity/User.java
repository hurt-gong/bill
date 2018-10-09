package cn.yu2.baomihua.manage.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 用户表
 *
 */
@TableName(value = "GL_USER")
public class User implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId
	private Long id;

	/** 姓名 */
	private String name;

	/** 拼音 */
	private String spell;

	/** 登录名 */
	private String loginName;

	/** 密码 */
	private String password;

	/** 性别 */
	private Integer gender;

	/** 民族 */
	private String nation;

	/** 学籍号 */
	private String studentCode;

	/** 身份证号 */
	private String idCardNo;

	/** 手机号 */
	private String mobile;

	/** 头像 */
	private String headUrl;

	/** 出生年月 */
	private Date birthday;

	/** 用户类型,1:学生,2:教师,3:家长 */
	private Integer type;

	/** 用户状态,1:在岗,在校,2:离岗,离校 */
	private Integer status;

	/** 所属学校，区id:0 */
	private Long schoolId;

	/** 职位 */
	private Long positionId;

	/** 创建时间 */
	private Date crTime;

	/** 创建人 */
	private Long crUserId;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpell() {
		return this.spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getStudentCode() {
		return this.studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getIdCardNo() {
		return this.idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadUrl() {
		return this.headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getPositionId() {
		return this.positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Date getCrTime() {
		return this.crTime;
	}

	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}

	public Long getCrUserId() {
		return this.crUserId;
	}

	public void setCrUserId(Long crUserId) {
		this.crUserId = crUserId;
	}

}
