package com.chinasofti.eecuser.model.javabean;

import java.util.Date;

public class MsgListNeed {
	
	
	


	private int msgId;
	private int eecId;
	private String img;
	private String name;
	private String title;
	private String msgContent;
	private String msgTime;
	
	public int getMsgId() {
		return msgId;
	}
	public int getEecId() {
		return eecId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	public String getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}
	
	public MsgListNeed() {
		
	}
	public MsgListNeed(int msgId, int eecId, String name, String img,
			String title, String msgContent, String msgTime) {
		this.msgId = msgId;
		this.eecId = eecId;
		this.name = name;
		this.img = img;
		this.title = title;
		this.msgContent = msgContent;
		this.msgTime = msgTime;
	}
	
	
	@Override
	public String toString() {
		return "MsgListNeed [msgId=" + msgId + ", eecId=" + eecId + ", name="
				+ name + ", img=" + img + ", title=" + title + ", msgContent="
				+ msgContent + ", msgTime=" + msgTime + "]";
	}
	
}
