package com.tripleh.triplehapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tripleh.triplehapp.entity.ListadoGral;

@Repository("triplehRepository")
public interface ITriplehRepository extends PagingAndSortingRepository<ListadoGral, Long> {

	// LISTAR ORDENADO TRAYENDO UN SOLO VENDEDOR
		public Page<ListadoGral> findAllByNroVendedorOrderByLocalidadAscIdClienteGalassiAscIdEmpresaAsc(Pageable pageable,Long idvendedor);

		public List<ListadoGral> findAllByNroVendedorOrderByLocalidadAscIdClienteGalassiAscIdEmpresaAsc(Long idvendedor);
		
		public List<ListadoGral> findAllByIdEmpresaOrderByLocalidadAscIdClienteGalassiAscVendedorAsc(Long idempresa);
		
		//LISTAR ORDENADO GRAL
//		@Query(value = "SELECT * FROM dbgalassi.dtosgenerales order by id_vendedor, Localidad, id_cliente_galassi, id_empresa;", nativeQuery = true)
//		public List<TablaGral> findAllOrdenado();
		public Page<ListadoGral> findAllByOrderByNroVendedorAscLocalidadAscIdClienteGalassiAscIdEmpresaAsc(Pageable pageable);

		public void  deleteAll();

		public void deleteByNombreEmpresa(String empresa);
		
}
