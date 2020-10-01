package pe.edu.upc.tropsmart.models.repositories.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.tropsmart.models.entities.DetalleServicio;
import pe.edu.upc.tropsmart.models.repositories.DetalleServicioRepository;

@Named
public class DetalleServicioRepositoryImpl implements DetalleServicioRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(name = "TropSmartPU")
	private EntityManager em;
	
	@Override
	public DetalleServicio save(DetalleServicio entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public DetalleServicio update(DetalleServicio entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<DetalleServicio> opt = findById(id); 
		if(!opt.isEmpty())
			em.remove(opt.get());
	}

	@Override
	public List<DetalleServicio> findAll() throws Exception {
		return em.createQuery("SELECT d FROM DetalleServicio d", DetalleServicio.class).getResultList();
	}

	@Override
	public Optional<DetalleServicio> findById(Integer id) throws Exception {
		TypedQuery<DetalleServicio> query = em.createQuery("SELECT d FROM DetalleServicio d WHERE d.id = ?1", DetalleServicio.class).setParameter(1, id);
		List<DetalleServicio> detallesservicio = query.getResultList();
		if (detallesservicio != null && !detallesservicio.isEmpty())
			return Optional.of(detallesservicio.get(0));
		
		return null;
	}

}
