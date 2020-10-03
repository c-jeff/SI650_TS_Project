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
import pe.edu.upc.tropsmart.models.entities.DetalleServicio;
import pe.edu.upc.tropsmart.models.entities.Servicio;
import pe.edu.upc.tropsmart.models.entities.Transportista;
import pe.edu.upc.tropsmart.services.ClienteService;
import pe.edu.upc.tropsmart.services.DetalleServicioService;
import pe.edu.upc.tropsmart.services.ServicioService;
import pe.edu.upc.tropsmart.services.TransportistaService;

@Named("servicioView")
@ViewScoped
public class ServicioView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Servicio> servicios;
	private Servicio servicio;
	private DetalleServicio detalleServicio;
	private Servicio servicioSelected;
	private Servicio servicioSearch;
	private Boolean nuevo;
	
	private List<Cliente> clientes;
	private List<Transportista> transportistas;
	
	
	private Boolean disabledNuevo;
	private Boolean disabledGrabar;
	private Boolean disabledCancelar;
	private Boolean disabledEditar;
	private Boolean disabledEliminar;
	
	private String stylePanelGrid;
	private String StyleDataTable;
	
	@Inject
	private ServicioService servicioService;
	
	@Inject
	private DetalleServicioService detalleServicioService;
	
	@Inject
	private ClienteService clienteService;

	@Inject
	private TransportistaService transportistaService;
	
	@PostConstruct
	public void init() {
		cleanForm();
		loadServicios();
		servicioSearch = new Servicio();
		nuevo = null;
		disabledAllButton();
		try {
		clientes = clienteService.findAll();
		transportistas = transportistaService.findAll();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.print(e.getMessage());
		}
	}
	
	public void loadServicios() {
		try {
			servicios = servicioService.findAll();
		}
		catch( Exception e ) {
			e.printStackTrace();
			System.err.println( e.getMessage() );
		}
	}	
	
	public Optional<Cliente> loadCliente(Integer id) {
		try {
			return clienteService.findById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println( e.getMessage() );
		}
		return Optional.empty();
	}
	
	public void newServicio() {
		nuevo = true;
		cleanForm();
		loadServicios();
		addMessageInfo("Agregando nuevo servicio");
		enabledButtonGrabar();
	}
	
	public void cleanForm() {
		servicio = new Servicio();
		servicioSelected = null;
	}
	
	public void saveServicio() {
		try {
			if (nuevo != null) {
				if (nuevo) {
					servicio.setEstado("Pendiente");
					servicioService.save(servicio);
					//detalleServicio.setId(servicio.getId());
					//detalleServicioService.save(detalleServicio);
					addMessageInfo("Se agrego el servicio correctamente");
				} 
				else if (!nuevo) {
					servicioService.update(servicio);
					addMessageInfo("Se actualizaron los datos del servicio");
				}	
				nuevo = null;
				cleanForm();
				loadServicios();
				disabledAllButton();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println( e.getMessage() );
		}
	}
	
	public void cancelSaveServicio() {
		cleanForm();
		loadServicios();
		disabledAllButton();
	}
	
	public void selectServicio(SelectEvent<Servicio> e) {
		cleanForm();
		servicioSelected = e.getObject();
		enabledButtonEditarEliminar();
	}
	
	public void unselectServicio(UnselectEvent<Servicio> e) {
		cleanForm();
		servicioSelected = null;
		disabledAllButton();
	}
	
	public void editServicio() {
		if (servicioSelected != null) {
			nuevo = false;
			servicio = servicioSelected;
			servicioSelected = null;
			addMessageInfo("Ya puedes modificar los datos");
			enabledButtonGrabar();
		} 
		else {
			addMessageError("No has seleccionado un servicio");
		}
	}
	
	public void deleteServicio() {
		if (servicioSelected != null) {
			try {
				servicioService.deleteById(servicioSelected.getId());
				nuevo = null;				
				addMessageInfo("Se elimino un servicio " + servicioSelected.getEstado());
				cleanForm();
				loadServicios();
			}
			catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
			disabledAllButton();
		}
		else {
			addMessageError("No has seleccionado un servicio");
		}
	}
	
	public void searchEstadoServicio() {
		try {
			servicios = servicioService.findByEstado(servicioSearch.getEstado());
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

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public DetalleServicio getDetalleServicio() {
		return detalleServicio;
	}

	public void setDetalleServicio(DetalleServicio detalleServicio) {
		this.detalleServicio = detalleServicio;
	}

	public Servicio getServicioSelected() {
		return servicioSelected;
	}

	public void setServicioSelected(Servicio servicioSelected) {
		this.servicioSelected = servicioSelected;
	}

	public Servicio getServicioSearch() {
		return servicioSearch;
	}

	public void setServicioSearch(Servicio servicioSearch) {
		this.servicioSearch = servicioSearch;
	}

	public Boolean getNuevo() {
		return nuevo;
	}

	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
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

	public Boolean getDisabledNuevo() {
		return disabledNuevo;
	}

	public void setDisabledNuevo(Boolean disabledNuevo) {
		this.disabledNuevo = disabledNuevo;
	}

	public Boolean getDisabledGrabar() {
		return disabledGrabar;
	}

	public void setDisabledGrabar(Boolean disabledGrabar) {
		this.disabledGrabar = disabledGrabar;
	}

	public Boolean getDisabledCancelar() {
		return disabledCancelar;
	}

	public void setDisabledCancelar(Boolean disabledCancelar) {
		this.disabledCancelar = disabledCancelar;
	}

	public Boolean getDisabledEditar() {
		return disabledEditar;
	}

	public void setDisabledEditar(Boolean disabledEditar) {
		this.disabledEditar = disabledEditar;
	}

	public Boolean getDisabledEliminar() {
		return disabledEliminar;
	}

	public void setDisabledEliminar(Boolean disabledEliminar) {
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

	public ServicioService getServicioService() {
		return servicioService;
	}

	public void setServicioService(ServicioService servicioService) {
		this.servicioService = servicioService;
	}

	public DetalleServicioService getDetalleServicioService() {
		return detalleServicioService;
	}

	public void setDetalleServicioService(DetalleServicioService detalleServicioService) {
		this.detalleServicioService = detalleServicioService;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public TransportistaService getTransportistaService() {
		return transportistaService;
	}

	public void setTransportistaService(TransportistaService transportistaService) {
		this.transportistaService = transportistaService;
	}
	
	

}
