package cn.yu2.baomihua.manage.service.impl;

import org.springframework.stereotype.Service;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.entity.Section;
import cn.yu2.baomihua.manage.mapper.SectionMapper;
import cn.yu2.baomihua.manage.service.ISectionService;

/**
 *
 * Section 表数据服务层接口实现类
 *
 */
@Service
public class SectionServiceImpl extends BaseServiceImpl<SectionMapper, Section>implements ISectionService {

	@Override
	public int deleteSectionById(Long id) {
		Section sect = new Section();
		sect.setPid(id);
		baseMapper.deleteSelective(sect);
		return baseMapper.deleteById(id);
	}
}