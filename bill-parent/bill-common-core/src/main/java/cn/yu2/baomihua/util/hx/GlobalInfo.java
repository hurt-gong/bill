package cn.yu2.baomihua.util.hx;

/**
 * Created by Paul.wu on 2016/04/15.
 * <p/>
 *
 * 全局信息
 */
public final class GlobalInfo {

    //应用标识（ZZSDZFP）
    private String appId;

    //接口版本
    private String version;

    //接口编码
    private String interfaceCode;

    //商户的电商平台编码
    private String userName;

    //10位随机数+Base64(MD5（10位随机数+注册码）)
    private String passWord;

    //纳税人识别号
    private String taxpayerId;

    //数据交换请求发出时间（业务请求当前时间）
    private String requestTime;

    //数据交换流水号
    private String dataExchangeId;

    //"请求方代码（由51平台提供)"
    private String requestCode;

    //"纳税人授权码（由51平台提供）"
    private String authorizationCode;

    //"终端类型标识(0:B/S请求来源;1:C/S请求来源)",
    private String terminalCode;
    
    private String responseCode;
    
    private String interfaceMode;
    
    public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getInterfaceMode() {
		return interfaceMode;
	}

	public void setInterfaceMode(String interfaceMode) {
		this.interfaceMode = interfaceMode;
	}

	public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }



    public String getDataExchangeId() {
        return dataExchangeId;
    }

    public void setDataExchangeId(String dataExchangeId) {
        this.dataExchangeId = dataExchangeId;
    }

    public String getTaxpayerId() {
        return taxpayerId;
    }

    public void setTaxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
