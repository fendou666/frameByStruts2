package com.sims.mvc.model.bean;

public class Team {
	private String id;
	private String name;
	private String leaderID;
	private String classID;
	 
	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Team(String id, String name, String leaderID, String classID) {
		super();
		this.id = id;
		this.name = name;
		this.leaderID = leaderID;
		this.classID = classID;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLeaderID() {
		return leaderID;
	}
	public void setLeaderID(String leader) {
		this.leaderID = leader;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	 
}
