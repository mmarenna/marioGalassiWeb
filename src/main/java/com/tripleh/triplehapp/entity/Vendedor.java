package com.tripleh.triplehapp.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "vendedor")
public class Vendedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_listado")
	private Long idEnListado;
	private String nombre;
	private String description;
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String apellido;
	private String telefono;
	public Vendedor(Long id, Long idEnListado, String nombre, String apellido, String telefono, String description, String email) {
		super();
		this.id = id;
		this.idEnListado = idEnListado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.description = description;
		this.telefono = telefono;
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	private String email;

	public Vendedor() {
		
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdEnListado() {
		return idEnListado;
	}

	public void setIdEnListado(Long idEnListado) {
		this.idEnListado = idEnListado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
