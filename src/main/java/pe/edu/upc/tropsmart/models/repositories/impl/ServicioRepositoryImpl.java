package pe.edu.upc.tropsmart.models.repositories.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.tropsmart.models.entities.Servicio;
import pe.edu.upc.tropsmart.models.repositories.ServicioRepository;

@Named
public class ServicioRepositoryImpl implements ServicioRepository, Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name = "TropSmartPU")
	private EntityManager em;

	@Override
	public Servicio save(Servicio entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Servicio update(Servicio entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Servicio> opt = findById(id); 
		if(!opt.isEmpty())
			em.remove(opt.get());
	}

	@Override
	public List<Servicio> findAll() throws Exception {
		return em.createQuery("SELECT c FROM Servicio c", Servicio.class).getResultList();
	}

	@Override
	public Optional<Servicio> findById(Integer id) throws Exception {
		TypedQuery<Servicio> query = em.createQuery("SELECT s FROM Servicio s WHERE s.id = ?1", Servicio.class).setParameter(1, id);
		List<Servicio> servicios = query.getResultList();
		if (servicios != null && !servicios.isEmpty())
			return Optional.of(servicios.get(0));
		
		return null;
	}

	@Override
	public List<Servicio> findByEstado(String estado) throws Exception {
		TypedQuery<Servicio> query = em.createQuery("SELECT s FROM Servicio s WHERE s.estado LIKE '%?1%'", Servicio.class).setParameter(1, estado);
		return query.getResultList();
	}
}
