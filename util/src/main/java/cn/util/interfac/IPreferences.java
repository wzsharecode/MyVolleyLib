package cn.util.interfac;

import java.util.Map;

/**
 * SharedPreferences 保存数据
 * 
 */
public interface IPreferences {
	
	void putBoolean(String key, boolean val);

	void putInteger(String key, int val);

	void putLong(String key, long val);

	void putFloat(String key, float val);

	void putString(String key, String val);

	void put(Map<String, ?> vals);

	boolean getBoolean(String key);

	int getInteger(String key);

	long getLong(String key);

	float getFloat(String key);

	String getString(String key);

	boolean getBoolean(String key, boolean defValue);

	int getInteger(String key, int defValue);

	long getLong(String key, long defValue);

	float getFloat(String key, float defValue);

	String getString(String key, String defValue);

	/**
	 * Returns a read only Map<String, Object> with all the key, objects of the
	 * preferences.
	 */
	Map<String, ?> get();

	boolean contains(String key);

	void clear();

	void remove(String key);

	/** Makes sure the preferences are persisted. */
	void flush();
}
