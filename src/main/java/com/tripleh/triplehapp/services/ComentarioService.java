package com.tripleh.triplehapp.services;

import java.util.List;

import com.tripleh.triplehapp.entity.Contacto;
import com.tripleh.triplehapp.repository.IComentarioRepository;
import com.tripleh.triplehapp.repository.IContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tripleh.triplehapp.entity.Comentario;
import com.tripleh.triplehapp.entity.ListadoGral;

@Service("comentarioService")
public class ComentarioService {

	@Autowired
	private IComentarioRepository comentarioRepository;

	@Autowired
	private IContactoRepository contactoRepository;

	@Autowired
	private TripleHService tripleHService;

	public void setUsuarioLogged(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
	}

	public List<Comentario> getComentarios(Model model) {
		this.setUsuarioLogged(model);
		return (List<Comentario>) comentarioRepository.findAllByOrderByVendedorAscClienteNombreAscFacturaAscFechacommentAsc();
	}

	// FORMULARIO Y LISTADO DE COMENTARIOS
	public String listAndForm(Long id, Model model) {
		// obtengo el usuario conectado para guardar en el registro
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ListadoGral regDtoGral = tripleHService.getRegistroListGral(id);

		List<Comentario> listComentarios = this.getComentarios(model);

		if (regDtoGral == null) {
			return "redirect:/vendedores";
		}

		Comentario comentario = new Comentario();

		comentario.setLocalidad(regDtoGral.getLocalidad());
		comentario.setNroVendedor(regDtoGral.getNroVendedor());
		comentario.setVendedor(regDtoGral.getVendedor());
		comentario.setClienteNombre(regDtoGral.getClienteNombre());
		comentario.setNombreEmpresa(regDtoGral.getNombreEmpresa());
		comentario.setFactura(regDtoGral.getFactura());
		comentario.setSaldo(regDtoGral.getSaldo());
		comentario.setImpFactura(regDtoGral.getImpFactura());
		comentario.setImpAplicado(regDtoGral.getImpAplicado());
		comentario.setFechaFactura(regDtoGral.getFechaFactura());
		comentario.setUserComment(user.getUsername());
		comentario.setId_registro(regDtoGral.getId_registros());

		// model.addAttribute("username", user.getUsername());
		model.addAttribute("id_empresa", regDtoGral.getIdEmpresa());
		model.addAttribute("comentario", comentario);
		model.addAttribute("listComentarios", listComentarios);

		return "views/comentarioform";

	}

	public Long saveCommentemp(RedirectAttributes flash, Comentario comentario, Model model) {
		ListadoGral listGral = tripleHService.getRegistroListGral(comentario.getId_registro());
		listGral.setComentario(true);
		tripleHService.guardaRegistro(listGral);
		this.guarda(comentario);
		flash.addFlashAttribute("success", "Comentario guardado con exito!");
		return listGral.getIdEmpresa();
	}
	
	public Long saveCommentven(RedirectAttributes flash, Comentario comentario,  Model model) {
		ListadoGral listGral = tripleHService.getRegistroListGral(comentario.getId_registro());
		listGral.setComentario(true);
		tripleHService.guardaRegistro(listGral);
		this.guarda(comentario);
		flash.addFlashAttribute("success", "Comentario guardado con exito!");
		return comentario.getNroVendedor();
	}

	// GUARDAR COMENTARIO
	public Comentario guarda(Comentario newComentario) {
		return (Comentario) comentarioRepository.save(newComentario);
	}

	// ARCHIVA UN COMENTARIO
	public Long archivaComent(Long id, RedirectAttributes flash) {

		Comentario coment = comentarioRepository.findById(id).get();
		coment.setArchivado(!coment.isArchivado());
		comentarioRepository.save(coment);
		flash.addFlashAttribute("success", "Comentario cambio de estado!!");
		return coment.getNroVendedor();
	}

	public List<Comentario> getComentariosVendedor(Long idVendedor, Model model) {
		this.setUsuarioLogged(model);
		return comentarioRepository.findAllByNroVendedorOrderByClienteNombreAscFacturaAscFechacommentAsc(idVendedor);
	}

	public void deleteComment(Long idComentario, RedirectAttributes flash) {
		comentarioRepository.deleteById(idComentario);
		flash.addFlashAttribute("success", "Comentario eliminado con exito!");
	}

	public void deleteContacto(Long idContacto, RedirectAttributes flash) {
		contactoRepository.deleteById(idContacto);
		flash.addFlashAttribute("danger", "Contacto eliminado con exito!");
	}

	public List<Contacto> getContactos(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		return (List<Contacto>) contactoRepository.findAll();
	}

	public void guardaContacto(Contacto contacto){
		contactoRepository.save(contacto);
	}
}
