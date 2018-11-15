package cn.yu2.baomihua.util.hx;
import java.security.MessageDigest;

/**
 * Created by Bourne.Lv on 2015/04/22.
 * <p/>
 * 验证电商平台传递的用户名和密码
 */
public final class PassWordCheck {
    private static final String ALGORITHM = "MD5";
    private static final String CHARSET = "UTF-8";
    private static PassWordCheck passWordCheck = null;

    private PassWordCheck() {
    }

    public static PassWordCheck instantiation() {
        if (passWordCheck == null) {
            passWordCheck = new PassWordCheck();
        }
        return passWordCheck;
    }
    /**
     * 生成24位密码
     *
     * @param zch 注册号
     * @return 生成24位密码
     */
    public static Password passWordCreate(String zch) {
        final Password password = new Password();
        if (zch ==null || "".equals(zch)) {
            return password;
        }
        try {
            final StringBuilder bf = new StringBuilder();
            bf.append(CommonsUtil.getRandomNum(10));
            final String sjm = bf.toString();
            bf.append(zch);
            final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(bf.toString().getBytes(CHARSET));
            password.setPass(new String(ProtocolFactory.encode(md.digest()), CHARSET));
            password.setSjm(sjm);
            return password;
        } catch (Exception e) {
            System.out.println("未知异常:{}" + e);
            return password;
        }
    }
}
