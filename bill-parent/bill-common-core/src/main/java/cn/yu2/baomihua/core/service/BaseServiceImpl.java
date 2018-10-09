package cn.yu2.baomihua.core.service;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.ReflectionUtils;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.mapper.AutoMapper;


/**
 * <p>
 * Service 基类
 * </p>
 * 
 * @author hubin
 * @param <M>
 * @param <T>
 * @Date 2016-04-18
 */
public class BaseServiceImpl<M extends AutoMapper<T>, T> extends SuperServiceImpl<M, T> {

	/**
	 * 以list中对象的某个属性做键值,转换成map
	 * <p>
	 *
	 * @param list 要转换的list
	 * @param property list中对象的属性,作为键值
	 * @return 转换后的map
	*/
	public static <V> Map<Long, V> list2Map( List<V> list, String property ) {
		if ( list == null ) {
			return Collections.emptyMap();
		}
		Map<Long, V> map = new LinkedHashMap<Long, V>(list.size());
		for ( V v : list ) {
			Field field = ReflectionUtils.findField(v.getClass(), property);
			ReflectionUtils.makeAccessible(field);
			Object fieldValue = ReflectionUtils.getField(field, v);
			map.put((Long) fieldValue, v);
		}
		return map;
	}


	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> list2MapByKey( List<V> list, String property ) {
		if ( list == null ) {
			return Collections.emptyMap();
		}
		Map<K, V> map = new LinkedHashMap<K, V>(list.size());
		for ( V v : list ) {
			Field field = ReflectionUtils.findField(v.getClass(), property);
			ReflectionUtils.makeAccessible(field);
			Object fieldValue = ReflectionUtils.getField(field, v);
			map.put((K) fieldValue, v);
		}
		return map;
	}


	@SuppressWarnings("unchecked")
	public static <T> Set<T> list2Set( List<?> list, String property ) {
		if ( list == null ) {
			return Collections.emptySet();
		}
		Set<T> set = new HashSet<T>(list.size());
		for ( Object v : list ) {
			Field field = ReflectionUtils.findField(v.getClass(), property);
			ReflectionUtils.makeAccessible(field);
			Object fieldValue = ReflectionUtils.getField(field, v);
			set.add((T) fieldValue);
		}
		return set;
	}


	public static <V> Map<Long, List<V>> list2MapList( List<V> list, String property ) {
		if ( list == null ) {
			return Collections.emptyMap();
		}
		Map<Long, List<V>> map = new LinkedHashMap<Long, List<V>>(list.size());
		for ( V v : list ) {
			Field field = ReflectionUtils.findField(v.getClass(), property);
			ReflectionUtils.makeAccessible(field);
			Object fieldValue = ReflectionUtils.getField(field, v);
			List<V> lst = map.get(fieldValue);
			if ( lst == null ) {
				lst = new ArrayList<V>();
				map.put((Long) fieldValue, lst);
			}
			lst.add(v);
		}
		return map;
	}


	@SuppressWarnings("unchecked")
	public static <V> List<V> listProperty( List<?> list, String property ) {
		if ( list == null ) {
			return Collections.emptyList();
		}
		List<V> result = new ArrayList<V>();
		for ( Object v : list ) {
			Field field = ReflectionUtils.findField(v.getClass(), property);
			ReflectionUtils.makeAccessible(field);
			Object fieldValue = ReflectionUtils.getField(field, v);
			result.add((V) fieldValue);
		}
		return removeDumplicate(result);
		//		return result;
	}


	/**
	 * 去除List中重复元素
	 * <p>
	 *
	 * @param list
	 * @return
	 */
	public static <V> List<V> removeDumplicate( List<V> list ) {
		HashSet<V> set = new HashSet<V>(list);
		list.clear();
		list.addAll(set);
		return list;
	}
}
