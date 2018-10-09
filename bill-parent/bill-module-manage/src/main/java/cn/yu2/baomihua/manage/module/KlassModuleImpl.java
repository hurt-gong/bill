/**
 * KlassModuleImpl.java cn.yu2.baomihua.manage.module Copyright (c) 2016,
 * 北京微课创景教育科技有限公司版权所有.
 */

package cn.yu2.baomihua.manage.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.service.IKlassService;

/**
 * 
 * <p>
 *
 * @author   李新迎
 * @date	 2016年6月28日
 * @version  1.0.0
 */

public class KlassModuleImpl extends AbstractModule implements IKlassModule {

	@Autowired
	private IKlassService klassService;

	@Autowired
	private IKlassStudentModule klassStudentModuleImpl;


	/** 通过id获取班级的名字 */
	@Override
	public Response<String> getKlassName( Long id ) {

		return success(klassService.getKlassName(id));

	}


	/** 通过id获取班级 */
	@Override
	public Response<Klass> getKlassById( Long id ) {

		return success(klassService.selectById(id));

	}


	/** 通过entity查询班级 */
	@Override
	public Klass getKlassByEntity( Klass klass ) {
		return klassService.selectOne(klass);
	}


	@Override
	public List<Klass> getKlassByIds( Long gradeId, Long schoolId, Long crUserId ) {
		return klassService.getKlassByIds(gradeId, schoolId, crUserId);
	}


	@Override
	public Response<Klass> getKlassByStudentId( Long studentId ) {
		Long klassId = klassStudentModuleImpl.getKlassIdByStudnetId(studentId);
		if ( null != klassId ) return success(klassService.selectById(klassId));
		return success(null);
	}

}
