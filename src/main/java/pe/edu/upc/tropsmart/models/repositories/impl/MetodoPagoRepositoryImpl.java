package pe.edu.upc.tropsmart.models.repositories.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.tropsmart.models.entities.MetodoPago;
import pe.edu.upc.tropsmart.models.repositories.MetodoPagoRepository;

@Named
public class MetodoPagoRepositoryImpl implements MetodoPagoRepository, Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(name = "TropSmartPU")
	private EntityManager em;
	
	@Override
	public MetodoPago save(MetodoPago entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public MetodoPago update(MetodoPago entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<MetodoPago> opt = findById(id); 
		if(!opt.isEmpty())
			em.remove(opt.get());
	}

	@Override
	public List<MetodoPago> findAll() throws Exception {
		return em.createQuery("SELECT m FROM MetodoPago m", MetodoPago.class).getResultList();
	}

	@Override
	public Optional<MetodoPago> findById(Integer id) throws Exception {
		TypedQuery<MetodoPago> query = em.createQuery("SELECT m FROM MetodoPago m WHERE m.id = ?1", MetodoPago.class).setParameter(1, id);
		List<MetodoPago> metodospago = query.getResultList();
		if (metodospago != null && !metodospago.isEmpty())
			return Optional.of(metodospago.get(0));
		
		return null;
	}

}
