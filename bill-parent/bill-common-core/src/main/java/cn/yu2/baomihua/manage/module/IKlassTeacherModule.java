package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.KlassTeacher;

/**
 * 
 * @author jia
 *
 */
public interface IKlassTeacherModule {
	
	/**  通过班级id查询  教师  */
	public Response<List<Long>> getTeacherByKlassId(Long klassId);
	
	/** 通过teacherId查询教师教的klassIdList  */
	public Response<List<Long>> getklassIdListByUserId(Long userId);
	
	public KlassTeacher getKlassTeacherByEntity(KlassTeacher klassTeacher);
	
	public KlassTeacher getKlassTeacherByTeacherId(Long teacherId,Long klassId);
}
