package com.api.rest.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_grokIA")
public class GrokIA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagemRequest;
	@Lob
	private String mensagemResponse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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