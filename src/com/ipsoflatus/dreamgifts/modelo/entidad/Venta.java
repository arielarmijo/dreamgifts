package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.sql.Date;
import java.sql.Time;

public class Venta {

    private Integer id;
    private Integer clienteId;
    private Integer total;
    private Date fechaVenta;
    private Date fechaTransferencia;
    private Integer codigoTransferencia;
    private Integer bancoId;
    private String nombreDestinatario;
    private String apellidoDestinatario;
    private String direccionDestinatario;
    private Integer comunaId;
    private String telefonoDestinatario;
    private Date fechaEntrega;
    private Time horaEntregaInicial;
    private Time horaEntregaFinal;
    private String saludo;
    private Integer rrssId;
    private Integer estadoVentaId;
    private Integer packId;

    public Venta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Date getFechaTransferencia() {
        return fechaTransferencia;
    }

    public void setFechaTransferencia(Date fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    public Integer getCodigoTransferencia() {
        return codigoTransferencia;
    }

    public void setCodigoTransferencia(Integer codigoTransferencia) {
        this.codigoTransferencia = codigoTransferencia;
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getApellidoDestinatario() {
        return apellidoDestinatario;
    }

    public void setApellidoDestinatario(String apellidoDestinatario) {
        this.apellidoDestinatario = apellidoDestinatario;
    }

    public String getDireccionDestinatario() {
        return direccionDestinatario;
    }

    public void setDireccionDestinatario(String direccionDestinatario) {
        this.direccionDestinatario = direccionDestinatario;
    }

    public Integer getComunaId() {
        return comunaId;
    }

    public void setComunaId(Integer comunaId) {
        this.comunaId = comunaId;
    }

    public String getTelefonoDestinatario() {
        return telefonoDestinatario;
    }

    public void setTelefonoDestinatario(String telefonoDestinatario) {
        this.telefonoDestinatario = telefonoDestinatario;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Time getHoraEntregaInicial() {
        return horaEntregaInicial;
    }

    public void setHoraEntregaInicial(Time horaEntregaInicial) {
        this.horaEntregaInicial = horaEntregaInicial;
    }

    public Time getHoraEntregaFinal() {
        return horaEntregaFinal;
    }

    public void setHoraEntregaFinal(Time horaEntregaFinal) {
        this.horaEntregaFinal = horaEntregaFinal;
    }

    public String getSaludo() {
        return saludo;
    }

    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    public Integer getRrssId() {
        return rrssId;
    }

    public void setRrssId(Integer rrssId) {
        this.rrssId = rrssId;
    }

    public Integer getEstadoVentaId() {
        return estadoVentaId;
    }

    public void setEstadoVentaId(Integer estadoVentaId) {
        this.estadoVentaId = estadoVentaId;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }
    
}
