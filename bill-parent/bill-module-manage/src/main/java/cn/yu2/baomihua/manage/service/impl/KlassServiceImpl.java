package cn.yu2.baomihua.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.mapper.KlassMapper;
import cn.yu2.baomihua.manage.service.IKlassService;
import cn.yu2.baomihua.core.service.BaseServiceImpl;

/**
 *
 * Klass 表数据服务层接口实现类
 *
 */
@Service
public class KlassServiceImpl extends BaseServiceImpl<KlassMapper, Klass> implements IKlassService {

	@Autowired
	private KlassMapper klassMapper;
	
	@Override
	public String getKlassName(Long id) {
		Klass klass = baseMapper.getKlassName(id);
		if(klass!=null){
			return klass.getName();
		}else{
			return null;
		}
		
	}

	@Override
	public List<Klass> getKlassByIds(Long gradeId, Long schoolId, Long crUserId) {
		List<Klass> list = klassMapper.getKlassByIds(gradeId, schoolId, crUserId);
		return list;
	}

}