package com.extenprise.mapp.customer.action;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.extenprise.mapp.customer.data.Customer;
import com.extenprise.mapp.util.DBUtil;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class PhoneExistsCheckAction {
	private Customer customer;

	public PhoneExistsCheckAction() {
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
			DebugManager.doAudit("Phone Exists Check: phone = "
					+ customer.getSignInData().getPhone());
			if (!DBUtil.isValueFound("Customer", "phone", customer
					.getSignInData().getPhone())) {
				DebugManager.doAudit("The phone is not registered with us");
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
