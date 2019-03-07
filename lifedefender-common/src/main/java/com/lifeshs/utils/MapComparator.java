package com.lifeshs.utils;

import java.util.Comparator;
import java.util.Map;

public class MapComparator implements Comparator<Map<String, Object>> {

	private String fieldName;
	
	public MapComparator(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		
		int number1 = (Integer)o1.get(fieldName);
		int number2 = (Integer)o2.get(fieldName);
		
		if(number1 == number2) {
			return 0;
		}
		return number1 - number2 > 0 ? 1 : -1;
	}
	
}