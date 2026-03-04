package com.api.rest.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.rest.entidade.GrokIA;
import com.api.rest.repository.GrokIARepository;
import com.api.rest.service.GrokIAService;
import com.crud.framework.service.impl.GenericServiceImpl;

@Service
public class GrokIAServiceImpl extends GenericServiceImpl<GrokIA, Long> implements GrokIAService {

    @Value("${groq.api.key}")
    private String apiKey;

    private RestTemplate restTemplate;
    private String baseUrl;

    public GrokIAServiceImpl(GrokIARepository repository) {
        super(repository);
    }

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
        this.baseUrl = "https://api.groq.com/openai/v1/chat/completions";
    }

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public GrokIA comunicar(GrokIA openIA) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiKey);

		Map<String, Object> body = Map.of(
				"model", "llama-3.3-70b-versatile", 
				"messages", List.of(Map.of("role", "user", 
								   		   "content", openIA.getMensagemRequest())), 
				"max_tokens", 500);

		ResponseEntity<Map> response = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(body, headers), Map.class);

		List<Map> choices = (List<Map>) response.getBody().get("choices");
		String respostaIA = (String) ((Map) choices.get(0).get("message")).get("content");

		openIA.setMensagemResponse(respostaIA);

		return super.inserir(openIA);
	}
}