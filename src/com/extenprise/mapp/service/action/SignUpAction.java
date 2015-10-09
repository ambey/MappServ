package com.extenprise.mapp.service.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.extenprise.mapp.data.City;
import com.extenprise.mapp.service.data.ServProvHasServPt;
import com.extenprise.mapp.service.data.ServicePoint;
import com.extenprise.mapp.service.data.ServiceProvider;
import com.extenprise.mapp.util.DBManager;
import com.extenprise.mapp.util.DBUtil;
import com.extenprise.mapp.util.DebugManager;
import com.opensymphony.xwork2.Action;

public class SignUpAction {
	private ServiceProvider serviceProvider;

	public SignUpAction() {
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
			DebugManager.doAudit(serviceProvider.toString());
			ArrayList<ServProvHasServPt> services = serviceProvider
					.getServices();
			QueryRunner run = DBManager.getQueryRunner();
			String query = "insert into ServiceProvider ("
					+ serviceProvider.members() + ") " + "values("
					+ DBUtil.getPlaceHolder(serviceProvider.memberCount(), "?")
					+ ")";
			DebugManager.doAudit("Customer SignUpAction: query = " + query);
			run.batch(query,
					new Object[][] { serviceProvider.memberValues(null) });

			ResultSetHandler<Integer> rsh = DBUtil
					.getResultSetHandler(Integer.class);
			Set<String> serviceNames = new HashSet<String>();
			for (ServProvHasServPt s : services) {
				serviceNames.add(s.getService());

				ServicePoint servPt = s.getServicePoint();

				City city = servPt.getCity();
				query = "select idCity from City where city=? and state=? and country=?";
				int idCity = run.query(query, rsh, city.getCity(),
						city.getState(), city.getCountry());
				city.setIdCity(idCity);
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
				DebugManager.doAudit("Customer SignUpAction: query = " + query);
				run.batch(
						query,
						new Object[][] { servPt.memberValues(""
								+ servPt.getCity().getIdCity()) });
				//DebugManager.doAudit("Service Point id: " + id);
				//servPt.setIdServicePoint(id);
				DebugManager.doAudit("Service Provider SignUpAction: idCity = "
						+ idCity + ": batch query = " + query);
			}
			String[][] servNames = new String[serviceNames.size()][1];
			int i = 0;
			for (String s : serviceNames) {
				servNames[i++][0] = s;
			}
			try {
				run.batch("insert into Service values(?)", servNames);
			} catch (SQLException x) {
				x.printStackTrace();
			}

			for (ServProvHasServPt s : services) {
				ServicePoint servPt = s.getServicePoint();
				query = "insert into ServProvHasServPt (" + s.members()
						+ ", servProvPhone, idServicePoint) values("
						+ DBUtil.getPlaceHolder(s.memberCount(), "?") + ",?,?)";
				DebugManager.doAudit("Customer SignUpAction: query = " + query
						+ ", servProv phone = "
						+ serviceProvider.getSignInData().getPhone()
						+ ", servPoint id = " + servPt.getIdServicePoint());
				run.batch(
						query,
						new Object[][] { s.memberValues(new String[] {
								serviceProvider.getSignInData().getPhone(),
								"" + servPt.getIdServicePoint() }) });
				//DebugManager.doAudit("ServProvHasServPt id: " + id);
				//s.setIdServProvHasServPt(id);
			}
		} catch (Exception x) {
			x.printStackTrace();
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
}
