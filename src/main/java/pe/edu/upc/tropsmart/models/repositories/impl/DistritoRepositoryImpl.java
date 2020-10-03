package pe.edu.upc.tropsmart.models.repositories.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.tropsmart.models.entities.Distrito;
import pe.edu.upc.tropsmart.models.repositories.DistritoRepository;

@Named
@ApplicationScoped
public class DistritoRepositoryImpl implements DistritoRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(name = "TropSmartPU")
	private EntityManager em;
	
	@Override
	public Distrito save(Distrito entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Distrito update(Distrito entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Distrito> opt = findById(id); 
		if(!opt.isEmpty())
			em.remove(opt.get());
	}

	@Override
	public List<Distrito> findAll() throws Exception {
		return em.createQuery("SELECT d FROM Distrito d", Distrito.class).getResultList();
	}

	@Override
	public Optional<Distrito> findById(Integer id) throws Exception {
		TypedQuery<Distrito> query = em.createQuery("SELECT d FROM Distrito d WHERE d.id = ?1", Distrito.class).setParameter(1, id);
		List<Distrito> distritos = query.getResultList();
		if (distritos != null && !distritos.isEmpty())
			return Optional.of(distritos.get(0));
		
		return null;
	}

	@Override
	public Optional<Distrito> findByNombre(String nombre) throws Exception {
		TypedQuery<Distrito> query = em.createQuery("SELECT d FROM Distrito d WHERE d.nombre = ?1", Distrito.class).setParameter(1, nombre);
		List<Distrito> distritos = query.getResultList();
		if (distritos != null && !distritos.isEmpty())
			return Optional.of(distritos.get(0));
		
		return null;
	}

}
