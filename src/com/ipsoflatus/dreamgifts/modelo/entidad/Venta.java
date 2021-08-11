package com.ipsoflatus.dreamgifts.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ventas")
@NamedQueries({
    @NamedQuery(name = "Venta.findByTermLike", query = "SELECT v FROM Venta v WHERE v.cliente.rut LIKE :term OR UPPER(v.cliente.nombre) LIKE UPPER(:term) OR UPPER(v.cliente.apellido) LIKE UPPER(:term) OR UPPER(v.pack.nombre) LIKE UPPER(:term)"),
    @NamedQuery(name = "Venta.findMinDate", query = "SELECT MIN(v.fechaVenta) FROM Venta v"),
    @NamedQuery(name = "Venta.findMaxDate", query = "SELECT MAX(v.fechaVenta) FROM Venta v"),
    @NamedQuery(name = "Venta.findByDateBetween", query = "SELECT v FROM Venta v WHERE v.fechaVenta BETWEEN :desde AND :hasta")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "total")
    private Integer total;
    
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    
    @Column(name = "fecha_transferencia")
    @Temporal(TemporalType.DATE)
    private Date fechaTransferencia;
    
    @Column(name = "codigo_transferencia")
    private Integer codigoTransferencia;
    
    @Column(name = "nombre_destinatario")
    private String nombreDestinatario;
    
    @Column(name = "apellido_destinatario")
    private String apellidoDestinatario;
    
    @Column(name = "direccion_destinatario")
    private String direccionDestinatario;
    
    @Column(name = "telefono_destinatario")
    private String telefonoDestinatario;
    
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    
    @Column(name = "hora_entrega_inicial")
    @Temporal(TemporalType.TIME)
    private Date horaEntregaInicial;
    
    @Column(name = "hora_entrega_final")
    @Temporal(TemporalType.TIME)
    private Date horaEntregaFinal;
    
    @Column(name = "saludo")
    private String saludo;
    
    @ManyToOne
    @JoinColumn(name = "banco_id", referencedColumnName = "id")
    private Banco banco;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "comuna_id", referencedColumnName = "id")
    private Comuna comuna;
    
    @ManyToOne
    @JoinColumn(name = "estado_venta_id", referencedColumnName = "id")
    private EstadoVenta estadoVenta;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "pack_id", referencedColumnName = "id")
    private Pack pack;
    
    @ManyToOne
    @JoinColumn(name = "rrss_id", referencedColumnName = "id")
    private RedSocial redSocial;

    public Venta() {
    }

    public Venta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getHoraEntregaInicial() {
        return horaEntregaInicial;
    }

    public void setHoraEntregaInicial(Date horaEntregaInicial) {
        this.horaEntregaInicial = horaEntregaInicial;
    }

    public Date getHoraEntregaFinal() {
        return horaEntregaFinal;
    }

    public void setHoraEntregaFinal(Date horaEntregaFinal) {
        this.horaEntregaFinal = horaEntregaFinal;
    }

    public String getSaludo() {
        return saludo;
    }

    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public RedSocial getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venta[ id=" + id + " ]";
    }
    
}
