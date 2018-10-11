package cn.yu2.baomihua.api.test;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.ConfigGenerator;

/**
 * <p>
 * 自动生成数据库表 entity、mapper、xml 映射辅助类
 * </p>
 * 
 * @author hubin
 * @Date 2016-03-15
 */
public class AutoGeneratorHelper {

	/**
	 * 
	 * 测试 run 执行
	 * 
	 * <p>
	 * 配置方法查看 {@link ConfigGenerator}
	 * </p>
	 * 
	 */
	public static void main(String[] args) {
		ConfigGenerator cg = new ConfigGenerator();
		cg.setEntityPackage("cn.yu2.baomihua.manage.entity");// 实体包路径
		cg.setMapperPackage("cn.yu2.baomihua.manage.mapper");// 映射文件路径
		cg.setServicePackage("cn.yu2.baomihua.manage.service");
		cg.setSuperServiceImpl("cn.yu2.baomihua.core.service.BaseServiceImpl");
		cg.setSaveDir("D:/hdedu/manage");// 生成文件保存位置

		/* 数据库相关配置 */
		cg.setDbDriverName("com.mysql.jdbc.Driver");
		cg.setDbUser("yunxiaoyuan");
		cg.setDbPassword("vko999999");
		cg.setDbUrl("jdbc:mysql://192.168.1.251:3306/hdedu_manage?characterEncoding=utf8");

		/*
		 * 表主键 ID 生成类型, 自增该设置无效。 <p> IdType.AUTO 数据库ID自增 IdType.ID_WORKER
		 * 全局唯一ID，内容为空自动填充（默认配置） IdType.INPUT 用户输入ID </p>
		 */
		cg.setIdType(IdType.ID_WORKER);

		/*
		 * 表是否包括前缀 <p> 例如 mp_user 生成实体类 false 为 MpUser , true 为 User </p>
		 */
		cg.setDbPrefix(true);
		AutoGenerator.run(cg);
	}

}
