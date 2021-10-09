package com.ecommerce.tul.cart.domain.data;

import java.io.Serializable;

public class Response implements Serializable {
	private static final long serialVersionUID = -4312610454933350482L;
	private int httpCode;
	private String message;
	private transient Object resp;

	public Response(int httpCode, String message, Object resp) {
		super();
		this.httpCode = httpCode;
		this.message = message;
		this.resp = resp;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return resp;
	}

	public void setResponse(Object resp) {
		this.resp = resp;
	}

}
