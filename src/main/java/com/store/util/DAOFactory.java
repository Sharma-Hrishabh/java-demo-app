package com.store.util;

import com.store.dao.DAO;

public class DAOFactory {
	private static DAO dao;
	public static DAO createObject(String ss) {
		if(dao==null && ss.equals("getDAO")) {
			dao = new DAO();
		}
		return dao;
	}
}
