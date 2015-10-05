package com.extenprise.mapp.customer.action;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.extenprise.mapp.customer.data.Customer;
import com.extenprise.mapp.util.DBManager;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class SignInAction {
	private Customer mCustomer;

	public SignInAction() {
		mCustomer = new Customer();
	}

	public Customer getCustomer() {
		return mCustomer;
	}

	public void setCustomer(Customer customer) {
		this.mCustomer = customer;
	}

	public String execute() {
		try {
			QueryRunner run = DBManager.getQueryRunner();
			ResultSetHandler<Customer> rsh = new BeanHandler<Customer>(
					Customer.class);
			String query = "select * from Customer where phone=? and passwd=?";
			DebugManager.doAudit("Customer.getCustomer: phone = "
					+ mCustomer.getSignInData().getPhone() + ", passwd = "
					+ mCustomer.getSignInData().getPasswd());
			mCustomer = run.query(query, rsh, mCustomer.getSignInData().getPhone(),
					mCustomer.getSignInData().getPasswd());
		} catch (Exception x) {
			x.printStackTrace();
			return Action.SUCCESS;
		}
		return Action.SUCCESS;
	}

}
