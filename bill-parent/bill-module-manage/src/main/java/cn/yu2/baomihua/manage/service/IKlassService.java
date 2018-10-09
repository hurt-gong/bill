package cn.yu2.baomihua.manage.service;

import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Klass;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ISuperService;

/**
 *
 * Klass 表数据服务层接口
 *
 */
public interface IKlassService extends ISuperService<Klass> {

	public String getKlassName(Long id);

	public List<Klass> getKlassByIds(Long gradeId,Long schoolId,Long crUserId);
	
}