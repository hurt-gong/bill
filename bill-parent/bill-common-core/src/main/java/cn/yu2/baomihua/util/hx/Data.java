package cn.yu2.baomihua.util.hx;


/**
 * Created by Paul.wu on 2016/5/3.
 * 数据交互节点(用于JSON)
 */
public class Data {

    private String content;

    private DataDescription dataDescription;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public DataDescription getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(DataDescription dataDescription) {
        this.dataDescription = dataDescription;
    }
}
