package com.techjs.yourbookstrore.helper;

import com.techjs.yourbookstore.model.User;

public class NewMemberParticipant {
	
	private User user;
	private String otp;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
