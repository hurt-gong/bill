package cn.yu2.baomihua.openapi.module;

import java.util.List;

import cn.yu2.baomihua.manage.entity.BookTag;
import cn.yu2.baomihua.openapi.entity.Company;

/**
 * @author uskytec
 *
 */
public interface ICompanyModule {
	/**
	 * 
	 * @return
	 */

	public List<Company> getCompanyList();

}
