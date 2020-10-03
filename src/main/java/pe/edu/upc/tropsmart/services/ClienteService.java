package pe.edu.upc.tropsmart.services;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.tropsmart.models.entities.Cliente;

public interface ClienteService extends CrudService<Cliente, Integer>{
	List<Cliente> findByNombres(String nombres) throws Exception;
	List<Cliente> findByApellidos(String apellidos) throws Exception;
	Optional<Cliente> findByNumeroDocumento(String numeroDocumento) throws Exception;
	Optional<Cliente> findByNombreUsuario(String nombreUsuario) throws Exception;
	Optional<Cliente> findByNombreUsuarioYClave(String nombreUsuario, String clave) throws Exception;
}
