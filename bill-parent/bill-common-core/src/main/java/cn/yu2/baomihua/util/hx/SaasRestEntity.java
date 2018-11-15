package cn.yu2.baomihua.util.hx;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Paul.Wu on 2017/4/17.
 */
public class SaasRestEntity implements Serializable {

    private static final long serialVersionUID = 4421614833440539932L;
    private GlobalInfo globalInfo;
    private ReturnStateInfo returnStateInfo;
    
    private  Data Data;

    @JSONField(name="Data")
    public Data  getData() {
        return Data;
    }

    public void setData( Data data) {
        this.Data = data;
    }	

    public GlobalInfo getGlobalInfo() {
        return globalInfo;
    }

    public void setGlobalInfo(GlobalInfo globalInfo) {
        this.globalInfo = globalInfo;
    }

    public ReturnStateInfo getReturnStateInfo() {
        return returnStateInfo;
    }

    public void setReturnStateInfo(ReturnStateInfo returnStateInfo) {
        this.returnStateInfo = returnStateInfo;
    }
}
