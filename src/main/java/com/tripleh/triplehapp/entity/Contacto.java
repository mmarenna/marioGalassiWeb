package com.tripleh.triplehapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  mensaje;
    private Date fecha;
    private String empresa;
    private String telefono;
    private  String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @PrePersist
    protected void onCreate() {
        fecha = new Date();
    }

    public Contacto(String mensaje, Date fecha, String telefono, String empresa, String email) {
        this.mensaje = mensaje;
        this.empresa= empresa;
        this.fecha = fecha;
        this.telefono = telefono;
        this.email = email;
    }

    public Contacto() {
    }
}
