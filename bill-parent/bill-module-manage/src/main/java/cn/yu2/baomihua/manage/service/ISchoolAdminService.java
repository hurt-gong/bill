package cn.yu2.baomihua.manage.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.manage.dto.SchoolAdminDTO;
import cn.yu2.baomihua.manage.entity.SchoolAdmin;

/**
 *
 * SchoolAdmin 表数据服务层接口
 *
 */
public interface ISchoolAdminService extends ISuperService<SchoolAdmin> {

	public List<SchoolAdminDTO> getAdmins(Long schoolId);

	@Override
	boolean insertBatch(List<SchoolAdmin> entityList);

	@Override
	boolean deleteById(Long id);

	@Override
	boolean deleteSelective(SchoolAdmin entity);
	
	
}