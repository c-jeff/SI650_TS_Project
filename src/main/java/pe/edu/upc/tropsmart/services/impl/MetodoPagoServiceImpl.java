package pe.edu.upc.tropsmart.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.tropsmart.models.entities.MetodoPago;
import pe.edu.upc.tropsmart.models.repositories.MetodoPagoRepository;
import pe.edu.upc.tropsmart.services.MetodoPagoService;

@Named
public class MetodoPagoServiceImpl implements MetodoPagoService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MetodoPagoRepository metodoPagoRepository;
	
	@Transactional
	@Override
	public MetodoPago save(MetodoPago entity) throws Exception {
		return metodoPagoRepository.save(entity);
	}

	@Transactional
	@Override
	public MetodoPago update(MetodoPago entity) throws Exception {
		return metodoPagoRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		metodoPagoRepository.deleteById(id);
	}

	@Override
	public List<MetodoPago> findAll() throws Exception {
		return metodoPagoRepository.findAll();
	}

	@Override
	public Optional<MetodoPago> findById(Integer id) throws Exception {
		return metodoPagoRepository.findById(id);
	}

}
