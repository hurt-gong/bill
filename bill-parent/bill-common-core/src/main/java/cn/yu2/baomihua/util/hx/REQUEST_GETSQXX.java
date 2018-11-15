package cn.yu2.baomihua.util.hx;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Paul.Wu on 2018-06-29.
 */
public class REQUEST_GETSQXX {

    private String NSRSBH;
    private String LX;

    @JSONField(name="NSRSBH")
    public String getNSRSBH() {
        return NSRSBH;
    }

    public void setNSRSBH(String NSRSBH) {
        this.NSRSBH = NSRSBH;
    }
    @JSONField(name="LX")
    public String getLX() {
        return LX;
    }

    public void setLX(String LX) {
        this.LX = LX;
    }
}
