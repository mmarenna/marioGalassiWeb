package com.tripleh.triplehapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tripleh.triplehapp.entity.Vendedor;
@Repository("vendedorRepository")
public interface IVendedoresRepository extends CrudRepository<Vendedor, Long> {
	
}
