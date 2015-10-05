package com.extenprise.mapp.service.action;

import java.util.ArrayList;

import com.extenprise.mapp.data.ServProvListItem;
import com.extenprise.mapp.form.SearchServProvForm;
import com.opensymphony.xwork2.Action;

public class SearchAction {
	SearchServProvForm form;
	ArrayList<ServProvListItem> list;

	public SearchAction() {
		form = new SearchServProvForm();
		list = new ArrayList<ServProvListItem>();
	}
	
	public SearchServProvForm getForm() {
		return form;
	}

	public void setForm(SearchServProvForm form) {
		this.form = form;
	}

	public ArrayList<ServProvListItem> getList() {
		return list;
	}

	public void setList(ArrayList<ServProvListItem> list) {
		this.list = list;
	}

	public String execute() {
		return Action.SUCCESS;
	}
}
