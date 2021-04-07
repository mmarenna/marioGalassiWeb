package com.tripleh.triplehapp.services;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.tripleh.triplehapp.entity.*;
import com.tripleh.triplehapp.exception.TripleHException;
import com.tripleh.triplehapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.tripleh.triplehapp.utils.paginator.PageRender;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service("listadoService")
public class TripleHService {

	@Autowired
	private ITriplehRepository triplehRepository;

	@Autowired
	private IEmpresaRepository empresaRepository;

	@Autowired
	private IOfertaRepository ofertaRepository;

	@Autowired
	private IVendedoresRepository vendedorRepository;

	@Autowired
	private IComentarioRepository comentarioRepository;

	@Autowired
	private EmpresaService empresaService;

	public String Home(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		return "views/home";
	}

	// GET REGISTRO
	public ListadoGral getRegistroListGral(Long idVendedor) {

		return triplehRepository.findById(idVendedor).get();
	}

	public void getClientesPaginado(int page, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());

		Pageable pageRequest = new PageRequest(page, 10);
		Page<ListadoGral> listado = this.getListGral(pageRequest);
		PageRender<ListadoGral> pageRender = new PageRender<>("/listarclientes", listado);
		model.addAttribute("page", pageRender);
		model.addAttribute("titulo", "Listado de registros");
		model.addAttribute("listado", listado);
	}

	public void listadoVendedores(Model model) throws TripleHException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());

		// LISTADO DE VENDEDORES EN BBDD
		List<Vendedor> vendedores = this.getVendedores();
		model.addAttribute("vendedores", vendedores);
		model.addAttribute("titulo", "Listado de vendedores");
	}

	
	public void registroVendedor(int page, Long id, Model model) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());

		List<ListadoGral> listado = this.listaVendedor(id);

		model.addAttribute("listado", listado);
//		model.put("listado", listado);
	}
	
	public void detalleVendedor(Model model, @PathVariable(value = "id") Long id) throws TripleHException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());

		// DETALLE VENDEDOR
		Vendedor vendedor = this.getVendedor(id);
		model.addAttribute("vendedor", vendedor);
		// LISTA VENDEDORES
		List<Vendedor> vendedores = this.getVendedores();
		model.addAttribute("vendedores", vendedores);
		model.addAttribute("titulo", "Listado de vendedores");
	}

	public void getEmpresas(Model model) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		model.addAttribute("listEmpresas", empresaService.getEmpresas());
	}
