package com.bagnesapps.common.model;

import java.io.Serializable;

public class RequestBodyWithKey implements Serializable {

	private String appName;
	private String appKey;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}	
	
}
