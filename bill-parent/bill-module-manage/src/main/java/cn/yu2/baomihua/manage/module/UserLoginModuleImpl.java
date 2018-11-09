/**
 * UserLoginModuleImpl.java cn.yu2.baomihua.manage.module Copyright (c) 2016,
 *           .
 */

package cn.yu2.baomihua.manage.module;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.constant.ManageConstant;
import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.Klass;
import cn.yu2.baomihua.manage.entity.KlassStudent;
import cn.yu2.baomihua.manage.entity.LoginStatistics;
import cn.yu2.baomihua.manage.entity.School;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.util.DateUtils;
import cn.yu2.baomihua.manage.service.IKlassService;
import cn.yu2.baomihua.manage.service.IKlassStudentService;
import cn.yu2.baomihua.manage.service.ILoginStatisticsService;
import cn.yu2.baomihua.manage.service.ISchoolService;
import cn.yu2.baomihua.manage.service.IUserLoginService;

/**
 * 
 * <p>
 *
 * @author   ShiBin
 * @date	 2016年6月1日 
 * @version  1.0.0	 
 */
public class UserLoginModuleImpl extends AbstractModule implements IUserLoginModule {

	@Autowired
	private IUserLoginService userLoginService;

	@Autowired
	private ILoginStatisticsService loginStatisticsServiceImpl;

	@Autowired
	private ISchoolService schoolService;

	@Autowired
	private IKlassStudentService KlassStudentServiceImpl;

	@Autowired
	private IKlassService klassServiceImpl;


	@Override
	public Response<User> getUser( User user ) {
		return success(userLoginService.getUser(user));

	}


	@Override
	public Response<User> getCurrentUser( long userId ) {

		return success(userLoginService.getCurrentUser(userId));

	}


	@Override
	public Response<Boolean> setPassword( Long userId, String password ) {
		User user = new User();
		user.setId(userId);
		user.setPassword(password);
		return result(userLoginService.updateUser(user));
	}


	@Override
	public Response<Boolean> setHeadUrl( Long userId, String headUrl ) {
		User user = new User();
		user.setId(userId);
		user.setHeadUrl(headUrl);
		return result(userLoginService.updateUser(user));

	}


	@Override
	public Response<Boolean> addLoginStatistics( Long userId, Long schoolId, String loginIp ) {
		LoginStatistics loginSta = new LoginStatistics();
		loginSta.setUserId(userId);
		loginSta.setCrDate(DateUtils.parseyyyyMMddDate(""));
		loginSta.setSchoolId(schoolId);
		loginSta.setLoginIp(loginIp);
		LoginStatistics loginStatistics = loginStatisticsServiceImpl.selectOne(loginSta);
		if ( null != loginStatistics ) {
			loginStatistics.setLoginCount(loginStatistics.getLoginCount() + 1);
			loginStatisticsServiceImpl.updateById(loginStatistics);
		} else {
			if ( ManageConstant.DISTRIC_SCHOOLID != schoolId ) {
				KlassStudent klassStu = new KlassStudent();
				klassStu.setStudentId(userId);
				klassStu = KlassStudentServiceImpl.selectOne(klassStu);
				if ( null != klassStu ) {
					Klass klass = klassServiceImpl.selectById(klassStu.getKlassId());
					if ( null != klass ) {
						loginSta.setGradeId(klass.getGradeId());
						loginSta.setKlassName(klass.getName());
					}
				}
			}
			School school = schoolService.getSchoolById(schoolId);
			if ( null != school ) loginSta.setSchoolName(school.getName());
			loginSta.setPageView(0);
			loginSta.setLoginCount(1);
			return success(loginStatisticsServiceImpl.insert(loginSta));
		}
		return success(false);
	}

}

