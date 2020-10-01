package pe.edu.upc.tropsmart.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import pe.edu.upc.tropsmart.models.entities.Servicio;
import pe.edu.upc.tropsmart.models.repositories.ServicioRepository;
import pe.edu.upc.tropsmart.services.ServicioService;

@Named
public class ServicioServiceImpl implements ServicioService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServicioRepository servicioRepository;
	
	@Transactional
	@Override
	public Servicio save(Servicio entity) throws Exception {
		return servicioRepository.save(entity);
	}

	@Transactional
	@Override
	public Servicio update(Servicio entity) throws Exception {
		return servicioRepository.update(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		servicioRepository.deleteById(id);
	}

	@Override
	public List<Servicio> findAll() throws Exception {
		return servicioRepository.findAll();
	}

	@Override
	public Optional<Servicio> findById(Integer id) throws Exception {
		return servicioRepository.findById(id);
	}

	@Override
	public List<Servicio> findByEstado(String estado) throws Exception {
		return servicioRepository.findByEstado(estado);
	}

}
