package com.extenprise.mapp.service.action;

import java.util.ArrayList;
import com.extenprise.mapp.form.WorkPlace;
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
                        serviceProvider.getQualification(),
                        serviceProvider.getSignInData().getPhone());

            ServicePoint servPt = new ServicePoint();


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

        } catch (Exception x) {
            x.printStackTrace();
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }
}
