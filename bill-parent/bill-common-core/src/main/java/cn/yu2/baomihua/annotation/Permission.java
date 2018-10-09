package cn.yu2.baomihua.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @Description:
 * 权限注解
 * @author wanglulu
 * @version 1.0 
 * @date  2016年11月9日 下午2:30:26 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {

	String value();
}
