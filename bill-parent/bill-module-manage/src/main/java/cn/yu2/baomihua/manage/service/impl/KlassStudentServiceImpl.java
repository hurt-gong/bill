package cn.yu2.baomihua.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.entity.KlassStudent;
import cn.yu2.baomihua.manage.mapper.KlassStudentMapper;
import cn.yu2.baomihua.manage.service.IKlassStudentService;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.core.service.BaseServiceImpl;

/**
 *
 * KlassStudent 表数据服务层接口实现类
 *
 */
@Service
public class KlassStudentServiceImpl extends BaseServiceImpl<KlassStudentMapper, KlassStudent> implements IKlassStudentService {

	@Override
	public int getStudentSumById(Long id) {
		return baseMapper.getStudentSumById(id);
	}

	/** 通过kalssId 查询学生 */
	public List<Long> getStudentByKlassId(Long klassId) {
		
		KlassStudent klassStudent = new KlassStudent();
		
		klassStudent.setKlassId(klassId);
		
		EntityWrapper<KlassStudent> entityWrapper = new EntityWrapper<KlassStudent>(klassStudent);
		
		List<KlassStudent> students = baseMapper.selectList(entityWrapper);
		
		List<Long> ids = new ArrayList<>();
		
		for (KlassStudent student : students) {
			ids.add(student.getStudentId());
		}
		
		return ids;
	}


}