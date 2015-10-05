package com.extenprise.mapp.customer.action;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.extenprise.mapp.customer.data.Customer;
import com.extenprise.mapp.data.City;
import com.extenprise.mapp.util.DBManager;
import com.extenprise.mapp.util.DBUtil;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class SignUpAction {
	private Customer customer;

	public SignUpAction() {
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
			DebugManager.doAudit("Customer SignUpAction: query = " + query);
			ResultSetHandler<Integer> rsh = DBUtil
					.getResultSetHandler(Integer.class);
			City city = customer.getCity();
			int idCity = run.query(query, rsh, city.getCity(), city.getState(),
					city.getCountry());
			city.setIdCity(idCity);
			query = "select max(id) from (select max(idCustomer) as id from Customer UNION "
					+ "select max(idCustomer) as id from CustDependents) as IdTable";
			int idCustomer = -1;
			try {
				idCustomer = run.query(query, rsh);
			} catch (NullPointerException e) {

			}
			if (idCustomer == -1) {
				idCustomer = 1000;
			}
			idCustomer++;
			customer.setIdCustomer(idCustomer);
			query = "insert into Customer (" + customer.members()
					+ ", idCity) " + "values("
					+ DBUtil.getPlaceHolder(customer.memberCount(), "?")
					+ ",?)";
			DebugManager.doAudit("Customer SignUpAction: idCity = " + idCity
					+ ": batch query = " + query);
			run.batch(query,
					new Object[][] { customer.memberValues("" + idCity) });
		} catch (Exception x) {
			x.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

}
