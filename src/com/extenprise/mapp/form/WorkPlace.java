package com.extenprise.mapp.form;

import java.lang.Override;
import java.lang.String;

import com.extenprise.mapp.data.City;
import com.extenprise.mapp.data.SignInData;
import com.extenprise.mapp.service.data.ServProvHasServPt;
import com.extenprise.mapp.service.data.ServicePoint;

/**
 * Created by avinash on 4/10/15.
 */
public class WorkPlace {

    private SignInData signInData;
    private String name;
    private String location;
    private String pincode;
    private String phone;
    private String altPhone;
    private String emailId;
    private City city;
    private String servPointType;
    private String servCatagory;
    private String speciality;
    private float experience;
    private String qualification;
    private String workingDays;
    private float consultFee;
    private int startTime; //as minutes
    private int endTime;//as minutes

    public WorkPlace() {
        signInData = new SignInData();
    }

    public SignInData getSignInData() {
        return signInData;
    }

    public void setSignInData(SignInData signInData) {
        this.signInData = signInData;
    }

    @Override
    public String toString() {
        return "WorkPlace{" +
                "name=" + name +
                ", location=" + location +
                ", pincode=" + pincode +
                ", phone=" + phone +
                ", altPhone=" + altPhone +
                ", emailId=" + emailId +
                ", city=" + city +
                ", servPointType=" + servPointType +
                ", servCatagory=" + servCatagory +
                ", speciality=" + speciality +
                ", experience=" + experience +
                ", qualification=" + qualification +
                ", workingDays=" + workingDays +
                ", consultFee=" + consultFee +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public ServicePoint getServicePoint() {
        ServicePoint spt = new ServicePoint();
        spt.setName(name);
        spt.setLocation(location);
        spt.setAltPhone(altPhone);
        spt.setCity(city);
        spt.setEmailId(emailId);
        spt.setPhone(phone);
        spt.setPincode(pincode);

        return spt;
    }

    public ServProvHasServPt getServProvHasServPt() {
        ServProvHasServPt spspt = new ServProvHasServPt();
        spspt.setConsultFee(consultFee);
        spspt.setEndTime(endTime);
        spspt.setStartTime(startTime);
        spspt.setWorkingDays(workingDays);
        spspt.setServPointType(servPointType);
        spspt.setExperience(experience);


        return spspt;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public String getServPointType() {
        return servPointType;
    }

    public void setServPointType(String servPointType) {
        this.servPointType = servPointType;
    }

    public String getServCatagory() {
        return servCatagory;
    }

    public void setServCatagory(String servCatagory) {
        this.servCatagory = servCatagory;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    public float getConsultFee() {
        return consultFee;
    }

    public void setConsultFee(float consultFee) {
        this.consultFee = consultFee;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
