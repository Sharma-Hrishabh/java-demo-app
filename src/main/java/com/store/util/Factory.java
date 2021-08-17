package com.store.util;

import com.store.controller.Controller;

public class Factory {
	private static Controller cs;
	public static Controller createObject(String ss) {
		if(cs==null && ss.equals("getController")) {
			cs=new Controller();
		}
		return cs;
	}
}
