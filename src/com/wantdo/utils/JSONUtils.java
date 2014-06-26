package com.wantdo.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtils {
	
	public static String getCurrentState(String jsonString){
		JSONObject jsonObject=JSONObject.fromObject(jsonString);
		JSONArray jsonArray=jsonObject.getJSONArray("data");
		JSONObject data=jsonArray.getJSONObject(0);
		return data.getString("time")+" "+data.getString("context");
	}
	
	public static String getCol(String jsonString,String col){
		JSONObject jsonObject=JSONObject.fromObject(jsonString);
		return jsonObject.getString(col);
	}

}
