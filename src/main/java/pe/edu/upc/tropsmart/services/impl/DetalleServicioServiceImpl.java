package pe.edu.upc.tropsmart.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.tropsmart.models.entities.DetalleServicio;
import pe.edu.upc.tropsmart.models.repositories.DetalleServicioRepository;
import pe.edu.upc.tropsmart.services.DetalleServicioService;

@Named
@ApplicationScoped
public class DetalleServicioServiceImpl implements DetalleServicioService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DetalleServicioRepository detalleServicioRepository;
	
	@Transactional
	@Override
	public DetalleServicio save(DetalleServicio entity) throws Exception {
		return detalleServicioRepository.save(entity);
	}

	@Transactional
	@Override
	public DetalleServicio update(DetalleServicio entity) throws Exception {
		return detalleServicioRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		detalleServicioRepository.deleteById(id);
	}

	@Override
	public List<DetalleServicio> findAll() throws Exception {
		return detalleServicioRepository.findAll();
	}

	@Override
	public Optional<DetalleServicio> findById(Integer id) throws Exception {
		return detalleServicioRepository.findById(id);
	}

}
