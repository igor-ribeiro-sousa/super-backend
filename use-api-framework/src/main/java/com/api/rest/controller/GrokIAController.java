package com.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.entidade.GrokIA;
import com.api.rest.entidade.dto.GrokIADTO;
import com.api.rest.service.GrokIAService;
import com.crud.framework.config.GenericMapper;
import com.crud.framework.controller.GenericLeituraController;
import com.crud.framework.response.ResponseAPI;


@RestController
@RequestMapping("/grok")
public class GrokIAController extends GenericLeituraController<GrokIA, GrokIADTO, Long> {
	
	private GrokIAService service;
	
    public GrokIAController(GrokIAService service, GenericMapper mapper) {
        super(service, mapper, GrokIA.class, GrokIADTO.class);
        this.service = (GrokIAService) service;
    }
    
    
    @PostMapping
	public final ResponseEntity<ResponseAPI<GrokIADTO>> integracao(@RequestBody GrokIADTO OpenIADTO) {
		
    	GrokIA entidade = this.genericMapper.convertToEntity(OpenIADTO, GrokIA.class);
		
    	GrokIA resultado = this.service.comunicar(entidade);

    	GrokIADTO resultadoDTO = this.genericMapper.convertToDTO(resultado, GrokIADTO.class);

		ResponseAPI<GrokIADTO> response = new ResponseAPI<>();
		response.setData(resultadoDTO);

		return ResponseEntity.ok(response);
		
	}
    
    
}