package cn.yu2.baomihua.manage.module;

import java.util.List;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Section;

/**
 * @Description:教材目录module
 * 
 * @author wanglulu
 * @version 1.0
 * @date 2016年4月27日 下午3:10:00
 */
public interface ISectionModule {
	/**
	 * 获取教材列表
	 */
	public Response<List<Section>> getSectionListModule(Long pId, Long bookId);

	/**
	 * 添加教材
	 */
	public Response<Boolean> saveSection(String SectionName, Long bookId, Long pId);

	/**
	 * 删除教材
	 */
	public Response<Boolean> deleteSectionModule(Long id);

	Response<Boolean> updateSection(Long id, String name);

	public Response<Boolean> saveSectionList(List<Section> secList);

	public List<Section> getSecSectionByPid(Long key1Id);

	public List<Section> getSectionByBookId(Long bookId);
}