//	LISTADO POR EMPRESA
	public void registrosEmpresa(Long idempresa, Model model){
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		
		
		List<ListadoGral> listadoParcial =  triplehRepository.findAllByIdEmpresaOrderByLocalidadAscIdClienteGalassiAscVendedorAsc(idempresa);
		
		List<ListadoGral> listadoParcial1 = estadoFactura(listadoParcial);
		List<ListadoGral> listadoFinal = saldoUnificados(listadoParcial1);
		
		model.addAttribute("listempresa", listadoFinal);

	}

	// LISTADO POR VENDEDOR
	public List<ListadoGral> listaVendedor(Long idVendedor) {
		
		List<ListadoGral> listadoParcial = triplehRepository.findAllByNroVendedorOrderByLocalidadAscIdClienteGalassiAscIdEmpresaAsc(idVendedor);
		List<ListadoGral> listadoParcial1 = estadoFactura(listadoParcial);
		List<ListadoGral> listadoFinal = saldoUnificados(listadoParcial1);
		return listadoFinal;
	}

	// OBTENIENDO ESTADO DE VENCIMIENTO DE FACTURA
	public List<ListadoGral> estadoFactura(List<ListadoGral> listadoParcial) {

		List<Empresa> empresas = (List<Empresa>) empresaRepository.findAll();

		for (ListadoGral registro : listadoParcial) {

			Date fechaActual = new Date();
			Date fechaFactura = registro.getFechaFactura();
			Long days = getDateDiff(fechaFactura, fechaActual, TimeUnit.DAYS);

			for (Empresa empresa : empresas) {
				if (registro.getIdEmpresa() == empresa.getId_provgalassi()) {

					if (empresa.getPlazoVencimiento() == 90) {
						if (days <= 100) {
							registro.setEstadoFactura((long) 1);
						} else if (100 < days && days <= 115) {
							registro.setEstadoFactura((long) 2);
						} else {
							registro.setEstadoFactura((long) 3);
						}
					} else if (empresa.getPlazoVencimiento() == 10) {
						if (days <= 10) {
							registro.setEstadoFactura((long) 1);
						} else if (10 < days && days <= 25) {
							registro.setEstadoFactura((long) 2);
						} else {
							registro.setEstadoFactura((long) 3);
						}
					} else {
						if (days <= 40) {
							registro.setEstadoFactura((long) 1);
						} else if (40 < days && days <= 55) {
							registro.setEstadoFactura((long) 2);
						} else {
							registro.setEstadoFactura((long) 3);
						}
					}
				}
			}
		}
		return listadoParcial;
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	// SALDOS UNIFICADOS
	@SuppressWarnings("null")
	public List<ListadoGral> saldoUnificados(List<ListadoGral> listParcial) {

		for (ListadoGral reg : listParcial) {

			List<ListadoGral> aux = listParcial;
			double sald = 0;
			for (ListadoGral regaux : aux) {
				if (reg.equals(regaux) && !aux.isEmpty()) {
					sald += regaux.getSaldo();
				}
			}
			reg.setSaldoUnificado((double) sald);
		}
		return listParcial;
	}

	public Vendedor getVendedor(Long id) {
		return vendedorRepository.findById(id).get();
	}

	public List<Vendedor> getVendedores() throws TripleHException {
		List<Vendedor> listaVendedores = (List<Vendedor>) vendedorRepository.findAll();
		if (listaVendedores == null) {
			throw new TripleHException("No se pudo cargar los vendedores", null, HttpStatus.NO_CONTENT);
		}
		return (List<Vendedor>) listaVendedores;
	}

	public List<ListadoGral> getListWithoutPaging() {
		return (List<ListadoGral>) triplehRepository.findAll();
	}

	// ELIMINO TODOS LOS REGISTROS!!!!!!!
	public void deleteAll() {
		triplehRepository.deleteAll();
	}

	// LISTAR ORDENADO GRAL
	// @Query(value = "SELECT * FROM dbgalassi.dtosgenerales order by id_vendedor,
	// Localidad, id_cliente_galassi, id_empresa;", nativeQuery = true)
	// public List<TablaGral> findAllOrdenado();
	public Page<ListadoGral> getListGral(Pageable pageable) {
		return (Page<ListadoGral>) triplehRepository
				.findAllByOrderByNroVendedorAscLocalidadAscIdClienteGalassiAscIdEmpresaAsc(pageable);
	}

	public Page<ListadoGral> getListVendedor(Pageable pageable, Long idVendedor) throws TripleHException {

		Page<ListadoGral> listVendedores = (Page<ListadoGral>) triplehRepository
				.findAllByNroVendedorOrderByLocalidadAscIdClienteGalassiAscIdEmpresaAsc(pageable, idVendedor);
		if (listVendedores == null) {
			throw new TripleHException("No se pudo cargar la lista de registros del vendedor!", null,
					HttpStatus.NO_CONTENT);
		}

		return (Page<ListadoGral>) listVendedores;
	}

	public ListadoGral guardaRegistro(ListadoGral registro) {
		return triplehRepository.save(registro);
	}

	public List<Comentario> listComentarios() {
		return (List<Comentario>) comentarioRepository.findAll();
	}

	public void guardaOferta(Oferta oferta){
		ofertaRepository.save(oferta);
	}

	public List<Oferta> getOfertas(Model model){
		return (List<Oferta>) ofertaRepository.findAll();
	}

	public void deleteOferta(Long idOferta, RedirectAttributes flash) {
		ofertaRepository.deleteById(idOferta);
		flash.addFlashAttribute("danger", "Oferta eliminado con exito!");
	}
}
