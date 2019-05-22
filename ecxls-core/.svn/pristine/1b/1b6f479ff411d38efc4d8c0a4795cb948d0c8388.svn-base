package com.ecxls.integral.util.file;

import java.io.File;
import java.util.UUID;

import com.ecxls.integral.util.string.StringUtils;

public class UploadPath{
	
	private String uploadPath = "E:\\image";
	
	public static String OS = System.getProperty("os.name").toLowerCase(); 
	
	// 自定义目录路径
	private String categoryDir;
	// 原始文件名
	private String originFileName;
	// 保存的文件名
	private String fileName;
	// 相对路径
	private String relativeDir;				
		
	
	public UploadPath(String categoryDir_, String fileName_, boolean isRename_){
		this.categoryDir = categoryDir_;
		this.originFileName = fileName_;
		fileName = originFileName;
		if(isRename_){
			fileName = rename();
		}		
	}

	public String getCategoryDir() {
		return categoryDir;
	}

	public String getRelativeDir() {
		if(StringUtils.isEmpty(relativeDir)){
			StringBuilder sb = new StringBuilder();
			sb.append(File.separator);
			sb.append("upload");
			sb.append(File.separator);
			/*sb.append("");		
			sb.append(File.separator);*/
			sb.append(categoryDir);
			sb.append(File.separator);		
			relativeDir = sb.toString();	
		}
		return relativeDir;
	}
	
	
	public String getWebRelativeDir(){
		StringBuilder sb = new StringBuilder();
		sb.append("/");
		sb.append("upload");
		sb.append("/");
		sb.append(categoryDir);
		sb.append("/");
		return sb.toString();		
	}

	
	public String getWebRelativePath(){
		StringBuilder sb = new StringBuilder();
		sb.append(getWebRelativeDir());
		sb.append(fileName);
		return sb.toString();		
	}
	
	public String getRelativePath(){
		return getRelativeDir() + fileName;
	}
	
	public String getFullDir() {
		StringBuilder sb = new StringBuilder();
		String uploadPath = this.uploadPath;
		if (OS.contains("window")) {
			sb.append(StringUtils.isBlank(uploadPath) ? ServletContextHelper.getRealPath() : uploadPath);
		}		
		sb.append(getRelativeDir());
		return sb.toString();
	}
	
	public String getFullPath(){	
		return getFullDir() + File.separator + fileName;
	}
	
	public String getFullPathAppendPostfix(String postfix){
		return getFullDir() + File.separator + StringUtils.appendNamePostfix(fileName, postfix);
	}
	
	public String getOriginFileName(){
		return originFileName;
	}
	
	public String getFileName(){
		return fileName;
	}

	private String rename() {
		String suffix = StringUtils.getFileExt(fileName);
		//加上UUID重命名
		String newName = UUID.randomUUID().toString();
		
		//加上后缀返回
		return newName + (suffix.equals("") ? "" : "." + suffix);
	}
}
