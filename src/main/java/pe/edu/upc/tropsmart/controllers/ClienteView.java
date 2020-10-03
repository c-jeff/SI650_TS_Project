package pe.edu.upc.tropsmart.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.upc.tropsmart.models.entities.Cliente;
import pe.edu.upc.tropsmart.models.entities.Distrito;
import pe.edu.upc.tropsmart.services.ClienteService;
import pe.edu.upc.tropsmart.services.DistritoService;

@Named("clienteView")
@ViewScoped
public class ClienteView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientes;
	private Cliente cliente;
	private Cliente clienteSelected;
	private Cliente clienteSearch;
	private Boolean nuevo;
	
	private List<Distrito> distritos;
	
	private Boolean disabledNuevo;
	private Boolean disabledGrabar;
	private Boolean disabledCancelar;
	private Boolean disabledEditar;
	private Boolean disabledEliminar;
	
	// Style for Panelgrid y Datatable
	private String stylePanelGrid;
	private String StyleDataTable;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private DistritoService distritoService;

	@PostConstruct
	public void init() {
		cleanForm();
		loadClientes();
		loadDistritos();
		this.clienteSearch = new Cliente();
		nuevo = null;
		disabledAllButton();
	}
	
	public void loadClientes() {
		try {
			this.clientes = clienteService.findAll();
		}
		catch( Exception e ) {
			e.printStackTrace();
			System.err.println( e.getMessage() );
		}
	}	
	
	public void loadDistritos() {
		try {
			this.distritos = distritoService.findAll();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println( e.getMessage() );
		}
	}
	public Optional<Distrito> loadDistrito( Integer id ) {
		try {
			return distritoService.findById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println( e.getMessage() );
		}
		return Optional.empty();
	}
	
	
	
	public void newCliente() {
		nuevo = true;
		cleanForm();
		loadClientes();
		addMessageInfo("Agregando nuevo cliente");
		enabledButtonGrabar();
	}
	public void cleanForm( ) {
		this.cliente = new Cliente();
		this.clienteSelected = null;
	}
	
	public void saveCliente() {
		try {
			if (nuevo != null) {
				verifyDistrito();
				if (nuevo) {
					clienteService.save(this.cliente);
					addMessageInfo("Se agregó al cliente correctamente");
				} 
				else if (!nuevo) {
					
					clienteService.update(this.cliente);
					addMessageInfo("Se actualizaron los datos del cliente");
				}	
				nuevo = null;
				cleanForm();
				loadClientes();
				disabledAllButton();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println( e.getMessage() );
		}
	}
	
	public void verifyDistrito() {
		if (nuevo) {
			changeDistrito();
		} 
		else if (!nuevo) {
			if (cliente.getDistrito() != null) {
				if (!cliente.getDistritoId().equals(this.cliente.getDistrito().getId()))
					changeDistrito();
			}
			else {
				changeDistrito();
			}				
		}
	}
	
	public void changeDistrito() {
		Optional<Distrito> optional = loadDistrito(this.cliente.getDistritoId());
		if(!optional.isEmpty()) {
			cliente.setDistrito( optional.get() );
		}
	}
	
	public void cancelSaveCliente() {
		cleanForm();
		loadClientes();
		disabledAllButton();
	}
	
	public void selectCliente(SelectEvent<Cliente> e) {
		cleanForm();
		clienteSelected = e.getObject();
		if (getClienteSelected().getDistrito() != null )
			clienteSelected.setDistritoId(this.getClienteSelected().getDistrito().getId());
		
		enabledButtonEditarEliminar();
	}
	
	public void unselectCliente(UnselectEvent<Cliente> e) {
		cleanForm();
		clienteSelected = null;
		disabledAllButton();
	}
	
	public void editCliente() {
		if (clienteSelected != null) {
			nuevo = false;
			cliente = clienteSelected;
			clienteSelected = null;
			addMessageInfo("Ingresa datos para modificar");
			enabledButtonGrabar();
		} 
		else {
			addMessageError("No has seleccionado un cliente");
		}
	}
	
	public void deleteCliente() {
		if (clienteSelected != null) {
			try {
				clienteService.deleteById(clienteSelected.getId());
				nuevo = null;				
				addMessageInfo("Se elimino de forma correcta el cliente: " + clienteSelected.getNombres() );
				cleanForm();
				loadClientes();
			}
			catch (Exception e) {
				e.printStackTrace();
				System.err.println( e.getMessage() );
			}
			disabledAllButton();
		}
		else {
			addMessageError("No has seleccionado un cliente");
		}
	}
	
	public void searchApellidosCliente() {
		try {
			clientes = clienteService.findByApellidos(clienteSearch.getApellidos());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println( e.getMessage() );
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
	
	// Disabled Button
	public void disabledAllButton() {
		this.stylePanelGrid = "none";
		this.StyleDataTable = "block";
		this.disabledNuevo = false;
		this.disabledGrabar = true;
		this.disabledCancelar = true;
		this.disabledEditar = true;
		this.disabledEliminar = true;
	}
	public void enabledButtonGrabar() {
		this.stylePanelGrid = "block";
		this.StyleDataTable = "none";
		this.disabledNuevo = true;
		this.disabledGrabar = false;
		this.disabledCancelar = false;
		this.disabledEditar = true;
		this.disabledEliminar = true;
	}
	public void enabledButtonEditarEliminar() {
		this.stylePanelGrid = "none";
		this.StyleDataTable = "block";
		this.disabledNuevo = false;
		this.disabledGrabar = true;
		this.disabledCancelar = true;
		this.disabledEditar = false;
		this.disabledEliminar = false;
	}
		

	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSelected() {
		return clienteSelected;
	}

	public void setClienteSelected(Cliente clienteSelected) {
		this.clienteSelected = clienteSelected;
	}
	
	public Cliente getClienteSearch() {
		return clienteSearch;
	}

	public void setClienteSearch(Cliente clienteSearch) {
		this.clienteSearch = clienteSearch;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public ClienteService getClienteService() {
		return clienteService;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

	public boolean isDisabledNuevo() {
		return disabledNuevo;
	}

	public void setDisabledNuevo(boolean disabledNuevo) {
		this.disabledNuevo = disabledNuevo;
	}

	public boolean isDisabledGrabar() {
		return disabledGrabar;
	}

	public void setDisabledGrabar(boolean disabledGrabar) {
		this.disabledGrabar = disabledGrabar;
	}

	public boolean isDisabledCancelar() {
		return disabledCancelar;
	}

	public void setDisabledCancelar(boolean disabledCancelar) {
		this.disabledCancelar = disabledCancelar;
	}

	public boolean isDisabledEditar() {
		return disabledEditar;
	}

	public void setDisabledEditar(boolean disabledEditar) {
		this.disabledEditar = disabledEditar;
	}

	public boolean isDisabledEliminar() {
		return disabledEliminar;
	}

	public void setDisabledEliminar(boolean disabledEliminar) {
		this.disabledEliminar = disabledEliminar;
	}

	public String getStylePanelGrid() {
		return stylePanelGrid;
	}

	public void setStylePanelGrid(String stylePanelGrid) {
		this.stylePanelGrid = stylePanelGrid;
	}

	public String getStyleDataTable() {
		return StyleDataTable;
	}

	public void setStyleDataTable(String styleDataTable) {
		StyleDataTable = styleDataTable;
	}

	public DistritoService getDistritoService() {
		return distritoService;
	}

	public void setDistritoService(DistritoService distritoService) {
		this.distritoService = distritoService;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
