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

@Entity
@Table(name = "servicios")
public class Servicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "detalle_servicio_id")
	private DetalleServicio detalles;
	
	@Column(name = "estado", length = 10, nullable = false)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "transportista_id")
	private Transportista transportista;
	
	@Column(name = "precio_solicitado")
	private Float precioSolicitado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetalleServicio getDetalles() {
		return detalles;
	}

	public void setDetalles(DetalleServicio detalles) {
		this.detalles = detalles;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
