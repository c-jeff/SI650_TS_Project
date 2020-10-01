package pe.edu.upc.tropsmart.services;

import java.util.List;

import pe.edu.upc.tropsmart.models.entities.Servicio;

public interface ServicioService extends CrudService<Servicio, Integer>{
	List<Servicio> findByEstado(String estado) throws Exception;
}
