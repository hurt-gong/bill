package cn.yu2.baomihua.openapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.openapi.entity.CompanyTickets;
import cn.yu2.baomihua.openapi.mapper.CompanyTicketsMapper;
import cn.yu2.baomihua.openapi.service.ICompanyTicketService;
import cn.yu2.baomihua.util.DateUtils;

/**
 *
 * 表数据服务层接口实现类
 *
 */
@Service
public class CompamyTicketsServiceImpl extends BaseServiceImpl<CompanyTicketsMapper, CompanyTickets>
		implements ICompanyTicketService {

	/**
	 * 根据企业信息查询结果
	 * <p>Title: search</p>  
	 * <p>Description: </p>  
	 * @param company
	 * @return
	 */
	public Map<String, Object> search(String companyInfo) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 增值税开票信息

		String[] month = new String[] { "201610", "201611", "201612", "201701", "201702", "201703", "201704", "201705",
				"201706", "201707", "201708", "201709", "201710", "201711", "201712", "201801", "201802", "201803",
				"201804", "201805", "201806", "201807", "201808", "2017809" };
		List<Map<String, Object>> ticketInformationList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < month.length; i++) {
			Map<String, Object> ticketInformation_everyDate = new HashMap<String, Object>();
			ticketInformation_everyDate.put("ticketInformationDate", month[i]);
			// 开票销售额
			ticketInformation_everyDate.put("salesVolume", "120000.00");
			// 开票销售额同比
			ticketInformation_everyDate.put("salesYearOnYear", "17.9%");
			// 开票销售额环比
			ticketInformation_everyDate.put("salesLink", "17.9%");
			// 废票金额
			ticketInformation_everyDate.put("discardTicketSales", "0");
			// 废票金额
			ticketInformation_everyDate.put("percentDiscardTicketSales", "0.0%");
			// 开票数量
			ticketInformation_everyDate.put("countTicket", "100000");
			// 红废票数量
			ticketInformation_everyDate.put("countDiscardTicket", "0");
			// 红废票数量占比
			ticketInformation_everyDate.put("percentDiscardTicket", "0.0");
			ticketInformationList.add(ticketInformation_everyDate);
			// ticketInformation.put(month[i], ticketInformation_everyDate);
		}

		String ticketRecord = "12天";
		// 下游客户概况
		List<Map<String, Object>> customerList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < month.length; i++) {
			Map<String, Object> customer_everyDate = new HashMap<String, Object>();
			customer_everyDate.put("customerDate", month[i]);
			// 全部开票客户数（户）
			customer_everyDate.put("countCustomer", "10000");
			// 主要客户（前5）销售额（万元）
			customer_everyDate.put("customerSalesVolume", "10000");
			// 前5大客户销售额占比(%)
			customer_everyDate.put("percentCustomerSales", "10%");
			// 前5大客户开票次数（次）
			customer_everyDate.put("countCustomerTicket", "10000");
			// 前5大客户开票次数占比(%)
			customer_everyDate.put("percentCustomerTicket", "10%");
			customerList.add(customer_everyDate);
			// customer.put(month[i], customer_everyDate);
		}
		resultMap.put("ticketInformation", ticketInformationList);
		resultMap.put("ticketRecord", ticketRecord);
		resultMap.put("customer", customerList);

		return resultMap;

	}
	
	/**
	 * 保存数据
	 * <p>Title: saveCompanyTickets</p>  
	 * <p>Description: </p>  
	 * @param companyTickets
	 */
	public void saveCompanyTickets(CompanyTickets companyTickets){
		baseMapper.insert(companyTickets);
	}
	
	/**
	 * 保存推过来的数据
	 * <p>Title: saveCompanyTickets</p>  
	 * <p>Description: </p>  
	 * @param companyTickets
	 */
	public void savePushData(JSONArray jsonArray,String msgId){
		if(jsonArray !=null && jsonArray.size()>0){
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				CompanyTickets companyTickets = jsonObject.toJavaObject(CompanyTickets.class);
				companyTickets.setInsertData(DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
				companyTickets.setMsgId(msgId);
				saveCompanyTickets(companyTickets);
			}
		}
	}
}