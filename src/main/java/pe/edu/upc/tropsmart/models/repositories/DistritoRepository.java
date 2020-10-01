package pe.edu.upc.tropsmart.models.repositories;

import java.util.Optional;

import pe.edu.upc.tropsmart.models.entities.Distrito;

public interface DistritoRepository extends JpaRepository<Distrito, Integer>{
	Optional<Distrito> findByNombre(String nombre) throws Exception;
}
