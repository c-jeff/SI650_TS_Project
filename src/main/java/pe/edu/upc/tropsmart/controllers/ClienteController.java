package pe.edu.upc.tropsmart.controllers;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.tropsmart.models.entities.Cliente;
import pe.edu.upc.tropsmart.models.entities.Distrito;
import pe.edu.upc.tropsmart.models.entities.Transportista;
import pe.edu.upc.tropsmart.services.ClienteService;
import pe.edu.upc.tropsmart.services.DistritoService;
import pe.edu.upc.tropsmart.services.TransportistaService;

@Named("clienteController")
@ViewScoped
public class ClienteController implements Serializable{

	private static final long serialVersionUID = 1L;

	private Boolean esTransportista;
	private Cliente cliente;
	private Transportista transportista;
	private List<Distrito> distritos;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private TransportistaService transportistaService;
	
	@Inject
	private DistritoService distritoService;
	
	@PostConstruct
	public void init() {
		cliente = new Cliente();
		transportista = new Transportista();
		try {
			distritos = distritoService.findAll();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void registrar(){
		try {
			if(transportistaService.findByNombreUsuario(cliente.getNombreUsuario()) == null &&
				clienteService.findByNombreUsuario(cliente.getNombreUsuario()) == null) {
			
				if(esTransportista) {
					transportista.setNombreUsuario(cliente.getNombreUsuario());
					transportista.setClave(cliente.getClave());
					transportista.setNombres(cliente.getNombres());
					transportista.setApellidos(cliente.getApellidos());
					transportista.setNumeroDocumento(cliente.getNumeroDocumento());
					transportista.setCelular(cliente.getCelular());
					transportista.setCorreo(cliente.getCorreo());
					transportista.setFechaNacimiento(cliente.getFechaNacimiento());
					transportista.setFechaRegistro(new Date(System.currentTimeMillis()));
					transportistaService.save(transportista);
					addMessageInfo("Registrado como transportista, ya puedes iniciar sesión");
				}
			
				if(!esTransportista) {
					cliente.setFechaRegistro(new Date(System.currentTimeMillis()));
					clienteService.save(cliente);
					addMessageInfo("Registrado como cliente, ya puedes iniciar sesión");
				}
			}
			else 
				addMessageError("El nombre de usuario ya existe, utiliza otro nombre");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	public void addMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void addMessageError(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public Boolean getEsTransportista() {
		return esTransportista;
	}

	public void setEsTransportista(Boolean esTransportista) {
		this.esTransportista = esTransportista;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

	public TransportistaService getTransportistaService() {
		return transportistaService;
	}

	public void setTransportistaService(TransportistaService transportistaService) {
		this.transportistaService = transportistaService;
	}

	public DistritoService getDistritoService() {
		return distritoService;
	}

	public void setDistritoService(DistritoService distritoService) {
		this.distritoService = distritoService;
	}
	
	
}
