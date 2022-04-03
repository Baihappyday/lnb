package com.example.login.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SharedUtil {

	private static com.example.login.util.SharedUtil mUtil;
	private static SharedPreferences mShared;
	
	public static com.example.login.util.SharedUtil getIntance(Context ctx, String fileNameOfInfo) {
		if (mUtil == null) {
			mUtil = new com.example.login.util.SharedUtil();
		}
		mShared = ctx.getSharedPreferences(fileNameOfInfo, Context.MODE_PRIVATE);
		return mUtil;
	}

	public void writeShared( ArrayList<String > a, HashMap<String ,String > hm) {
		SharedPreferences.Editor editor = mShared.edit();
		Iterator<String > i = a.iterator();
		while (i.hasNext()){
			String s = i.next();
			editor.putString(s, hm.get(s));
		}

		editor.commit(); 
	}

	public HashMap<String, String> readShared(ArrayList<String > a) {
		Iterator<String > i = a.iterator();
		HashMap<String ,String > hm = new HashMap<>();
		while (i.hasNext()){
			String s = i.next();
			hm.put(s,mShared.getString(s, "null"));
		}
		return hm;
	}

	public String readShared(String key, String defaultValue) {
		return mShared.getString(key, defaultValue);
	}
	
}
