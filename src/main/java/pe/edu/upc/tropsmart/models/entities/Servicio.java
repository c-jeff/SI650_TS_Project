package pe.edu.upc.tropsmart.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "servicios")
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "detalle_servicio_id")
	private DetalleServicio detalle;
	
	@Transient
	private Integer detalleServicioId;
	
	@Column(name = "estado", length = 10, nullable = false)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Transient
	private Integer clienteId;
	
	@ManyToOne
	@JoinColumn(name = "transportista_id")
	private Transportista transportista;
	
	@Transient
	private Integer transportistaId;
	
	@Column(name = "precio_solicitado")
	private Float precioSolicitado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public DetalleServicio getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleServicio detalle) {
		this.detalle = detalle;
	}

	public Integer getDetalleServicioId() {
		return detalleServicioId;
	}

	public void setDetalleServicioId(Integer detalleServicioId) {
		this.detalleServicioId = detalleServicioId;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getTransportistaId() {
		return transportistaId;
	}

	public void setTransportistaId(Integer transportistaId) {
		this.transportistaId = transportistaId;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Float getPrecioSolicitado() {
		return precioSolicitado;
	}

	public void setPrecioSolicitado(Float precioSolicitado) {
		this.precioSolicitado = precioSolicitado;
	}
}
