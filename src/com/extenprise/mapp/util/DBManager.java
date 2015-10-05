package com.extenprise.mapp.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

public class DBManager {
	private static Hashtable<String, DataSource> dataSources;
	private static QueryRunner run;

	public static synchronized Connection getConnection() {
		try {
			DataSource dataSource = getDataSource();
			return dataSource.getConnection();
		} catch (Exception x) {
			x.printStackTrace();
		}
		return null;
	}

	public static synchronized Connection getConnection(String paramString) {
		// System.out.println("inside get connection 2");
		try {
			DataSource localDataSource = getDataSource(paramString);
			System.out.println("localdatasource" + localDataSource);
			return localDataSource.getConnection();
		} catch (SQLException localSQLException) {
			System.out.println("[EMPL] Could not connect to Database defined "
					+ localSQLException.getMessage());
		} catch (NamingException localNamingException) {
			System.out.println("[EMPL] Could not locate Data Source!! "
					+ "Check your JCA and JNDI params :: "
					+ localNamingException.getMessage());
		}
		return null;
	}

	public static synchronized QueryRunner getQueryRunner()
			throws NamingException {
		if (run == null) {
			run = new QueryRunner(getDataSource());
		}
		return run;
	}

	public static DataSource getDataSource() throws NamingException {
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle("com.extenprise.mapp.db");
		return getDataSource(resourceBundle.getString("datasource"));
	}

	private static DataSource getDataSource(String paramString)
			throws NamingException {
		if (dataSources == null) {
			dataSources = new Hashtable<String, DataSource>();
		}
		DataSource localDataSource = dataSources.get(paramString);
		System.out.println("localdatasource2" + localDataSource);
		if (localDataSource == null) {
			InitialContext localInitialContext = new InitialContext();
			System.out.println("33333334444444 " + localInitialContext);
			try {
				localDataSource = (DataSource) localInitialContext
						.lookup("java:/" + paramString);
			} catch (Exception x) {

			} finally {
				System.out.println("datasource = " + localDataSource);
				if (localDataSource == null) {
					System.out.println("java:comp/env/" + paramString);
					localDataSource = (DataSource) localInitialContext
							.lookup("java:comp/env/" + paramString);
				}
			}
			//
			System.out.println("4444444555555");
			dataSources.put(paramString, localDataSource);
			System.out.println("55555555" + localDataSource);
			// System.out.println("paramString4"+paramString+"localDataSource"+localDataSource);
		}
		return localDataSource;
	}
}
