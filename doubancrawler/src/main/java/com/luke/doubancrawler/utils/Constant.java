package com.luke.doubancrawler.utils;


public class Constant {
	public static boolean PROXY_USE = PropertiesUtil.getStringByKey("proxy.use").toLowerCase().equals("true");
}
