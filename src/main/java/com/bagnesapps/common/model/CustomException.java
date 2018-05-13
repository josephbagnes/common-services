package com.bagnesapps.common.model;

import org.apache.commons.lang3.StringUtils;

public class CustomException extends Exception {

	public CustomException(String message) {
		super(message);
	}
	
	public boolean isUnauthorized() {
		return StringUtils.isNoneBlank(super.getMessage()) && StringUtils.equals(super.getMessage(), "UNAUTHORIZED");
	}
}
