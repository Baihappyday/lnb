package com.example.login.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static android.content.Context.MODE_PRIVATE;

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

	public void writeShared(String key, String value) {
		SharedPreferences.Editor editor = mShared.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void writeShared(String key, boolean value) {
		SharedPreferences.Editor editor = mShared.edit();
		editor.putBoolean(key, value);
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

	public boolean readShared(String key, boolean defaultValue) {
		return mShared.getBoolean(key, defaultValue);
	}

	private static SharedPreferences getmShared() {
		return mShared;
	}


	public static void clearShared(Context activity){
		SharedUtil sp = SharedUtil.getIntance(activity,"logininfo");
		if(sp!=null){
			sp.getmShared().edit().clear().commit();
		}
		sp=SharedUtil.getIntance(activity,"healthinfo");
		if(sp!=null){
			sp.getmShared().edit().clear().commit();
		}
		sp=SharedUtil.getIntance(activity,"taskinfo");
		if(sp!=null){
			sp.getmShared().edit().clear().commit();
		}
	}
	
}
