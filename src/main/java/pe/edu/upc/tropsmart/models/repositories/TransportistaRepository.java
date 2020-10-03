package pe.edu.upc.tropsmart.models.repositories;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.tropsmart.models.entities.Transportista;

public interface TransportistaRepository extends JpaRepository<Transportista, Integer>{
	List<Transportista> findByNombres(String nombres) throws Exception;
	List<Transportista> findByApellidos(String apellidos) throws Exception;
	Optional<Transportista> findByNumeroDocumento(String numeroDocumento) throws Exception;
	Optional<Transportista> findByNombreUsuario(String nombreUsuario) throws Exception;
	Optional<Transportista> findByNombreUsuarioYClave(String nombreUsuario, String clave) throws Exception;
}
