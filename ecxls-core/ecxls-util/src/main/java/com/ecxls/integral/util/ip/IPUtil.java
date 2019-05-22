package com.ecxls.integral.util.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ecxls.integral.util.exception.EcxlsException;

/**
 * <p>ClassName: IPUtil<p>
 * <p>Description: 专门用于获取客服端和服务端ip地址<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年7月24日 上午10:53:04
 */
public class IPUtil {

	private static Log log = LogFactory.getLog(IPUtil.class);
	
	/**
	 * 获取客户端IP 
	 * @author chenluning	
	 * @createTime 2018年7月24日 上午10:52:41
	 * @param request
	 * @return
	 */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
          } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	     }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")||ip.equals("0:0:0:0:0:0:0:1")){
  	          //根据网卡取本机配置的IP
  	          InetAddress inet= null;
  		      try{		    	  
  		          inet = InetAddress.getLocalHost();
  		       } catch(Exception e){
  		    	   e.printStackTrace();
  		       }
  	         ip = inet.getHostAddress();
  	         }
            
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ip != null && ip.length() > 15){ 
            if(ip.indexOf(",")> 0 ){  
                ip = ip.substring(0,ip.indexOf(","));  
            }  
        }  
        return ip;   
    }
    
    /**
	 * 获取服务器IP
	 * @author chenluning
	 * @return Stirng  返回服务端ip地址
	 * @throws EcxlsException
	 */
	public static String getServerIp() throws EcxlsException {
		String server_IP =null;
		 try {  
		        InetAddress address=InetAddress.getLocalHost();
		        server_IP=address.getHostAddress();
		        return server_IP;
		    } catch (Exception e) {  
		        throw new EcxlsException(e);  
		    }  
	}
	
	/**
	 * 获取本地mac地址 
	 * @author chenluning	
	 * @createTime 2018年7月24日 上午10:50:17
	 * @return
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static String getLocalMac() throws SocketException, UnknownHostException {
		InetAddress ia = InetAddress.getLocalHost();
		//获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		log.info("本机MAC地址:"+sb.toString().toUpperCase());
		return sb.toString().toUpperCase();
	}

	/**
	 * ping 地址是否可用 
	 * @author chenluning	
	 * @createTime 2018年5月17日 上午10:38:14
	 * @param ipAddress
	 * @return
	 * @throws IOException
	 */
	public boolean ping(String ipAddress) throws IOException {
		//超时应该在3钞以上      
        int  timeOut =  3000 ;    
        // 当返回值是true时，说明host是可用的，false则不可。
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);     
        return status;
    }
    
	/**
	 * ping 并进行一直打印  
	 * @author chenluning	
	 * @createTime 2018年7月24日 上午10:51:48
	 * @param ipAddress
	 * @throws IOException
	 */
    public void pingLine(String ipAddress) throws IOException {
        String line = null;
        try {
            Process pro = Runtime.getRuntime().exec("ping " + ipAddress);
            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    pro.getInputStream()));
            while ((line = buf.readLine()) != null)
            	log.info(line);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
    }
    
    /**
     * ping 并进行一直打印   
     * @author chenluning	
     * @createTime 2018年7月24日 上午10:51:37
     * @param ipAddress
     * @param pingTimes
     * @param timeOut
     * @return
     */
    public boolean ping(String ipAddress, int pingTimes, int timeOut) {  
        BufferedReader in = null;  
        // 将要执行的ping命令,此命令是windows格式的命令  
        Runtime r = Runtime.getRuntime();  
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;  
        try {   // 执行命令并获取输出  
            log.info(pingCommand);   
            Process p = r.exec(pingCommand);   
            if (p == null) {    
                return false;   
            }
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   
            int connectedCount = 0;   
            String line = null;   
            while ((line = in.readLine()) != null) {    
                connectedCount += getCheckResult(line);   
            }   
            // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真  
            return connectedCount == pingTimes;  
        } catch (Exception ex) {   
            ex.printStackTrace();   // 出现异常则返回假  
            return false;  
        } finally {   
            try {    
                in.close();   
            } catch (IOException e) {    
                e.printStackTrace();   
            }  
        }
    }
    
   /**
    * 若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.  
    * @author chenluning	
    * @createTime 2018年7月24日 上午10:51:23
    * @param line
    * @return
    */
    private int getCheckResult(String line) {  // log.info("控制台输出的结果为:"+line);  
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(line);  
        while (matcher.find()) {
            return 1;
        }
        return 0; 
    }

}
