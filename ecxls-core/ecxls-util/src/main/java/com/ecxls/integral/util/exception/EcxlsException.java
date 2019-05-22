package com.ecxls.integral.util.exception;

/**
 * <p>ClassName: EcxlsException<p>
 * <p>Description: 异常处理类<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年5月31日 下午3:09:44
 */
public class EcxlsException extends Exception{

	private static final long serialVersionUID = 1L;
	/**
	 * <p>Description: 默认构造函数</p>
	 */
	public EcxlsException(){
		super();
	}
	/**
	 * 
	 * <p>Description: 带消息参数构造函数</p>
	 * @param arg0 异常消息参数
	 */
	public EcxlsException(String arg0){
		super(arg0);
	}
	/**
	 * 
	 * <p>Description: 根据Throwable对象构造函数</p>
	 * @param arg0 Throwable对象参数 
	 */
	public EcxlsException(Throwable arg0){
		super(arg0);
	}
	/**
	 * 
	 * <p>Description: 根据消息参数及Throwable对象构造函数</p>
	 * @param arg0  消息参数
	 * @param arg1 Throwable对象参数
	 */
	public EcxlsException(String arg0,Throwable arg1){
		super(arg0, arg1);
	}
}
