package com.techjs.yourbookstrore.ui;

public class AlertMessage {
	private String message;
	private AlertType alertType;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AlertType getAlertType() {
		return alertType;
	}
	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}
}
