package cn.yu2.baomihua.util.hx;
import org.apache.commons.lang.RandomStringUtils;

/**
 * 公共方法
 * Created by Bourne.Lv on 2015/04/23.
 */
public final class CommonsUtil {
    private CommonsUtil() {
    }

    /**
     * 随机数字
     *
     * @param ws 长度
     * @return 随机字符串
     */
    public static String getRandomNum(int ws) {
        return RandomStringUtils.randomNumeric(ws);
    }
}
