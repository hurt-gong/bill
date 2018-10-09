package cn.yu2.baomihua.manage.module;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Grade;

/** 
 * @Description:年级module
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:04:42 
 */
public interface IGradeModule {
	
	public Response<Page<Grade>> getGradeList(Page<Grade> page);
	
	public List<Grade> getGradeList();
	
	public List<Grade> getGradeListByPhaseId(Long phaseId);
	
	public Grade getGradeById(Long id);
	
}
