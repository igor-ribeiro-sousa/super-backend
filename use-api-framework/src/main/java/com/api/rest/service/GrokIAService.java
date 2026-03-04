package com.api.rest.service;

import com.api.rest.entidade.GrokIA;
import com.crud.framework.service.GenericService;

public interface GrokIAService extends GenericService<GrokIA, Long> {
	
	GrokIA comunicar(GrokIA grokIA);

}
