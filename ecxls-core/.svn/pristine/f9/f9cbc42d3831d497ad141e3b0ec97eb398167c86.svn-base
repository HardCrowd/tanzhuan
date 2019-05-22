package com.ecxls.integral.util.file;

import java.io.File;

import javax.servlet.ServletContext;

public class ServletContextHelper {
	private final static ServletContextHelper servletContextHelper = new ServletContextHelper();
	
	private ServletContext servletContext;
	
	public static void init(ServletContext servletContext){  
		//只初始化一次
		if(servletContextHelper.servletContext == null){
			servletContextHelper.servletContext = servletContext;
		}
	}
	
	public static ServletContext getServletContext(){
		return servletContextHelper.servletContext;
	}
	
	public static String getRealPath(){		
		if(servletContextHelper.servletContext!=null){
			return servletContextHelper.servletContext.getRealPath("/");
		}
		File file = new File("");
		return file.getPath();
	}
	
	public static String getContextPath(){
		if(servletContextHelper.servletContext!=null){
			return servletContextHelper.servletContext.getContextPath();
		}
		return "";
	}
}
