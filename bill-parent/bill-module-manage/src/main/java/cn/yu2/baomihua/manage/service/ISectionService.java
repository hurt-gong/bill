package cn.yu2.baomihua.manage.service;

import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.manage.entity.Section;

/**
 *
 * Section 表数据服务层接口
 *
 */
public interface ISectionService extends ISuperService<Section> {

	/** 跟据id上次一二级目录 */
	public int deleteSectionById(Long id);

}
