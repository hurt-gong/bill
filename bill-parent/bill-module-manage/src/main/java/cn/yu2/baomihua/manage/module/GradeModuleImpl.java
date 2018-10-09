package cn.yu2.baomihua.manage.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Grade;
import cn.yu2.baomihua.manage.service.IGradeService;

/** 
 * @Description:
 * 
 * @author wanglulu
 * @version 1.0 
 * @date  2016年4月27日 下午3:18:35 
 */
public class GradeModuleImpl extends AbstractModule implements IGradeModule {
	@Autowired
	private IGradeService gradeServcie;
	
	@Override
	public Response<Page<Grade>> getGradeList(Page<Grade> page) {
		page = gradeServcie.getGradeList(page);
		
		for(Grade gra : page.getRecords()){
			
		}
		return success(page);
	}

	@Override
	public List<Grade> getGradeList() {
		List<Grade> gradeList = gradeServcie.getGradeList();
		return gradeList;
	}

	@Override
	public List<Grade> getGradeListByPhaseId(Long phaseId) {
		return gradeServcie.getGradeListByPhaseId(phaseId);
	}

	@Override
	public Grade getGradeById(Long id) {
		return gradeServcie.getGradeById(id);
	}
}
