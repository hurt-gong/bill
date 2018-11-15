package cn.yu2.baomihua.util.hx;
import org.apache.commons.codec.binary.Base64;


/**
 * Created by Bourne.Lv on 2016/03/28.
 * <p/>
 * 第三方平台与电子发票开票企业协议工厂类
 */
public final class ProtocolFactory {
    private ProtocolFactory() {
    }


    /**
     * 编码
     *
     * @param res 字节数组
     * @return 编码后字节数组
     */
    public static byte[] encode(byte[] res) {
        try {
            final Base64 base = new Base64();
            return base.encode(res);
        } catch (Exception e) {
            System.out.println("未知异常:{}" + e);
            return new byte[0];
        }
    }
    
    public static String decode(String str){
    	 final Base64 base = new Base64();
    	 return new String(base.decode(str.getBytes()));
    }
    
    
    public static void  main(String s[]){
    	String st = "eyJOU1JTQkgiOiI5MTE0MDMwMTY2NjY2NjY2NjYiLCJMWCI6IjUyIiwiVE9LRU4iOiJleUowZVhBaU9pSktWMVFpTENKaGJHY2lPaUpJVXpJMU5pSjkuZXlKaVlpSTZJakV1TUNJc0luQjBJam9pUkZOR01EQXdNRElpTENKelltZ2lPaUk1TVRFME1ETXdNVFkyTmpZMk5qWTJOallpTENKc2VDSTZJalV5SWl3aWFXUWlPaUk0Tnpnek9UWWlmUS41MjNEOTRGNkY3OUI0RERBNTM0MDFFRkZDQ0M1MjY4MDYxM0VCRThERkQ5QUU1OTQyRjQ4MjZBRDdDM0I3QjFFIiwiWVhTQyI6IjAifQ==";
    	System.out.println(decode(st));;
    }
    
}
