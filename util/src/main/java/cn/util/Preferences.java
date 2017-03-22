package cn.util;

import java.util.Map;
import java.util.Map.Entry;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import cn.util.interfac.IPreferences;

/**
 * SharedPreferences 保存数据
 *
 */
public class Preferences implements IPreferences {

	SharedPreferences sharedPrefs;
	Editor editor;

	public Preferences(SharedPreferences preferences) {
		this.sharedPrefs = preferences;
	}

	@Override
	public void putBoolean(String key, boolean val) {
		edit();
		editor.putBoolean(key, val);
	}

	@Override
	public void putInteger(String key, int val) {
		edit();
		editor.putInt(key, val);
	}

	@Override
	public void putLong(String key, long val) {
		edit();
		editor.putLong(key, val);
	}

	@Override
	public void putFloat(String key, float val) {
		edit();
		editor.putFloat(key, val);
	}

	@Override
	public void putString(String key, String val) {
		edit();
		editor.putString(key, val);
	}

	@Override
	public void put(Map<String, ?> vals) {
		edit();
		for (Entry<String, ?> val : vals.entrySet()) {
			if (val.getValue() instanceof Boolean)
				putBoolean(val.getKey(), (Boolean) val.getValue());
			if (val.getValue() instanceof Integer)
				putInteger(val.getKey(), (Integer) val.getValue());
			if (val.getValue() instanceof Long)
				putLong(val.getKey(), (Long) val.getValue());
			if (val.getValue() instanceof String)
				putString(val.getKey(), (String) val.getValue());
			if (val.getValue() instanceof Float)
				putFloat(val.getKey(), (Float) val.getValue());
		}
	}

	@Override
	public boolean getBoolean(String key) {
		return sharedPrefs.getBoolean(key, false);
	}

	@Override
	public int getInteger(String key) {
		return sharedPrefs.getInt(key, 0);
	}

	@Override
	public long getLong(String key) {
		return sharedPrefs.getLong(key, 0);
	}

	@Override
	public float getFloat(String key) {
		return sharedPrefs.getFloat(key, 0);
	}

	@Override
	public String getString(String key) {
		return sharedPrefs.getString(key, "");
	}

	@Override
	public boolean getBoolean(String key, boolean defValue) {
		return sharedPrefs.getBoolean(key, defValue);
	}

	@Override
	public int getInteger(String key, int defValue) {
		return sharedPrefs.getInt(key, defValue);
	}

	@Override
	public long getLong(String key, long defValue) {
		return sharedPrefs.getLong(key, defValue);
	}

	@Override
	public float getFloat(String key, float defValue) {
		return sharedPrefs.getFloat(key, defValue);
	}

	@Override
	public String getString(String key, String defValue) {
		return sharedPrefs.getString(key, defValue);
	}

	@Override
	public Map<String, ?> get() {
		return sharedPrefs.getAll();
	}

	@Override
	public boolean contains(String key) {
		return sharedPrefs.contains(key);
	}

	@Override
	public void clear() {
		edit(); 
		editor.clear();
	}

	@Override
	public void flush() {
		if (editor != null) {
			editor.commit();
			editor = null;
		}
	}

	@Override
	public void remove(String key) {
		edit();
		editor.remove(key);
	}

	@SuppressLint("CommitPrefEdits")
	private void edit() {
		if (editor == null) {
			editor = sharedPrefs.edit();
		}
	}
}
