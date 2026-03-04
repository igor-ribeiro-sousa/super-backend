package com.api.rest.entidade.dto;

import java.io.Serializable;

public class GrokIADTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mensagemRequest;
	private String mensagemResponse;

	public String getMensagemRequest() {
		return mensagemRequest;
	}

	public void setMensagemRequest(String mensagemRequest) {
		this.mensagemRequest = mensagemRequest;
	}

	public String getMensagemResponse() {
		return mensagemResponse;
	}

	public void setMensagemResponse(String mensagemResponse) {
		this.mensagemResponse = mensagemResponse;
	}

}
