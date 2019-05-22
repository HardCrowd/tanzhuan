package com.ecxls.integral.util.convert;

import com.ecxls.integral.util.exception.EcxlsException;

/**
 * <p>ClassName: ConvertAbstract<p>
 * <p>Description: 专门用字符串编码的处理<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年5月31日 下午2:43:13
 */
public abstract class ConvertUtils{
	
	
	 /** 要转换编码格式 */
	private String code="UTF-8";
	
	/**
	 * 默认构造方法 
	 */
	public ConvertUtils(){}
	
	/**
	 * 有参数的构造方法生成LDConvert类对象,然后调用LDConvert类里面的方法
	 * @param code 要转换的编码格式
	 */
	public ConvertUtils(String code){
		this.code=code;
	}

	/**
	 * 将指定字符串按默认编码转换输出（只能有参数的构造方法声明的对象使用）
	 * @author chenluning
	 * @param data 指定的字符串
	 * @return String
	 * @throws EcxlsException
	 */
	public String getObjectByCode(String data) throws EcxlsException {
		return getObjectByCode(data,code);
	}

	/**
	 * 将指定字符串按指定编码转换输出
	 * @author chenluning
	 * @param data 指定字符串
	 * @param code 指编码类型
	 * @return String
	 * @throws EcxlsException
	 */
	public String getObjectByCode(String data, String code)
			throws EcxlsException {
		return getObjectByCode("ISO-8859-1", data, code);
	}
	/**
	 * 
	 * 将指定字符串按指定编码转换输出		
	 * @param dataCode 指定字符串的编码格式
	 * @param data 指定字符串
	 * @param code 要转换的编码格式
	 * @return
	 * @throws EcxlsException
	 */
	public String getObjectByCode(String dataCode,String data, String code)
	throws EcxlsException {
		try {
			return new String(data.getBytes(dataCode),code);
		} catch (Exception e) {
			throw new EcxlsException(e);
		}
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
