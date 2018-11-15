package cn.yu2.baomihua.openapi.module;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import cn.yu2.baomihua.manage.entity.BookTag;
import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.entity.MsgHistory;
import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyDetil;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;

/**
 * @author uskytec
 *
 */
public interface IHxapiModule {
	public  Map<String, Object> getToken(String userName, String password, String requestCode, String taxpayerId,
			String authorizationCode );
	}
