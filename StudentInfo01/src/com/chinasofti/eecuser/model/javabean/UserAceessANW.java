package com.chinasofti.eecuser.model.javabean;

public class UserAceessANW {
	
	
	private int eec_id;
	private String answer1;
	private String answer2;
	
	public UserAceessANW() {
	
	}
	public UserAceessANW(int eec_id, String answer1, String answer2) {
		this.eec_id = eec_id;
		this.answer1 = answer1;
		this.answer2 = answer2;
	}
	
	public int getEec_id() {
		return eec_id;
	}
	
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	
}
