package cn.yu2.baomihua.manage.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.KlassStudent;

/**
 *
 * KlassStudent 表数据服务层接口
 *
 */
public interface IKlassStudentService extends ISuperService<KlassStudent> {


	
	public int getStudentSumById( Long id);
	
	/** 通过kalssId 查询学生 */
	public List<Long> getStudentByKlassId(Long klassId);
}