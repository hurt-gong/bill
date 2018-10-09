package cn.yu2.baomihua.core.common;

import java.awt.Color;
import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.baomidou.framework.captcha.DefaultCaptcha;
import com.baomidou.framework.captcha.ICaptchaStore;
import com.baomidou.kisso.common.captcha.background.OvalNoiseBackgroundFactory;
import com.baomidou.kisso.common.captcha.color.ColorFactory;
import com.baomidou.kisso.common.captcha.color.SingleColorFactory;
import com.baomidou.kisso.common.captcha.filter.ConfigurableFilterFactory;
import com.baomidou.kisso.common.captcha.filter.library.AbstractImageOp;
import com.baomidou.kisso.common.captcha.filter.library.WobbleImageOp;
import com.baomidou.kisso.common.captcha.font.RandomFontFactory;
import com.baomidou.kisso.common.captcha.service.ConfigurableCaptchaService;
import com.baomidou.kisso.common.captcha.text.renderer.BestFitTextRenderer;
import com.baomidou.kisso.common.captcha.word.RandomWordFactory;

/**
 * <p>
 * ehcache 缓存处理验证码
 * </p>
 * @author   hubin
 * @date	 2016-06-27
 */
public class EhcacheCaptcha extends DefaultCaptcha {

	public static final String CAPTCHA_TOKEN = "ctoken";

	private static final String CAPTCHA_CACHE = "captchaCache";

	private static final EhcacheCaptcha mc = new EhcacheCaptcha();

	private EhcacheCaptcha() {
		setCaptchaService(getMyConfigurableCaptchaService());
		setCaptchaStore(new ICaptchaStore() {

			@Override
			public String get( String ticket ) {
				Object obj = EhcacheHelper.get(CAPTCHA_CACHE, ticket);
				if ( obj != null ) {
					return String.valueOf(obj);
				}
				return null;
			}


			@Override
			public boolean put( String ticket, String captcha ) {
				EhcacheHelper.put(CAPTCHA_CACHE, ticket, captcha);
				return true;
			}

		});
	}


	/**
	 * <p>
	 * 换掉验证码库， 继承  AbstractCaptcha 
	 * 实现 writeImage 方法即可
	 * </p>
	 */
	public static EhcacheCaptcha getInstance() { 
		return mc;
	}


	/**
	 * <p>
	 * 自定义图片验证码生成规则
	 * </p>
	 */
	public static ConfigurableCaptchaService getMyConfigurableCaptchaService() {
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		// 验证码宽高
		cs.setWidth(118);
		cs.setHeight(38);

		//字符颜色设置
		ColorFactory colorFactory = new SingleColorFactory(Color.blue);
		cs.setColorFactory(colorFactory);
		// 设置一个单一颜色字体
		// cs.setColorFactory(new SingleColorFactory(new Color(11, 182, 114)));
		// cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

		//设置随机4个字符串
		MyRandomWordFactory rw = new MyRandomWordFactory();
		rw.setMinLength(4);
		rw.setMaxLength(4);
		rw.setCharacters("abcdefhjkmnpqrstuvwxyz235678");
		cs.setWordFactory(rw);

		//验证码渲染设置
		BestFitTextRenderer bft = new BestFitTextRenderer();
		bft.setTopMargin(5);
		bft.setBottomMargin(5);
		bft.setRightMargin(5);
		bft.setLeftMargin(5);
		cs.setTextRenderer(bft);

		// 字符大小设置
		RandomFontFactory rf = new RandomFontFactory();
		rf.setMinSize(23);
		rf.setMaxSize(30);
		cs.setFontFactory(rf);

		// 文本渲染
		// cs.setTextRenderer(new RandomYBestFitTextRenderer());

		// 图片滤镜设置
		ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();

		// 摆动干扰
		WobbleImageOp wio = new WobbleImageOp();
		wio.setEdgeMode(AbstractImageOp.EDGE_CLAMP);
		wio.setxAmplitude(2.0);
		wio.setyAmplitude(1.0);
		filters.add(wio);

		// 曲线干扰
		// CurvesImageOp cio = new CurvesImageOp();
		// cio.setColorFactory(new SingleColorFactory(new Color(59, 162, 9)));
		// cio.setEdgeMode(AbstractImageOp.EDGE_ZERO);
		// cio.setStrokeMax(0.3f);
		// cio.setStrokeMin(0.1f);
		// filters.add(cio);

		filterFactory.setFilters(filters);
		cs.setFilterFactory(filterFactory);

		// 椭圆形干扰背景
		cs.setBackgroundFactory(new OvalNoiseBackgroundFactory(7));

		// 线形干扰背景
		//cs.setBackgroundFactory(new LineNoiseBackgroundFactory(37));
		return cs;
	}


}


/**
 * 自定义随机字符获取工厂
 * <p>
 * @author   hubin
 * @date	 2014-12-25 
 * @version  1.0.0	 
 */
class MyRandomWordFactory extends RandomWordFactory {

	/**
	 * 重载父类获取字符方法
	 * 支持随机大小写字符
	 */
	@Override
	public String getNextWord() {
		Random rnd = new Random();
		StringBuffer sb = new StringBuffer();
		int l = this.minLength + (this.maxLength > this.minLength ? rnd.nextInt(this.maxLength - this.minLength) : 0);
		for ( int i = 0 ; i < l ; i++ ) {
			int j = rnd.nextInt(this.characters.length());
			if ( rnd.nextBoolean() ) {
				sb.append(this.characters.toUpperCase().charAt(j));
			} else {
				sb.append(this.characters.charAt(j));
			}
		}
		return sb.toString();
	}
}
