package cn.yu2.baomihua.manage.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Section;
import cn.yu2.baomihua.manage.service.ISectionService;

/**
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 下午3:22:02
 */
public class SectionModuleImpl extends AbstractModule implements ISectionModule {
	@Autowired
	private ISectionService sectionService;

	@Override
	public Response<List<Section>> getSectionListModule(Long pId, Long bookId) {
		Section section = new Section();
		section.setBookId(bookId);
		section.setPid(pId);
		return success(sectionService.selectList(section));
	}

	@Override
	public Response<Boolean> deleteSectionModule(Long id) {
		int result = sectionService.deleteSectionById(id);
		if (result > 0)
			return successOfBoolean();
		return failOfBoolean();
	}

	@Override
	public Response<Boolean> updateSection(Long id, String name) {
		Section section = new Section();
		section.setId(id);
		section.setName(name);
		return success(sectionService.updateSelectiveById(section));
	}

	@Override
	public Response<Boolean> saveSection(String name, Long bookId, Long pId) {
		Section section = new Section();
		section.setName(name);
		section.setBookId(bookId);
		section.setPid(pId);
		return success(sectionService.insert(section));
	}

	@Override
	public Response<Boolean> saveSectionList(List<Section> secList) {
		return success(sectionService.insertBatch(secList));
	}

	@Override
	public List<Section> getSecSectionByPid(Long key1Id) {
		Section section = new Section();
		section.setPid(key1Id);
		List<Section> sectionList = sectionService.selectList(section);
		return sectionList;
	}

	@Override
	public List<Section> getSectionByBookId(Long bookId) {
		Section section = new Section();
		section.setBookId(bookId);
		List<Section> sectionList = sectionService.selectList(section);
		return sectionList;
	}

}
