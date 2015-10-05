package com.extenprise.mapp.service.action;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.extenprise.mapp.service.data.ServiceProvider;
import com.extenprise.mapp.util.DBUtil;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class PhoneExistsCheckAction {
	private ServiceProvider serviceProvider;

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String execute() {
		try {
			DebugManager.doAudit("Phone Exists Check: phone = "
					+ serviceProvider.getSignInData().getPhone());
			if (!DBUtil.isValueFound("ServiceProvider", "phone",
					serviceProvider.getSignInData().getPhone())) {
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
