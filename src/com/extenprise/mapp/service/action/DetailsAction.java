package com.extenprise.mapp.service.action;

import com.extenprise.mapp.data.ServProvListItem;
import com.extenprise.mapp.service.data.ServiceProvider;
import com.opensymphony.xwork2.Action;

public class DetailsAction {
	ServProvListItem form;
	ServiceProvider serviceProvider;

	public DetailsAction() {
		form = new ServProvListItem();
		serviceProvider = new ServiceProvider();
	}
	
	public ServProvListItem getForm() {
		return form;
	}

	public void setForm(ServProvListItem form) {
		this.form = form;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String execute() {
		return Action.SUCCESS;
	}
}
