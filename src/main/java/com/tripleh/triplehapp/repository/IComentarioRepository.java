package com.tripleh.triplehapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tripleh.triplehapp.entity.Comentario;

@Repository("comentarioRepository")
public interface IComentarioRepository extends CrudRepository<Comentario, Long >{
	
	public List<Comentario> findAllByNroVendedorOrderByClienteNombreAscFacturaAscFechacommentAsc(Long id);
	
	public List<Comentario> findAllByOrderByVendedorAscClienteNombreAscFacturaAscFechacommentAsc();

}
