package com.android.beijingwisdom.utils;

import android.content.SharedPreferences;

public class CacheUtils {
	/**
	 * ���û���
	 * @param url ��Ϊkey
	 * @param json ��Ϊvalue ��url һһ��Ӧ
	 */
	public static void setCache(SharedPreferences sp,String url,String json){
		SPUtil.setString(sp, url, json);
	}
	/**
	 * ��ȡ����
	 * @param url
	 * @param sp
	 * @return
	 */
	public static String getCache(String url,SharedPreferences sp){
		return SPUtil.getString(sp, url, null);
	}
	
}
