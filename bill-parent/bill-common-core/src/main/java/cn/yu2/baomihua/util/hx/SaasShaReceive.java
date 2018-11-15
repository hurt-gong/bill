package cn.yu2.baomihua.util.hx;

/**
 * Created by Paul.Wu on 2017/8/22.
 */
public class SaasShaReceive {
    private String datagram; //外层报文
    private String signtype; //
    private String signature; //签名值

 
 
	public String getDatagram() {
		return datagram;
	}

	public void setDatagram(String datagram) {
		this.datagram = datagram;
	}

	public String getSigntype() {
        return signtype;
    }

    public void setSigntype(String signtype) {
        this.signtype = signtype;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
