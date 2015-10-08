package com.extenprise.mapp.service.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.extenprise.mapp.data.Appointment;

public class ServProvHasServPt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idServProvHasServPt;
	private String servPointType;
	private String service;
	private float experience;
	private String workingDays;
	private float consultFee;
	private int startTime; // as minutes
	private int endTime;// as minutes
	// private ArrayList<CustomerHistoryData> historyData;
	private ArrayList<Appointment> appointments;
	private ServicePoint servicePoint;
	private String servProvPhone;

	public ServProvHasServPt() {
		appointments = new ArrayList<Appointment>();
		servicePoint = new ServicePoint();
	}
	
	public String toString() {
		String value = "id: " + idServProvHasServPt + ",\n"
				+ "type: " + servPointType + ", service: " + service + ",\n"
				+ "exp: " + experience + ", fee: " + consultFee + ",\n"
				+ "work days: " + workingDays + ",\n"
				+ "start: " + startTime + ", end: " + endTime + ",\n"
				+ "appointments: {";
		for(Appointment a : appointments) {
			value += a.toString() + ",\n";
		}
		value += "},\n service point: {" + servicePoint.toString() + "},\n";
		value += "service provider phone: " + servProvPhone;
		return value;
	}
	
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public String getServProvPhone() {
		return servProvPhone;
	}

	public void setServProvPhone(String servProvPhone) {
		this.servProvPhone = servProvPhone;
	}

	public int getIdServProvHasServPt() {
		return idServProvHasServPt;
	}

	public void setIdServProvHasServPt(int idServProvHasServPt) {
		this.idServProvHasServPt = idServProvHasServPt;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public float getConsultFee() {
		return consultFee;
	}

	public void setConsultFee(float consultFee) {
		this.consultFee = consultFee;
	}

	public String getServPointType() {
		return servPointType;
	}

	public void setServPointType(String servPointType) {
		this.servPointType = servPointType;
	}

	public String getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	/*
	 * public ArrayList<CustomerHistoryData> getHistoryData() { return
	 * historyData; }
	 */

	/*
	 * public void setHistoryData(ArrayList<CustomerHistoryData> historyData) {
	 * this.historyData = historyData; }
	 */

	/*
	 * public ArrayList<Appointment> getAppointment() { return appointment; }
	 */

	/*
	 * public void setAppointment(ArrayList<Appointment> appointment) {
	 * this.appointment = appointment; }
	 */

	public ServicePoint getServicePoint() {
		return servicePoint;
	}

	public void setServicePoint(ServicePoint servicePoint) {
		this.servicePoint = servicePoint;
	}

	public String members() {
		return "servPointType, service, experience, workingDays, consultFee"
				+ ", startTime, endTime";
	}

	public Object[] memberValues(String[] extraValues) {

		int count = memberCount();
		if (extraValues != null) {
			count += extraValues.length;
		}
		Object[] values = new Object[count];
		int i = 0;
		values[i++] = servPointType;
		values[i++] = service;
		values[i++] = "" + experience;
		values[i++] = workingDays;
		values[i++] = "" + consultFee;
		values[i++] = String.format("%02d:%02d", startTime / 60, startTime % 60);
		values[i++] = String.format("%02d:%02d", endTime / 60, endTime % 60);
		if (extraValues != null) {
			for (String s : extraValues) {
				values[i++] = s;
			}
		}
		return values;
	}

	public int memberCount() {
		return members().split(",").length;
	}

}
