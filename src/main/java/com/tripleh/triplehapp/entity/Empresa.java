package com.tripleh.triplehapp.entity;

import javax.persistence.*;

@Entity
public class Empresa {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_provgalassi;
	
	private String nombre;

	@Column(name = "plazo_vencimiento")
	private Long plazoVencimiento;

	private String direccion;

	private String localidad;

	private String provincia;

	private String telefono;

	private String cuit;

	@Column(name = "codigo_postal")
	private String codPostal;

	private String email;
	
//	CONSTRUCTORES GETTERS Y SETTERS
	
	public Empresa() {
		
	}


	public Empresa(Long id_provgalassi, String nombre, Long plazoVencimiento, String direccion, String localidad,
		String provincia, String telefono, String cuit, String codPostal, String email) {
	super();
	this.id_provgalassi = id_provgalassi;
	this.nombre = nombre;
	this.plazoVencimiento = plazoVencimiento;
	this.direccion = direccion;
	this.localidad = localidad;
	this.provincia = provincia;
	this.telefono = telefono;
	this.cuit = cuit;
	this.codPostal = codPostal;
	this.email = email;
}


	public Long getId_provgalassi() {
		return id_provgalassi;
	}

	public void setId_provgalassi(Long id_provgalassi) {
		this.id_provgalassi = id_provgalassi;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getPlazoVencimiento() {
		return plazoVencimiento;
	}

	public void setPlazoVencimiento(Long plazoVencimiento) {
		this.plazoVencimiento = plazoVencimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
