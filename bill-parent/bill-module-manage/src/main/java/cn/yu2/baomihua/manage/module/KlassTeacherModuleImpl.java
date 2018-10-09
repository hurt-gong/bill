package cn.yu2.baomihua.manage.module;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.KlassTeacher;
import cn.yu2.baomihua.manage.service.IKlassTeacherService;

public class KlassTeacherModuleImpl extends AbstractModule implements IKlassTeacherModule{

	@Autowired
	private IKlassTeacherService klassTeacherService;
	
	/** 通过klassId查询教师  */
	@Override
	public Response<List<Long>> getTeacherByKlassId(Long klassId) {
		
		List<Long> ids = klassTeacherService.getTeacherByKlassId(klassId);
		
		return success(ids);
	}
	
	/** 通过teacherId查询教师教的klassIdList  */
	@Override
	public Response<List<Long>> getklassIdListByUserId(Long userId) {
		
		KlassTeacher klassTeacher = new KlassTeacher();
		klassTeacher.setTeacherId(userId);
		List<KlassTeacher> klassTeachers = klassTeacherService.selectList(klassTeacher);
		Set<Long> idsSet = new HashSet<Long>();
		for (KlassTeacher teacher : klassTeachers) {
			idsSet.add(teacher.getKlassId());
		}
		List<Long> ids = new ArrayList<>(); 
		for (Long id : idsSet) {
			ids.add(id);
		}
		return success(ids);
	}

	@Override
	public KlassTeacher getKlassTeacherByEntity(KlassTeacher klassTeacher) {
		// TODO Auto-generated method stub
		return klassTeacherService.selectList(klassTeacher).get(0);
	}
	
	@Override
	public KlassTeacher getKlassTeacherByTeacherId(Long teacherId,Long klassId) {
		KlassTeacher klassTeacher = new KlassTeacher();
		klassTeacher.setTeacherId(teacherId);
		klassTeacher.setKlassId(klassId);
		List<KlassTeacher> selectList = klassTeacherService.selectList(klassTeacher);
		for (KlassTeacher klassTeacher2 : selectList) {
			if (klassTeacher2.getSubjectId()!=null) {
				return klassTeacher2;
			}
		}
		return selectList.get(0);
	}
	
}
