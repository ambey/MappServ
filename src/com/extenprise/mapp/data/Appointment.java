package com.extenprise.mapp.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.extenprise.mapp.customer.data.Customer;
import com.extenprise.mapp.service.data.ServProvHasServPt;

public class Appointment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int id;
    private String servPointType;
    private String date;
    private int fromTime; //as minutes
    private int toTime;//as minutes
    private ServProvHasServPt service;
    private Customer customer;
    private ArrayList<Report> reports;

    public Appointment() {
        reports = new ArrayList<Report>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean addReport(Report report) {
        return reports.add(report);
    }

    public int getReportCount() {
        return reports.size();
    }

    public String getServPointType() {
        return servPointType;
    }

    public void setServPointType(String servPointType) {
        this.servPointType = servPointType;
    }

    public String getDateOfAppointment() {
        return date;
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.date = dateOfAppointment;
    }

    public int getFromTime() {
        return fromTime;
    }

    public void setFromTime(int fromTime) {
        this.fromTime = fromTime;
    }

    public int getToTime() {
        return toTime;
    }

    public void setToTime(int toTime) {
        this.toTime = toTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
