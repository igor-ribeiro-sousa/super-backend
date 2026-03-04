package com.api.rest.service.impl;

import org.springframework.stereotype.Service;

import com.api.rest.adapter.AdapterGroqIA;
import com.api.rest.entidade.GrokIA;
import com.api.rest.repository.GrokIARepository;
import com.api.rest.service.GrokIAService;
import com.crud.framework.service.impl.GenericServiceImpl;

@Service
public class GrokIAServiceImpl extends GenericServiceImpl<GrokIA, Long> implements GrokIAService {

	private final AdapterGroqIA adapterGroqIA;

    public GrokIAServiceImpl(GrokIARepository repository, AdapterGroqIA adapterGroqIA) {
        super(repository);
        this.adapterGroqIA = adapterGroqIA;
    }

	@Override
	public GrokIA comunicar(GrokIA openIA) {

		String respostaIA = adapterGroqIA.enviarMensagem(openIA.getMensagemRequest());

		openIA.setMensagemResponse(respostaIA);

		return super.inserir(openIA);
	}
}