package cn.yu2.baomihua.bastpay.bputil;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

/**
 * @Author: liujianqun
 * @Description:
 * @Date: 2017/8/8
 * @Moidfy by:
 */
public class VerifyUtil {
    /**
     * @desc
     * @author liujianqun
     * @method verifyCert
     * @param context
     * @param sign
     * @param base64Cert
     * @param charcterCode
     * @return boolean
     */
    public static boolean verifyCert(String context,String sign,String base64Cert,String charcterCode) throws GeneralSecurityException, UnsupportedEncodingException {
        X509Certificate x509Certificate = null;

        x509Certificate = CryptoUtil.base64StrToCert(base64Cert);
        byte[] sigBytes = sign.getBytes(charcterCode);
        byte[] lowSign = Base64.decode(sigBytes);
        byte[] actlVerifyData = CryptoUtil.enDecryptByRsa(lowSign, x509Certificate.getPublicKey(), Cipher.DECRYPT_MODE);
        String actlVerifyHexData = Hex.encodeHexString(actlVerifyData);
        return actlVerifyHexData.equals(context);

    }
}
