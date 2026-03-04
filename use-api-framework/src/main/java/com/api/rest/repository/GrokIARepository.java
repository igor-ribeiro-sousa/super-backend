package com.api.rest.repository;

import org.springframework.stereotype.Repository;

import com.api.rest.entidade.GrokIA;
import com.crud.framework.repository.GenericRepository;

@Repository
public interface GrokIARepository extends GenericRepository<GrokIA, Long> {}
