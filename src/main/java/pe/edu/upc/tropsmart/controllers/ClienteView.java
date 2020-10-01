package pe.edu.upc.tropsmart.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.tropsmart.models.entities.Cliente;
import pe.edu.upc.tropsmart.services.ClienteService;

@Named("clienteView")
@ViewScoped
public class ClienteView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientes;
	
	@Inject
	private ClienteService clienteService;
	
	@PostConstruct
	public void init() {
		loadClientes();
	}
	
	public void loadClientes() {
		try {
			 clientes = clienteService.findAll();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.print(e.getMessage());
		}
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}
}
