package com.ecxls.integral.template.json;

import org.springframework.stereotype.Component;

/**
 * <p>ClassName: ResultUtil<p>
 * <p>Description: 生成 JsonResponse 结果,用法:需要在模块扫描注入此工具类<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author luo
 * @createTime 2018年10月26日 下午1:51:05
 */
@Component
public class ResultUtil {
	
	private static final String VERSION = "1.0";
	
	private static final String SUCCESS = "200";

	/**  
	 * <p>Title: ok</p>  
	 * <p>Description:成功并且有数据 </p>  
	 * @param num
	 * @param message
	 * @param data
	 * @param version
	 * @return  
	 */  
	public static <T>JsonResponse<T> ok(String num,String message,T data, String version){
		return new JsonResponse<T>(SUCCESS, num, message, data, version);
	}
	
	public static <T>JsonResponse<T> ok(String num,String message,T data){
		return new JsonResponse<T>(SUCCESS, num, message, data, VERSION);
	}
	
	/**  
	 * <p>Title: ok</p>  
	 * <p>Description: 成功但没有数据返回</p>  
	 * @param num
	 * @param message
	 * @return  
	 */  
	public static <T>JsonResponse<T> ok(String num,String message){
		return new JsonResponse<T>(SUCCESS, num, message,VERSION);
	}
	
	public static <T>JsonResponse<T> error(String code,String num,String message,T data){
		return new JsonResponse<T>(code, num, message, data, VERSION);
	}
	
	public static <T>JsonResponse<T> error(String code,String num,String message){
		return new JsonResponse<T>(code, num, message,VERSION);
	}
}
