package pe.edu.upc.tropsmart.models.repositories.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.tropsmart.models.entities.Transportista;
import pe.edu.upc.tropsmart.models.repositories.TransportistaRepository;

@Named
public class TransportistaRepositoryImpl implements TransportistaRepository, Serializable{
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext(name = "TropSmartPU")
	private EntityManager em;
	
	@Override
	public Transportista save(Transportista entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public Transportista update(Transportista entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Transportista> opt = findById(id); 
		if(!opt.isEmpty())
			em.remove(opt.get());
	}

	@Override
	public List<Transportista> findAll() throws Exception {
		return em.createQuery("SELECT t FROM Transportista t", Transportista.class).getResultList();
	}

	@Override
	public Optional<Transportista> findById(Integer id) throws Exception {
		TypedQuery<Transportista> query = em.createQuery("SELECT t FROM Transportista t WHERE t.id = ?1", Transportista.class).setParameter(1, id);
		List<Transportista> transportistas = query.getResultList();
		if (transportistas != null && !transportistas.isEmpty())
			return Optional.of(transportistas.get(0));
		
		return null;
	}

	@Override
	public List<Transportista> findByNombres(String nombres) throws Exception {
		TypedQuery<Transportista> query = em.createQuery("SELECT t FROM Transportista t WHERE t.nombres LIKE '%?1%'", Transportista.class).setParameter(1, nombres);
		return query.getResultList();
	}

	@Override
	public List<Transportista> findByApellidos(String apellidos) throws Exception {
		TypedQuery<Transportista> query = em.createQuery("SELECT t FROM Transportista t WHERE t.apellidos LIKE '%?1%'", Transportista.class).setParameter(1, apellidos);
		return query.getResultList();
	}

	@Override
	public Optional<Transportista> findByNumeroDocumento(String numeroDocumento) throws Exception {
		TypedQuery<Transportista> query = em.createQuery("SELECT t FROM Transportista t WHERE t.numero_documento = ?1", Transportista.class).setParameter(1, numeroDocumento);
		List<Transportista> transportistas = query.getResultList();
		if (transportistas != null && !transportistas.isEmpty())
			return Optional.of(transportistas.get(0));
		
		return null;
	}

}
