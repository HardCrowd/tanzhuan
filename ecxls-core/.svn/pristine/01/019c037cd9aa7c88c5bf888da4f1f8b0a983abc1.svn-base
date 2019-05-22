package com.ecxls.integral.util.ip;

import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecxls.integral.util.httpclient.HttpUtils;
import com.ecxls.integral.util.threadpool.TaskExecutor;

/**
 * <p>ClassName: AddressUtil<p>
 * <p>Description: 根据IP地址获取详细的地域信息<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年10月26日 下午2:11:04
 */
public class AddressUtil { 
	 /**淘宝IP库*/
	 private static final String TAOBAO_IP_URL = "http://ip.taobao.com/service/getIpInfo2.php?ip=";

	 public static String getAddreByAsyncTaoBao(String ip) throws Exception {
		if (StringUtils.isBlank(ip)) new NullPointerException("ip cannot be null");
		return TaskExecutor.getInstance().submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				 return HttpUtils.connection(TAOBAO_IP_URL + ip, HttpUtils.GET);
			}
			
		 }).get();
 
	 }
	 
	 
	 public static String getAddreTaoBao(String ip) throws Exception {
			if (StringUtils.isBlank(ip)) new NullPointerException("ip cannot be null");
			return HttpUtils.connection(TAOBAO_IP_URL + ip, HttpUtils.GET);
		 }
	 
	 public static void main(String[] args) throws Exception {
			 String result = getAddreByAsyncTaoBao("202.105.37.58");
			 System.out.println(result);
			 
			 JSONObject parseObject = JSON.parseObject(result);
			 JSONObject data = parseObject.getJSONObject("data");
			 String country = (String) data.get("country");
			 String region = (String) data.get("region");
			 String city = (String) data.get("city");
			 String loginAddress = country + region + city;
			 System.out.println(loginAddress);
	 }

}
