package cn.yu2.baomihua.util;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
/**  
* <p>Title: AESEncryptUtil</p>  
* <p>Description: AES加密解密</p>  
* @author hurt-gong  
* @date 2018年10月10日  
*/  
public class AESEncryptUtil {
    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }
    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
    	 
        return new String(Base64.encodeBase64(bytes));
    }
    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code.getBytes());
    }
    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
    	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }
    public static byte[] aesEncryptToBytes(String content, byte[] encryptKey) throws Exception {
    	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey);
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }
    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }
    public static String aesEncrypt(String content, byte[] encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }
    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
    	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey.getBytes());
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes,"utf-8");
    }
    public static String aesDecryptByBytes(byte[] encryptBytes, byte[] decryptKey) throws Exception {
    	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey);
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes,"utf-8");
    }
    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }
    public static String aesDecrypt(String encryptStr, byte[] decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }
    public static byte[] getAESKey(String encodingAESKey){
        byte[] array = Base64.decodeBase64((encodingAESKey+"=").getBytes());
        return array;
    }
    public static void main(String[] args) throws Exception {
        String encodingAESKey = "bastpay";
        String content = "11010101";
        String encrypt =  aesEncrypt(content,getAESKey(encodingAESKey));
        System.out.println("encrypt string：" + encrypt);
        String decrypt = aesDecrypt(encrypt, getAESKey(encodingAESKey));
        System.out.println("decrypt string：" + decrypt);
    }    
}