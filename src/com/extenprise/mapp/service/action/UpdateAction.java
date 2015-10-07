package com.extenprise.mapp.service.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.extenprise.mapp.data.City;
import com.extenprise.mapp.service.data.ServProvHasServPt;
import com.extenprise.mapp.service.data.ServicePoint;
import com.extenprise.mapp.service.data.ServiceProvider;
import com.extenprise.mapp.util.DBManager;
import com.extenprise.mapp.util.DBUtil;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class SignUpAction {
    private ServiceProvider serviceProvider;

    public SignUpAction() {
        serviceProvider = new ServiceProvider();
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String execute() {
        try {
            DebugManager.doAudit(serviceProvider.toString());
            ArrayList<ServProvHasServPt> services = serviceProvider
                    .getServices();
            QueryRunner run = DBManager.getQueryRunner();
            String query = "update ServiceProvider set " + serviceProvider.updateMembers()
                    + " where phone = ? ";
            DebugManager.doAudit("ServiceProvider UpdateAction: query = " + query);
            run.batch(query,
                    new Object[][] { serviceProvider.updateMemberValues(null) });

        } catch (Exception x) {
            x.printStackTrace();
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }

    public String isRegNoExists() {
        try {
            DebugManager.doAudit("Registration Number Exists Check: RegNo = "
                    + serviceProvider.getRegNo());
            if (!DBUtil.isValueFound("ServiceProvider", "regNo",
                    serviceProvider.getRegNo())) {
                DebugManager.doAudit("The registration number is not registered with us");
                return Action.SUCCESS;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Action.ERROR;
    }

    public String addWorkPlace() {
        try {
            DebugManager.doAudit(serviceProvider.toString());
            ArrayList<ServProvHasServPt> services = serviceProvider
                    .getServices();
            QueryRunner run = DBManager.getQueryRunner();
            ResultSetHandler<Integer> rsh = DBUtil
                    .getResultSetHandler(Integer.class);
            String query = "";

            for (ServProvHasServPt s : services) {

                run.batch("insert into Service values(?)", s.getService());

                run.batch("update ServiceProvider set qualification = ? where phone = ?",
                        serviceProvider.getQualification(),
                        serviceProvider.getSignInData().getPhone());

                ServicePoint servPt = s.getServicePoint();

                City city = servPt.getCity();
                query = "select idCity from City where city=? and state=? and country=?";
                int idCity = run.query(query, rsh, city.getCity(),
                        city.getState(), city.getCountry());
                city.setIdCity(idCity);




//TODO







                int idServPt = DBUtil.getServicePointId(servPt.getName(),
                        servPt.getLocation(), servPt.getCity().getIdCity());
                if(idServPt != -1) {
                    servPt.setIdServicePoint(idServPt);
                    continue;
                }
                query = "insert into ServicePoint (" + servPt.members()
                        + ", idCity) " + "values("
                        + DBUtil.getPlaceHolder(servPt.memberCount(), "?")
                        + ",?)";
                DebugManager.doAudit("Serv Prov addWorkPlace: query = " + query);
                int id = run.insertBatch(
                        query,
                        rsh,
                        new Object[][] { servPt.memberValues(""
                                + servPt.getCity().getIdCity()) });
                DebugManager.doAudit("Service Point id: " + id);
                servPt.setIdServicePoint(id);


                DebugManager.doAudit("Serv Prov addWorkPlace: idCity = "
                        + idCity + ": batch query = " + query);



                query = "insert into ServProvHasServPt (" + s.members()
                        + ", servProvPhone, idServicePoint) values("
                        + DBUtil.getPlaceHolder(s.memberCount(), "?") + ",?,?)";
                DebugManager.doAudit("Serv Prov addWorkPlace: query = " + query );
                int id = run.insertBatch(
                        query,
                        rsh,
                        new Object[][] { s.memberValues(new String[] {
                                serviceProvider.getSignInData().getPhone(),
                                "" + servPt.getIdServicePoint() }) });
                DebugManager.doAudit("ServProvHasServPt id: " + id);
            }
        } catch (Exception x) {
            x.printStackTrace();
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
}
