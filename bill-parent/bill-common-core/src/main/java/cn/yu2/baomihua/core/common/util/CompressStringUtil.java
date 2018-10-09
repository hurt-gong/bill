/**
 * CompressString.java cn.bjjh.core.util Copyright (c) 2015, 北京微课创景教育科技有限公司版权所有.
 */

package cn.yu2.baomihua.core.common.util;

import java.io.UnsupportedEncodingException;


public class CompressStringUtil {


	public static String decode( byte[] content ) {
		if ( content == null ) {
			return null;
		}
		try {
			String result = new String(Lz4Compress.uncompress(content), "UTF-8");
			return result;
		} catch ( UnsupportedEncodingException e ) {

		}
		return null;
	}


	public static byte[] encode( String content ) {
		if ( content == null ) {
			return null;
		}
		try {
			return Lz4Compress.compress(content.getBytes("UTF-8"));
		} catch ( UnsupportedEncodingException e ) {


		}
		return null;
	}

}
