package com.tripleh.triplehapp.controllers;

import com.tripleh.triplehapp.entity.Contacto;
import com.tripleh.triplehapp.entity.Oferta;
import com.tripleh.triplehapp.services.DBFileStorageService;
import com.tripleh.triplehapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tripleh.triplehapp.exception.ErrorResponse;
import com.tripleh.triplehapp.exception.TripleHException;
import com.tripleh.triplehapp.services.TripleHService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	// INYECTO EL SERVICE
	@Autowired
	private TripleHService tripleHService;

	@Autowired
	private UserService userService;

	@Autowired
	private DBFileStorageService dbFileStorageService;

	/**CONTENIDO PUBLICO*/
    @GetMapping("/")
    public String Default(Model model) {
        return "views/inicio";
    }

	@GetMapping("/inicio")
	public String Home(Model model){
		model.addAttribute("titulo", "Fecha de vigencia promocional:  ");
		return "views/inicio";
	}

	@GetMapping("/ofertas")
	public String Ofertas(Model model){
		model.addAttribute("listaofertas", tripleHService.getOfertas(model));
		model.addAttribute("titulo", "Fecha de vigencia promocional:  ");

		return "views/listaofertas";
	}
	@GetMapping("/quienes")
	public String QuienesSomos(Model model){return "views/quienes";
	}

	@GetMapping("/contacto")
	public String Contacto(Model model){
		model.addAttribute("contacto", new Contacto());
		return "views/contacto";
	}

	@GetMapping("/zona")
	public String Zona(Model model){
		return "views/zona";
	}

	@GetMapping("/marcas")
	public String Marcas(Model model){
		return "views/marcas";
	}

	/** CONTENIDO ADMIN*/
	@GetMapping("/empresas")
	public String getEmpresas(Model model) {

		tripleHService.getEmpresas(model);
		return "views/datosempresas";
	}

	@GetMapping("/admin")
	public String HomeAdmin(Model model) {

		return tripleHService.Home(model);
	}

	@GetMapping("/home")
	public String HomeAdmin2(Model model) {

		return tripleHService.Home(model);
	}

	@GetMapping("/deleteall")
	public String eliminaTodo() {
		this.tripleHService.deleteAll();
		return "redirect:/home";
	}

	// LISTAR PAGINADO
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/listarclientes")
	public String getClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model)
			throws TripleHException {

		tripleHService.getClientesPaginado(page, model);
		return "views/listadogral";
	}

	// VENDEDORES
	@GetMapping("/vendedor")
	public String listadoVendedores(Model model) throws TripleHException {
		
		tripleHService.listadoVendedores(model);
		return "views/vendedores";
	}

	// DETALLE DEL VENDEDOR
	@GetMapping("/vendedor/{id}")
	public String detalleVendedor(Model model, @PathVariable(value = "id") Long id) throws TripleHException {
		
		tripleHService.detalleVendedor(model,id);
		return "views/vendedores";
	}

	// VENDEDORES
	@GetMapping("/vendedores/{id}")
	public String registroVendedor(@RequestParam(name = "page", defaultValue = "0") int page,
			@PathVariable(value = "id") Long id, Model model)  {

		tripleHService.registroVendedor(page, id, model);
		return "views/listadoVendedor";
	}
	
//	EMPRESA
	@GetMapping("/empresalistado/{id}")
	public String registroEmpresa(@PathVariable(value = "id") Long id,Model model) {
		
		tripleHService.registrosEmpresa(id,model);
		return "views/listEmpresa";
	}

	@GetMapping("/subirimagenes")
	public String imagenesAdmin(Model model) {

		model.addAttribute("files", dbFileStorageService.getFiles(model));
		return "views/uploadimagenes";
	}

	@GetMapping("/eliminarfile/{id}")
	public String deleteFile(@PathVariable String id,Model model) {

		dbFileStorageService.deleteFile(id);
		return "redirect:/subirimagenes";
	}

	@GetMapping("/formofertas")
	public String listarContactos(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username", user.getUsername());
		model.addAttribute("listaofertas", tripleHService.getOfertas(model));
		model.addAttribute("oferta", new Oferta());
		return "views/formofertas";
	}

	@PostMapping("/guardaroferta")
	public String guardaContacto(RedirectAttributes flash, @ModelAttribute(value = "oferta") Oferta oferta, RedirectAttributes attributes, Model model) {
		attributes.addFlashAttribute("success", "Se guardo la oferta con exito!");
		tripleHService.guardaOferta(oferta);
		return "redirect:/formofertas";
	}

	@GetMapping("/eliminaOferta/{id}")
	public String borrarMensaje(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		tripleHService.deleteOferta(id, flash);
		return "redirect:/formofertas";
	}

	// MANEJADOR DE EXEPCIONES
	@ExceptionHandler(TripleHException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(TripleHException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getHttpStatus().value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, ex.getHttpStatus());
	}
}
