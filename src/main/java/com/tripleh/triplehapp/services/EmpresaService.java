package com.tripleh.triplehapp.services;

import java.util.List;

import com.tripleh.triplehapp.repository.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.tripleh.triplehapp.entity.Empresa;

@Service("empresaService")
public class EmpresaService {
	
	@Autowired
	private IEmpresaRepository empresaRepository;
	
	public List<Empresa> getEmpresas(){
		return (List<Empresa>) empresaRepository.findAll();
	}

}
