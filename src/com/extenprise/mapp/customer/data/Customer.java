package com.extenprise.mapp.customer.data;

import java.io.Serializable;
import java.util.Date;

import com.extenprise.mapp.data.City;
import com.extenprise.mapp.data.SignInData;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCustomer;
	private SignInData signInData;
	private String fName;
	private String lName;
	private String emailId;
	private String gender;
	private int age;
	private float weight;
	private float height;
	private Date dob;
	private City city;
	private String location;
	private String pincode;

	public SignInData getSignInData() {
		return signInData;
	}

	public void setSignInData(SignInData signInData) {
		this.signInData = signInData;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Customer() {
		signInData = new SignInData();
		city = new City();
	}

	public String members() {
		return "idCustomer, fName, lName, phone, passwd, emailId, gender, dob, location, pincode"
				+ ", weight, height";
	}

	public String updateMembers() {
		return "fName=?, lName=?, emailId=?, gender=?, dob=?, location=?, pincode=?"
				+ ", weight=?, height=?, idCity=?";
	}

	public Object[] memberValues(String extraValue) {
		int count = memberCount();
		if(extraValue != null) {
			count++;
		}
		Object[] values = new Object[count];
		int i = 0;
		values[i++] = "" + idCustomer;
		values[i++] = fName;
		values[i++] = lName;
		values[i++] = signInData.getPhone();
		values[i++] = signInData.getPasswd();
		values[i++] = emailId;
		values[i++] = gender;
		values[i++] = dob;
		values[i++] = location;
		values[i++] = pincode;
		values[i++] = "" + weight;
		values[i++] = "" + height;
		if(extraValue != null) {
			values[i++] = extraValue;
		}
		return values;
	}

	public Object[] updateMemberValues(String extraValue) {
		int count = memberCount();
		if(extraValue != null) {
			count++;
		}
		Object[] values = new Object[count];
		int i = 0;
		values[i++] = fName;
		values[i++] = lName;
		values[i++] = emailId;
		values[i++] = gender;
		values[i++] = dob;
		values[i++] = location;
		values[i++] = pincode;
		values[i++] = "" + weight;
		values[i++] = "" + height;
		if(extraValue != null) {
			values[i++] = extraValue;
		}
		values[i++] = phone;

		return values;
	}

	public int memberCount() {
		return members().split(",").length;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
