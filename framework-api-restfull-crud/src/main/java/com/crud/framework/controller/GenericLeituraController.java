package com.crud.framework.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crud.framework.config.GenericMapper;
import com.crud.framework.response.ResponseAPI;
import com.crud.framework.service.GenericService;

public abstract class GenericLeituraController<T, D, ID> {

	protected final GenericService<T, ID> service;
	protected final GenericMapper genericMapper;
	
    private final Class<D> dtoClass;

	public GenericLeituraController(GenericService<T, ID> service, GenericMapper genericMapper, Class<T> entityClass, Class<D> dtoClass) {
        this.service = service;
        this.genericMapper = genericMapper;
        this.dtoClass = dtoClass;
    }
	
	@GetMapping
	public final ResponseEntity<ResponseAPI<List<D>>> pesquisar() {

		List<T> entities = this.service.pesquisar();
		
		List<D> resultados = this.genericMapper.convertListEntityToListDTO(entities, dtoClass);

		ResponseAPI<List<D>> response = new ResponseAPI<>();
		response.setData(resultados);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public final ResponseEntity<ResponseAPI<D>> pesquisarPorId(@PathVariable("id") ID id) {

		T entidade = this.service.pesquisarPorId(id);

		D resultado = this.genericMapper.convertToDTO(entidade, dtoClass);
		
		ResponseAPI<D> response = new ResponseAPI<>();
		response.setData(resultado);

		return ResponseEntity.ok(response);
		
	}
}