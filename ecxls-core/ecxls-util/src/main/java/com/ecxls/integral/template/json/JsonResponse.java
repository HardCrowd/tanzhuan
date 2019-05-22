package com.ecxls.integral.template.json;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <p>ClassName: JsonResponse<p>
 * <p>Description: 返回JSON对象<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年7月24日 上午10:45:49
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JsonResponse<T> implements Serializable{
	
	public static final String CODE_UCC = "200";
	public static final String CODE_FAIL = "1";
	public static final String CODE_ERR = "-1";

	/**相应代码**/
	private String code;
	/**接口代号**/
	private String num;
	/**成功信息**/
	private String msg;
	/**返回信息**/
	private T data;
	/**版本信息**/
	private String version;
	
	public JsonResponse(String code,String num,String mes,String version) {
		this.code = code;
		this.num = num;
		this.msg = mes;
		this.version = version;
	}
}
