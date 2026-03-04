package com.api.rest.adapter.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.api.rest.adapter.AdapterGroqIA;

@Component
public class AdapterGroqIAImpl implements AdapterGroqIA{
	
    private static final Logger LOG = LoggerFactory.getLogger(AdapterGroqIAImpl.class);

    @Value("${groq.api.key}")
    private String apiKey;
    
    @Value("${groq.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String enviarMensagem(String mensagem) {

        long inicio = System.currentTimeMillis();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", List.of(
                        Map.of("role", "user", "content", mensagem)
                ),
                "max_tokens", 500
        );

        LOG.info("AdapterGroqIAImpl.enviarMensagem - Entrada. body={}", body);

        ResponseEntity<Map> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                Map.class
        );

        List<Map> choices = (List<Map>) response.getBody().get("choices");

        String resposta = (String) ((Map) choices.get(0).get("message")).get("content");

        long tempo = System.currentTimeMillis() - inicio;

        LOG.info("AdapterGroqIAImpl.enviarMensagem - Saída. status={} tempoMs={} resposta={}", response.getStatusCode(), tempo, resposta);

        return resposta;
    }
}