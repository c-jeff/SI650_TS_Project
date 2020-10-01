package pe.edu.upc.tropsmart.models.repositories;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.tropsmart.models.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	List<Cliente> findByNombres(String nombres) throws Exception;
	List<Cliente> findByApellidos(String apellidos) throws Exception;
	Optional<Cliente> findByNumeroDocumento(String numeroDocumento) throws Exception;
}
