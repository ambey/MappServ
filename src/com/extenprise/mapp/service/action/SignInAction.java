package com.extenprise.mapp.service.action;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.extenprise.mapp.service.data.ServiceProvider;
import com.extenprise.mapp.util.DBManager;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class SignInAction {

	private ServiceProvider serviceProvider;

	public SignInAction() {
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
			QueryRunner run = DBManager.getQueryRunner();
			ResultSetHandler<ServiceProvider> rsh = new BeanHandler<ServiceProvider>(
					ServiceProvider.class);
			String query = "select * from ServiceProvider where phone=? and passwd=?";
			DebugManager.doAudit("Service SignIn phone = "
					+ serviceProvider.getSignInData().getPhone()
					+ ", passwd = "
					+ serviceProvider.getSignInData().getPasswd());
			serviceProvider = run.query(query, rsh, serviceProvider
					.getSignInData().getPhone(), serviceProvider
					.getSignInData().getPasswd());

		} catch (Exception x) {
			x.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
}
