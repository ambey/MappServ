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
}
