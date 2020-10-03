package pe.edu.upc.tropsmart.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.tropsmart.models.entities.Transportista;
import pe.edu.upc.tropsmart.models.repositories.TransportistaRepository;
import pe.edu.upc.tropsmart.services.TransportistaService;

@Named
@ApplicationScoped
public class TransportistaServiceImpl implements TransportistaService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TransportistaRepository transportistaRepository;
	
	@Transactional
	@Override
	public Transportista save(Transportista entity) throws Exception {
		return transportistaRepository.save(entity);
	}

	@Transactional
	@Override
	public Transportista update(Transportista entity) throws Exception {
		return transportistaRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		transportistaRepository.deleteById(id);
	}

	@Override
	public List<Transportista> findAll() throws Exception {
		return transportistaRepository.findAll();
	}

	@Override
	public Optional<Transportista> findById(Integer id) throws Exception {
		return transportistaRepository.findById(id);
	}

	@Override
	public List<Transportista> findByNombres(String nombres) throws Exception {
		return transportistaRepository.findByNombres(nombres);
	}

	@Override
	public List<Transportista> findByApellidos(String apellidos) throws Exception {
		return transportistaRepository.findByApellidos(apellidos);
	}

	@Override
	public Optional<Transportista> findByNumeroDocumento(String numeroDocumento) throws Exception {
		return transportistaRepository.findByNumeroDocumento(numeroDocumento);
	}

	@Override
	public Optional<Transportista> findByNombreUsuarioYClave(String nombreUsuario, String clave) throws Exception {
		return transportistaRepository.findByNombreUsuarioYClave(nombreUsuario, clave);
	}

	@Override
	public Optional<Transportista> findByNombreUsuario(String nombreUsuario) throws Exception {
		return transportistaRepository.findByNombreUsuario(nombreUsuario);
	}

}
