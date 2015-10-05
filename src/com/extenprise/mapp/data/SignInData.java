package com.extenprise.mapp.data;

import java.io.Serializable;

public class SignInData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String phone;
	private String passwd;

	public String toString() {
		return "phone: " + phone + ", passwd: " + passwd;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
