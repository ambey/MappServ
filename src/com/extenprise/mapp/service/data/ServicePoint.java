package com.extenprise.mapp.service.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.extenprise.mapp.data.City;

public class ServicePoint implements Serializable, Comparable<ServicePoint> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int idServicePoint;
	private String name;
	private String location;
	private String pincode;
	private String phone;
	private String altPhone;
	private String emailId;
	private City city;
	private ArrayList<ServProvHasServPt> services;

	public ServicePoint() {
		city = new City();
		services = new ArrayList<ServProvHasServPt>();
	}

	public String toString() {
		return "id: " + idServicePoint + ",\n" + "name: " + name
				+ ", location: " + location + ",\n" + "pincode: " + pincode
				+ ", phone: " + phone + ",\n" + "altPhone: " + altPhone
				+ ", emailId: " + emailId + ",\n" + "city: {" + city.toString()
				+ "},\n";
	}

	public ArrayList<ServProvHasServPt> getServices() {
		return services;
	}

	public void setServices(ArrayList<ServProvHasServPt> services) {
		this.services = services;
	}

	public boolean addService(ServProvHasServPt service) {
		return services.add(service);
	}

	public ServProvHasServPt getService(int position) {
		try {
			return services.get(position);
		} catch (IndexOutOfBoundsException x) {
			x.printStackTrace();
		}
		return null;
	}

	public int getIdServicePoint() {
		return idServicePoint;
	}

	public void setIdServicePoint(int idServicePoint) {
		this.idServicePoint = idServicePoint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String members() {
		return "name, location, phone, emailId, altPhone";
	}

	public String updateMembers() {
		return "name=?, location=?, phone=?, emailId=?, altPhone=?";
	}

	public Object[] memberValues(String extraValue) {
		int count = memberCount();
		if (extraValue != null) {
			count++;
		}
		Object[] values = new Object[count];
		int i = 0;
		values[i++] = name;
		values[i++] = location;
		values[i++] = phone;
		values[i++] = emailId;
		values[i++] = altPhone;
		if (extraValue != null) {
			values[i++] = extraValue;
		}
		return values;
	}

	public int memberCount() {
		return members().split(",").length;
	}

	public int compareTo(ServicePoint s) {
		int result = name.compareTo(s.getName());
		if (result != 0) {
			return result;
		}
		result = location.compareTo(s.getLocation());
		if (result != 0) {
			return result;
		}
		return (city.getIdCity() - s.getCity().getIdCity());
	}
}
