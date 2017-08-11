package com.sims.mvc.tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonMesg {
	private String mesg = null;

	public JsonMesg() {}

	public JsonMesg(String mesg) {
		super();
		this.mesg = mesg;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	
	public static String getJsonArray(Object obj){
		JSONArray fromObject = JSONArray.fromObject(obj);
		return fromObject.toString();
	}
	public static String getJsonObject(Object obj){
		JSONObject fromObject = JSONObject.fromObject(obj);
		return fromObject.toString();
	}
	
	
}
