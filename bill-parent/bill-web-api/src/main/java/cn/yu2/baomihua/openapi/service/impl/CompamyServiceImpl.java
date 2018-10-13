package cn.yu2.baomihua.openapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.mapper.CompanyMapper;
import cn.yu2.baomihua.openapi.service.ICompanyService;

/**
 *
 *   表数据服务层接口实现类
 *
 */
@Service
public class CompamyServiceImpl extends BaseServiceImpl<CompanyMapper, Company>implements ICompanyService {

	@Override
	public List<Company> getCompanyList() {
		return baseMapper.getCompanyList();
	}
}