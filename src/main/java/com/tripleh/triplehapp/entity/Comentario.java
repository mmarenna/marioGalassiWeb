package com.tripleh.triplehapp.entity;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long id_registro;
	

	@Column(name = "id_vendedor")
	private Long nroVendedor;
	
	@Column(name = "Localidad")
	private String localidad;
	
	@Column(name = "Vendedor")
	private String vendedor;
	
	@Column(name = "cliente_nombre")
	private String clienteNombre;
	
	@Column(name = "nombre_empresa")
	private String nombreEmpresa;
	
	@Column(name= "user_comment")
	private String userComment;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechacomment;
	
	@PrePersist
	public void prePersist() {
		fechacomment= new Date();
	}
	
	public Date getFechacomment() {
		return fechacomment;
	}

	public void setFechacomment(Date fechacomment) {
		this.fechacomment = fechacomment;
	}

	private String factura;
	
	private Double saldo;
	
	@Column(name = "importe_factura")
	private Double impFactura;
	
	@Column(name = "importe_aplicado")
	private Double impAplicado;
	
	@Column(name = "fecha_factura")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyy")
	private Date fechaFactura;

	private boolean archivado;
	
	

	private String comment;
	
	
//	CONSTRUCTORES GETTERS Y SETTERS
	public Comentario() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Comentario(Long id, Long id_registro, Long nroVendedor, String localidad, String vendedor, String clienteNombre,
		String nombreEmpresa, String userComment, Date fechacomment, String factura, Double saldo, Double impFactura,
		Double impAplicado, Date fechaFactura, boolean archivado, String comment) {
	super();
	this.id = id;
	this.id_registro = id_registro;
	this.nroVendedor = nroVendedor;
	this.localidad = localidad;
	this.vendedor = vendedor;
	this.clienteNombre = clienteNombre;
	this.nombreEmpresa = nombreEmpresa;
	this.userComment = userComment;
	this.fechacomment = fechacomment;
	this.factura = factura;
	this.saldo = saldo;
	this.impFactura = impFactura;
	this.impAplicado = impAplicado;
	this.fechaFactura = fechaFactura;
	this.archivado = archivado;
	this.comment = comment;
}

	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserComment() {
		return userComment;
	}


	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	public boolean isArchivado() {
		return archivado;
	}



	public void setArchivado(boolean archivado) {
		this.archivado = archivado;
	}

	public Long getId_registro() {
		return id_registro;
	}


	public void setId_registro(Long id_registro) {
		this.id_registro = id_registro;
	}


	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNroVendedor() {
		return nroVendedor;
	}

	public void setNroVendedor(Long nroVendedor) {
		this.nroVendedor = nroVendedor;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public Double getImpFactura() {
		return impFactura;
	}

	public void setImpFactura(Double impFactura) {
		this.impFactura = impFactura;
	}

	public Double getImpAplicado() {
		return impAplicado;
	}

	public void setImpAplicado(Double impAplicado) {
		this.impAplicado = impAplicado;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	
}
