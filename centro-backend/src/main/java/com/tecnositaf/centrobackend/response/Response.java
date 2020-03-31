package com.tecnositaf.centrobackend.response;

import java.time.LocalDateTime;

public class Response {
	private int code;
	private String message;
	private LocalDateTime timestamp;
	private String path;

	public Response(int code, String message, String path) {
		this.code = code;
		this.message = message;
		this.path = path;
		this.timestamp = LocalDateTime.now();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
