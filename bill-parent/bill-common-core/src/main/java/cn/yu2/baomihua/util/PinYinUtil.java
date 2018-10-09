package cn.yu2.baomihua.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/** 
 *  
 * 
 * @author wanglulu
 * @version 1.0 
 * @date 2015年12月1日 上午11:16:23 
 */
public class PinYinUtil {
	
	private final static String PRE_S = "xs_";
	private final static String PRE_P = "jz_";
	private final static String SUFFIX = "";
	
	/**
	 * 将汉字转换为全拼
	 * <p>
	 *
	 * @param src
	 * @return
	 */
	public static String getPingYin(String src) {

		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches(
						"[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else {
					//不是汉字不处理
					t4 += java.lang.Character.toString(t1[i]);
				}
			}

			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	/**
	 * 返回中文的首字母
	 * <p>
	 *
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				//不是汉字不处理
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * 将字符串转移为ASCII码
	 * <p>
	 *
	 * @param cnStr
	 * @return
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}
	
	public static String getLoginName(String name, Integer userType, String subject) {
		name = proccess(name);
		StringBuffer sb = new StringBuffer();
		String pre = null;
		String suffix = SUFFIX;
		if(1 == userType) {
			pre = PRE_S;
		} else if(3 == userType){
			pre = PRE_P;
		} else {
			pre = getPinYinHeadChar(subject)+"_";
		}
		sb.append(pre);
		int len = name.length();
		if(name.length()>2) {
			String preName= name.substring(0,len-2);
			String suffixName = name.substring(len-2, len);
			sb.append(getPinYinHeadChar(preName)).append(getPingYin(suffixName));
		} else {
			suffix = getPingYin(name);
			sb.append(suffix);
		}
		return sb.toString();
	}
	
	private static String proccess(String name) {
		name = name.replace(" ", "").replace("·", "");
		return name;
	}
	public static void main(String[] args) {
		System.out.println(getLoginName("周杰伦", 2, "语文"));
	}
}
