package com.models;


/**
 * @author Jay Patel
 */
public enum BooleanOptions {

	YES(1, "1. Yes."), NO(2, "2. No.");

	private Integer option;
	private String message;

	BooleanOptions(Integer option, String message) {
		this.option = option;
		this.message = message;
	}

	public Integer getOption() {
		return this.option;
	}

	public String getMessage() {
		return this.message;
	}
}
