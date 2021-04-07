package com.tripleh.triplehapp.entity;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dtosgenerales")
public class ListadoGral {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_registros;

	@Column(name = "id_vendedor")
	private Long nroVendedor;

	@Column(name = "Localidad")
	private String localidad;

	@Column(name = "Vendedor")
	private String vendedor;

	private boolean comentario;


	@Column(name = "cliente_nombre")
	private String clienteNombre;

	@Column(name = "id_cliente_galassi")
	private Long idClienteGalassi;

	@Column(name = "id_empresa")
	private Long idEmpresa;

	@Column(name = "rec_aplicacion")
	private Integer recAplicacion;

	@Column(name = "tipo_comprobante")
	private String tipoComprobante;

	private String factura;

	@Column(name = "fecha_factura")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yy")
	private Date fechaFactura;

	@Column(name = "importe_factura")
	private Double impFactura;

	@Column(name = "estado_factura")
	private Long estadoFactura;
	
	public Long getEstadoFactura() {
		return estadoFactura;
	}

	public void setEstadoFactura(Long days) {
		this.estadoFactura = days;
	}

	// IMPORTE DESCONTADO DE LA DEUDA
	@Column(name = "importe_aplicado")
	private Double impAplicado;
	@Column(name = "saldo_unificado")
	private Double saldoUnificado;


	private Double saldo;

	@Column(name = "fecha_listado")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaListado;

	@Column(name = "ZONA", nullable = true)
	private Integer zona;

	@Column(name = "nombre_empresa")
	private String nombreEmpresa;

	// GETTERS SETTERS Y CONSTRUCTORES//////////////////////////////////////
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListadoGral other = (ListadoGral) obj;
		if (clienteNombre == null) {
			if (other.clienteNombre != null)
				return false;
		} else if (!clienteNombre.equals(other.clienteNombre))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (nombreEmpresa == null) {
			if (other.nombreEmpresa != null)
				return false;
		} else if (!nombreEmpresa.equals(other.nombreEmpresa))
			return false;
		return true;
	}

	public Double getSaldoUnificado() {
		return saldoUnificado;
	}

	public void setSaldoUnificado(Double saldoUnificado) {
		this.saldoUnificado = saldoUnificado;
	}
	
	
	public boolean isComentario() {
		return comentario;
	}

	public void setComentario(boolean comentario) {
		this.comentario = comentario;
	}

	

	public ListadoGral(Long id_registros, Long nroVendedor, String localidad, String vendedor, boolean comentario,
			String clienteNombre, Long idClienteGalassi, Long idEmpresa, Integer recAplicacion, String tipoComprobante,
			String factura, Date fechaFactura, Double impFactura, Long estadoFactura, Double impAplicado,
			Double saldoUnificado, Double saldo, Date fechaListado, Integer zona, String nombreEmpresa) {
		super();
		this.id_registros = id_registros;
		this.nroVendedor = nroVendedor;
		this.localidad = localidad;
		this.vendedor = vendedor;
		this.comentario = comentario;
		this.clienteNombre = clienteNombre;
		this.idClienteGalassi = idClienteGalassi;
		this.idEmpresa = idEmpresa;
		this.recAplicacion = recAplicacion;
		this.tipoComprobante = tipoComprobante;
		this.factura = factura;
		this.fechaFactura = fechaFactura;
		this.impFactura = impFactura;
		this.estadoFactura = estadoFactura;
		this.impAplicado = impAplicado;
		this.saldoUnificado = saldoUnificado;
		this.saldo = saldo;
		this.fechaListado = fechaListado;
		this.zona = zona;
		this.nombreEmpresa = nombreEmpresa;
	}

	public Long getId_registros() {
		return id_registros;
	}

	public void setId_registros(Long id_registros) {
		this.id_registros = id_registros;
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

	public Long getIdClienteGalassi() {
		return idClienteGalassi;
	}

	public void setIdClienteGalassi(Long idClienteGalassi) {
		this.idClienteGalassi = idClienteGalassi;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getRecAplicacion() {
		return recAplicacion;
	}

	public void setRecAplicacion(Integer recAplicacion) {
		this.recAplicacion = recAplicacion;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Date getFechaListado() {
		return fechaListado;
	}

	public void setFechaListado(Date fechaListado) {
		this.fechaListado = fechaListado;
	}

	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona = zona;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public ListadoGral() {

	}

}
