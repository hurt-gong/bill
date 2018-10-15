package cn.yu2.baomihua.web.controller.openapi.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yu2.baomihua.openapi.entity.MsgHistory;
import cn.yu2.baomihua.openapi.module.ICompanyModule;
import cn.yu2.baomihua.util.DateUtils;

/**
 * version异步任务
 * 
 * @author lys
 * 
 */
public class MsgHistoryTask extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(MsgHistoryTask.class);

	private String msgId;
	private String requestParma;
	private String responseParma;
	private String companyName;
	private String channel;
	private int code;
	private ICompanyModule companyModule;

	public MsgHistoryTask(String channel ,String msgId, String requestParma, String responseParma, String companyName, int code,
			ICompanyModule companyModule) {
		super();
		this.msgId = msgId;
		this.requestParma = requestParma;
		this.responseParma = responseParma;
		this.companyName = companyName;
		this.code = code;
		this.companyModule = companyModule;
		this.channel = channel;
	}



	@Override
	public void run() {
		try {
			MsgHistory msgHistory = new MsgHistory();
			msgHistory.setChannel(channel);
			msgHistory.setCode(code);
			msgHistory.setCompanyName(companyName);
			msgHistory.setInsertDate(DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			msgHistory.setRequestParam(requestParma);
			msgHistory.setResponseParam(responseParma);
			msgHistory.setMsgId(msgId);
			companyModule.saveMsgHistory(msgHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ICompanyModule getCompanyModule() {
		return companyModule;
	}

	public void setCompanyModule(ICompanyModule companyModule) {
		this.companyModule = companyModule;
	}
	
	
	public String getChannel() {
		return channel;
	}



	public void setChannel(String channel) {
		this.channel = channel;
	}



	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getRequestParma() {
		return requestParma;
	}

	public void setRequestParma(String requestParma) {
		this.requestParma = requestParma;
	}

	public String getResponseParma() {
		return responseParma;
	}

	public void setResponseParma(String responseParma) {
		this.responseParma = responseParma;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}