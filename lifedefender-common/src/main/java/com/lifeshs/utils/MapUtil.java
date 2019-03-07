package com.lifeshs.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtil {

	public static <T> Map<String, T> sort(Class<T> clazz, Map<String, T> map) {

		/**
		 * 复杂度： 2n+?
		 */
		Map<String, T> returnMap = new LinkedHashMap<>();

		List<String> list = new ArrayList<>();
		for (String key : map.keySet()) {
			list.add(key);
		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);
			returnMap.put(key, map.get(key));
		}

		return returnMap;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
