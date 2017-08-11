package com.sims.mvc.tools;

import java.util.ArrayList;
import java.util.List;

public class ErrorLog {
	private static List<String> list = new ArrayList<String>();
	
	public static void addErrorInfo(String s){
		list.add(s);
	}
}
