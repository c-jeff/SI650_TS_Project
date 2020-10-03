package pe.edu.upc.tropsmart.models.repositories;

import java.util.List;

import pe.edu.upc.tropsmart.models.entities.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer>{
	List<Servicio> findByEstado(String estado) throws Exception;
}
