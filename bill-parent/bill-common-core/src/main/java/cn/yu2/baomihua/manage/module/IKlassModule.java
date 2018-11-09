/**
 * IKlassModule.java cn.yu2.baomihua.manage.module Copyright (c) 2016,
 *           .
 */

package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Klass;

/**
 * 
 * <p>
 *
 * @author   李新迎
 * @date	 2016年6月28日
 * @version  1.0.0
 */

public interface IKlassModule {

	/** 通过id获取班级的名字 */
	public Response<String> getKlassName( Long id );


	/** 通过id获取班级 */
	public Response<Klass> getKlassById( Long id );


	/** 通过entity查询班级 */
	public Klass getKlassByEntity( Klass klass );


	/** 通过年级、学校、创建人查询班级 */
	public List<Klass> getKlassByIds( Long gradeId, Long schoolId, Long crUserId );


	/** 通过学生ID查询班级 */
	public Response<Klass> getKlassByStudentId( Long studentId );
}
