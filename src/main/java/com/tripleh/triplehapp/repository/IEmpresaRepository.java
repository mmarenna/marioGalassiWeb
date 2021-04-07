package com.tripleh.triplehapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tripleh.triplehapp.entity.Empresa;


@Repository("empresaRepository")
public interface IEmpresaRepository extends CrudRepository<Empresa, Long> {

}
