package cn.yu2.baomihua.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.entity.KlassTeacher;
import cn.yu2.baomihua.manage.mapper.KlassMapper;
import cn.yu2.baomihua.manage.mapper.KlassTeacherMapper;
import cn.yu2.baomihua.manage.mapper.UserMapper;
import cn.yu2.baomihua.manage.service.IKlassTeacherService;

/**
 *
 * KlassTeacher 表数据服务层接口实现类
 *
 */
@Service
public class KlassTeacherServiceImpl extends BaseServiceImpl<KlassTeacherMapper, KlassTeacher>
		implements IKlassTeacherService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private KlassMapper klassMapper;
	
	@Autowired
	private KlassTeacherMapper klassTeacherMapper;

	@Override
	public List<UserDTO> getTeachTeacher( Page<UserDTO> page, Map<String, Object> paramMap ) {
		List<KlassTeacher> klassTeacherList = baseMapper.getTeachTeacher(page, paramMap);
		List<UserDTO> UserDTOList = new ArrayList<UserDTO>(klassTeacherList.size());
		for ( KlassTeacher klassTeacher : klassTeacherList ) {
			UserDTO userDTO = new UserDTO();
			Klass klass = klassMapper.selectById(klassTeacher.getKlassId());
			if ( null != klass ) {
				userDTO.setKlassName(klass.getName());
			}
			userDTO.setName(userMapper.selectById(klassTeacher.getTeacherId()).getName());
			UserDTOList.add(userDTO);
		}
		return UserDTOList;
	}

	/** 通过klassId获取教师id  */
	@Override
	public List<Long> getTeacherByKlassId(Long klassId) {
		
		KlassTeacher klassTeacher = new KlassTeacher();
		klassTeacher.setKlassId(klassId);
		EntityWrapper<KlassTeacher> entityWrapper = new EntityWrapper<KlassTeacher>(klassTeacher);
		List<KlassTeacher> klassTeachers = klassTeacherMapper.selectList(entityWrapper);
		List<Long> ids = new ArrayList<>();
		for (KlassTeacher teacher : klassTeachers) {
			if (!ids.contains(teacher.getTeacherId())) {
				ids.add(teacher.getTeacherId());
			}
		}
		return ids;
	}


}