package com.crud.framework.service;

import java.util.List;

public interface GenericLeituraService<T, ID> {
    
	List<T> pesquisar();
	
	T pesquisarPorId(ID id);
	
}