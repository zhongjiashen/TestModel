package com.shenzhongjia.timesmoke.entity;

/**
 * 二级Item实体类
 * 
 * @author zihao
 * 
 */
public class ChildStatusEntity {
	/** 发生事件 **/
	private String completeTime;
	/** 是否已完成 **/
	private boolean isfinished;

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public boolean isIsfinished() {
		return isfinished;
	}

	public void setIsfinished(boolean isfinished) {
		this.isfinished = isfinished;
	}

}
