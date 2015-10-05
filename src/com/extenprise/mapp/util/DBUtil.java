package com.extenprise.mapp.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public abstract class DBUtil {
	public static String getPlaceHolder(int count, String symbol) {
		if (count < 1) {
			return null;
		}

		String result = symbol;
		for (int i = 1; i < count; i++) {
			result += "," + symbol;
		}
		return result;
	}

	public static <T> ResultSetHandler<T> getResultSetHandler(
			final Class<T> type) {
		return new ResultSetHandler<T>() {

			public T handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return type.cast(rs.getObject(1));
				}
				return null;
			}
		};
	}

	public static boolean isValueFound(String table, String column, String value)
			throws NamingException, SQLException {
		QueryRunner run = DBManager.getQueryRunner();
		String query = "select " + column + " from " + table + " where "
				+ column + "=?";
		return (run.query(query, getResultSetHandler(String.class), value) != null);
	}

	public static int getServicePointId(String name, String location, int idCity)
			throws SQLException, NamingException {
		QueryRunner run = DBManager.getQueryRunner();
		String query = "select idServicePoint from ServicePoint where name=? and location=? "
				+ "and idCity=?";
		try {
			return run.query(query, getResultSetHandler(Integer.class), name,
					location, idCity);
		} catch (NullPointerException e) {
			return -1;
		}
	}
}
