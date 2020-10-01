package pe.edu.upc.tropsmart.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "distritos")
public class Distrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "provincia", length = 30, nullable = false)
	private String provincia;
	
	@OneToMany(mappedBy = "distrito")
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "distrito")
	private List<Transportista> transportistas;
	
	public Distrito() {
		clientes = new ArrayList<>();
		transportistas = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Transportista> getTransportistas() {
		return transportistas;
	}

	public void setTransportistas(List<Transportista> transportistas) {
		this.transportistas = transportistas;
	}
}
