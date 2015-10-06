package com.extenprise.mapp.service.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.extenprise.mapp.data.SignInData;

public class ServiceProvider implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idServiceProvider;
	private SignInData signInData;
	private String fName;
	private String lName;
	private String emailId;
	private String gender;
	private String qualification;
	private String regNo;
	private int subscribed;
	private Date subsDate;
	private ArrayList<ServProvHasServPt> services;
	private ArrayList<ServiceProvider> links;

	public ServiceProvider() {
		signInData = new SignInData();
		services = new ArrayList<ServProvHasServPt>();
		links = new ArrayList<ServiceProvider>();
	}

	public String toString() {
		String value = "id: " + idServiceProvider + ",\n"
				+ "signIn: {" + signInData.toString() + "},\n" + "fName: " + fName
				+ ", lName: " + lName + ",\n" + "emailId: " + emailId
				+ ", gender: " + gender + ",\n" + "qualification: "
				+ qualification + ", regNo: " + regNo + ",\n" + "subscribed: "
				+ subscribed + ", subsDate: " + subsDate + ",\n"
				+ "services: {";
		for (ServProvHasServPt service : services) {
			value += service.toString() + ",\n";
		}
		value += "},\nService Provider: {";
		for(ServiceProvider sp : links) {
			value += sp.toString() + ",\n";
		}
		value += "}";
		return value;
	}

	public int getIdServiceProvider() {
		return idServiceProvider;
	}

	public void setIdServiceProvider(int idServiceProvider) {
		this.idServiceProvider = idServiceProvider;
	}

	public SignInData getSignInData() {
		return signInData;
	}

	public void setSignInData(SignInData signInData) {
		this.signInData = signInData;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public int isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(int subscribed) {
		this.subscribed = subscribed;
	}

	public Date getSubsDate() {
		return subsDate;
	}

	public void setSubsDate(Date subsDate) {
		this.subsDate = subsDate;
	}

	public ArrayList<ServProvHasServPt> getServices() {
		return services;
	}

	public void setServices(ArrayList<ServProvHasServPt> services) {
		this.services = services;
	}

	public ArrayList<ServiceProvider> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<ServiceProvider> links) {
		this.links = links;
	}

	public String members() {
		return "fName, lName, phone, passwd, emailId, gender, qualification, regNo"
				+ ", subscribed, subsDate";
	}

	public String updateMembers() {
		return "fName=?, lName=?, emailId=?, gender=?, qualification=?, regNo=?"
				+ ", subscribed=?, subsDate=?";
	}

	public Object[] memberValues(String extraValue) {
		int count = memberCount();
		if (extraValue != null) {
			count++;
		}
		Object[] values = new Object[count];
		int i = 0;
		values[i++] = fName;
		values[i++] = lName;
		values[i++] = signInData.getPhone();
		values[i++] = signInData.getPasswd();
		values[i++] = emailId;
		values[i++] = gender;
		values[i++] = qualification;
		values[i++] = regNo;
		values[i++] = "" + subscribed;
		values[i++] = subsDate;
		if (extraValue != null) {
			values[i++] = extraValue;
		}
		return values;
	}

	public Object[] updateMemberValues(String extraValue) {
		int count = memberCount();
		if (extraValue != null) {
			count++;
		}
		Object[] values = new Object[count];
		int i = 0;
		values[i++] = fName;
		values[i++] = lName;
		values[i++] = emailId;
		values[i++] = gender;
		values[i++] = qualification;
		values[i++] = regNo;
		values[i++] = "" + subscribed;
		values[i++] = subsDate;
		if (extraValue != null) {
			values[i++] = extraValue;
		}
		values[i++] = signInData.getPhone();
		return values;
	}

	public int memberCount() {
		return members().split(",").length;
	}

}
