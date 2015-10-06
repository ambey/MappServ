package com.extenprise.mapp.customer.action;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.extenprise.mapp.customer.data.Customer;
import com.extenprise.mapp.data.City;
import com.extenprise.mapp.util.DBManager;
import com.extenprise.mapp.util.DBUtil;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class UpdateAction {
    private Customer customer;

    public UpdateAction() {
        customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String execute() {
        try {
            QueryRunner run = DBManager.getQueryRunner();
            String query = "select idCity from City where city=? and state=? and country=?";
            DebugManager.doAudit("Customer UpdateAction: query = " + query);
            ResultSetHandler<Integer> rsh = DBUtil
                    .getResultSetHandler(Integer.class);
            City city = customer.getCity();
            int idCity = run.query(query, rsh, city.getCity(), city.getState(),
                    city.getCountry());
            city.setIdCity(idCity);

            query = "update Customer set " + customer.updateMembers()
                    + " where phone = ? ";
            DebugManager.doAudit("Customer UpdateAction: idCity = " + idCity
                    + ": batch query = " + query);
            run.batch(query,
                    new Object[][] { customer.updateMemberValues("" + idCity) });
        } catch (Exception x) {
            x.printStackTrace();
            return Action.ERROR;
        }
        return Action.SUCCESS;
    }

}
