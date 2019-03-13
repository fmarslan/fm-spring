package com.fmarslan.base.common.configuration;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {

	public static final String DEFAULT_PAGE_SIZE = "configuration.pageSize";

	private static HashMap<String, Object> instance = new HashMap<String, Object>();

	public static void init(Map<String, Object> params) {
		instance.putAll(params);
	}

	private AppConfig() {

	}

	@SuppressWarnings({ "unchecked" })
	public static <T> T get(String key) {
		return (T) instance.get(key);
	}

	public static <T> T get(String key, T defaultValue) {
		if (instance.containsKey(key))
			return get(key);
		else
			return defaultValue;
	}

	public static String getAsString(String key) {
		return get(key);
	}

	public static Long getAsLong(String key) {
		return get(key);
	}

	public static Integer getAsInteger(String key) {
		return get(key);
	}

	public static Double getAsDouble(String key) {
		return get(key);
	}

	public static String[] getAsStringArray(String key) {
		return get(key);
	}

	public static Long[] getAsLongArray(String key) {
		return get(key);
	}

	public static Integer[] getAsIntegerArray(String key) {
		return get(key);
	}

	public static Double[] getAsDoubleArray(String key) {
		return get(key);
	}

	public static int getPageSize() {
		return get(DEFAULT_PAGE_SIZE, 25);
	}

}
