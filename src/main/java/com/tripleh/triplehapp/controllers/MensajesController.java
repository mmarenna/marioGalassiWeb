package com.tripleh.triplehapp.controllers;

import com.tripleh.triplehapp.entity.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tripleh.triplehapp.entity.Comentario;
import com.tripleh.triplehapp.services.ComentarioService;

@Controller
@RequestMapping("/comments")
public class MensajesController {

	@Autowired
	private ComentarioService comentarioService;

	
	@GetMapping("/comentarios")
	public String listarComentarios(Model model) {
		
		model.addAttribute("listcomentarios", comentarioService.getComentarios(model));
		return "views/comentarios";
	}

	@GetMapping("/comentarios/{id}")
	public String listarComentariosVendedor(@PathVariable(value = "id") Long id, Model model) {
		
		model.addAttribute("listcomentarios", comentarioService.getComentariosVendedor(id,model));
		return "views/comFiltVendedor";
	}

	@GetMapping("/comentarioformemp/{idRegistro}")
	public String comentFormularioEmp(@PathVariable(value = "idRegistro") Long id, Model model) {
//		LIST AND FORM LISTA LOS COMENTARIOS ACTUALES Y GENERA EL FORMULARIO
//		AGREGO AL MODEL EMP PARA SABER QUE VENGO DESDE EMPRESA
		model.addAttribute("emp", "emp");
		return comentarioService.listAndForm(id, model);
	}
	
	@GetMapping("/comentarioformven/{idRegistro}")
	public String comentFormularioVen(@PathVariable(value = "idRegistro") Long id, Model model) {
//		LIST AND FORM LISTA LOS COMENTARIOS ACTUALES Y GENERA EL FORMULARIO
//		AGREGO AL MODEL EMP PARA SABER QUE VENGO DESDE VENDEDOR
		model.addAttribute("ven", "ven");
		return comentarioService.listAndForm(id, model);
	}

	@PostMapping("/formguardaemp")
	public String saveComentarioemp(RedirectAttributes flash, @ModelAttribute(value = "comentario") Comentario comentario,
			 Model model) {

		//		GUARDA COMENTARIO
		Long idEmpresa = comentarioService.saveCommentemp(flash, comentario, model);
		return "redirect:/empresalistado/" + idEmpresa ;
	}
	
	@PostMapping("/formguardaven")
	public String saveComentarioven(RedirectAttributes flash, @ModelAttribute(value = "comentario") Comentario comentario,
			@ModelAttribute(value = "listComentarios") Comentario listComentarios, Model model) {

		//		GUARDA COMENTARIO
		Long idVendedor = comentarioService.saveCommentven(flash, comentario, model);
		return "redirect:/vendedores/" + idVendedor ;
	}

	@GetMapping("/archivar/{id}")
	public String archivarComentario(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		comentarioService.archivaComent(id,flash);
		return "redirect:/comments/comentarios/";
	}

	@GetMapping("/archivarcomentFiltrado/{id}")
	public String archivarComentarioFiltrado(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		Long nroVendedor = comentarioService.archivaComent(id, flash);
		return "redirect:/comments/comentarios/" + nroVendedor;
	}

	@GetMapping("/borrarcoment/{id}")
	public String deleteComment(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		comentarioService.deleteComment(id, flash);		
		return "redirect:/comments/comentarios";
	}

	@GetMapping("/comvendedor/{id}")
	public String getComentVendedor(@PathVariable(value = "id") Long id, Model model) {
		
		model.addAttribute("listcomentarios", comentarioService.getComentariosVendedor(id,model));
		return "views/comvendedor";
	}
/** ADMIN PUBLIC MENSAJES/
 *
 */
//		GUARDA CONTACTO
@PostMapping("/guardacontacto")
public String guardaContacto(RedirectAttributes flash, @ModelAttribute(value = "contacto") Contacto contacto, RedirectAttributes attributes, Model model) {
	attributes.addFlashAttribute("success", "Se envio el mensaje con exito!");
	comentarioService.guardaContacto(contacto);
	return "redirect:/inicio";
}

	@GetMapping("/contactos")
	public String listarContactos(Model model) {

		model.addAttribute("listacontactos", comentarioService.getContactos(model));
		return "views/listarcontactos";
	}

	@GetMapping("/eliminaContacto/{id}")
	public String borrarMensaje(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		comentarioService.deleteContacto(id, flash);
		return "redirect:/comments/contactos";
	}

}
