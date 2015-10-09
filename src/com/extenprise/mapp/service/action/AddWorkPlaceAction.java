package com.extenprise.mapp.service.action;

import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.extenprise.mapp.data.City;
import com.extenprise.mapp.form.WorkPlace;
import com.extenprise.mapp.service.data.ServicePoint;
import com.extenprise.mapp.util.DBManager;
import com.extenprise.mapp.util.DBUtil;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class AddWorkPlaceAction {
    WorkPlace form;

    public AddWorkPlaceAction() {
        form = new WorkPlace();
    }

    public WorkPlace getForm() {
        return form;
    }

    public void setForm(WorkPlace form) {
        this.form = form;
    }

    public String execute() {
        try {
            DebugManager.doAudit(form.toString());

            QueryRunner run = DBManager.getQueryRunner();
            ResultSetHandler<Integer> rsh = DBUtil
                    .getResultSetHandler(Integer.class);
            String query = "";

            run.batch("update ServiceProvider set qualification = ? where phone = ?",
                    new Object[][] {new Object[]{form.getQualification()},
                            new Object[]{form.getSignInData().getPhone()}});

            ServicePoint servPt = form.getServicePoint();

            City city = servPt.getCity();
            query = "select idCity from City where city=? and state=? and country=?";
            int idCity = run.query(query, rsh, city.getCity(),
                    city.getState(), city.getCountry());
            city.setIdCity(idCity);

            int idServPt = DBUtil.getServicePointId(servPt.getName(),
                    servPt.getLocation(), servPt.getCity().getIdCity());
            if(idServPt != -1) {
                servPt.setIdServicePoint(idServPt);
            }

            query = "update ServicePoint set" + servPt.updateMembers()
                    + ", idCity = ? ";
            run.batch(
                    query,
                    new Object[][]{servPt.memberValues(""
                            + servPt.getCity().getIdCity())});
            DebugManager.doAudit("Serv Prov addWorkPlace: idCity = "
                    + idCity + ": batch query = " + query);



                /*query = "insert into ServProvHasServPt (" + s.members()
                        + ", servProvPhone, idServicePoint) values("
                        + DBUtil.getPlaceHolder(s.memberCount(), "?") + ",?,?)";*/
            DebugManager.doAudit("Serv Prov addWorkPlace: query = " + query );
                /*run.batch(
                        query,
                        new Object[][] { s.memberValues(new String[] {
                                form.getSignInData().getPhone(),
                                "" + servPt.getIdServicePoint() }) });*/
            // DebugManager.doAudit("ServProvHasServPt id: " + id);

        } catch (Exception x) {
            x.printStackTrace();
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
}
