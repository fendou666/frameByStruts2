package com.chinasofti.eecuser.model.javabean;

import java.util.Date;

public class MsgList {
	

	private int msgId;
	private int eecId;
	private String title;
	private String msgContent;
	private Date msgTime;
	
	public MsgList() {
	}
	public MsgList(int msgId, int eecId, String title, String msgContent,
			Date msgTime) {
		this.msgId = msgId;
		this.eecId = eecId;
		this.title = title;
		this.msgContent = msgContent;
		this.msgTime = msgTime;
	}
	
	public int getMsgId() {
		return msgId;
	}
	public int getEecId() {
		return eecId;
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
	public Date getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	
	
	@Override
	public String toString() {
		return "MsgList [msgId=" + msgId + ", eecId=" + eecId + ", title="
				+ title + ", msgContent=" + msgContent + ", msgTime=" + msgTime
				+ "]";
	}
	
	
}
