package cn.yu2.baomihua.manage.service;

import java.util.List;
import java.util.Map;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.manage.dto.UserDTO;
import cn.yu2.baomihua.manage.entity.KlassTeacher;

/**
 *
 * KlassTeacher 表数据服务层接口
 *
 */
public interface IKlassTeacherService extends ISuperService<KlassTeacher> {

	/**获取任课教师*/
	public List<UserDTO> getTeachTeacher( Page<UserDTO> page, Map<String, Object> paramMap );

	/**通过 查询教师   */
	public List<Long> getTeacherByKlassId(Long klassId);
}