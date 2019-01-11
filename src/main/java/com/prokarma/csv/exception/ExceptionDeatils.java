package com.prokarma.csv.exception;

import java.util.Date;

public class ExceptionDeatils {
	private Date timestamp;
	private String message;
	private String details;

	public ExceptionDeatils(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
}
