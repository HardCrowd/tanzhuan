package com.ecxls.integral.util.httpclient;

import org.apache.http.client.CookieStore;

public class HttpResult {
	private String result;
	private CookieStore cookieStore;
	private String securityCode;

	public HttpResult() {
	}

	public HttpResult(String result, CookieStore cookieStore) {
		this.result = result;
		this.cookieStore = cookieStore;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
}
