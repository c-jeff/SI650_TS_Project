package pe.edu.upc.tropsmart.models.repositories.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.edu.upc.tropsmart.models.entities.Cliente;
import pe.edu.upc.tropsmart.models.repositories.ClienteRepository;

@Named
@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteRepository, Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name = "TropSmartPU")
	private EntityManager em;

	@Override
	public Cliente save(Cliente entity) throws Exception {
		em.persist(entity);
		return null;
	}

	@Override
	public Cliente update(Cliente entity) throws Exception {
		em.merge(entity);
		return null;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		Optional<Cliente> opt = findById(id); 
		if(!opt.isEmpty())
			em.remove(opt.get());
	}

	@Override
	public List<Cliente> findAll() throws Exception {
		return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
	}

	@Override
	public Optional<Cliente> findById(Integer id) throws Exception {
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.id = ?1", Cliente.class).setParameter(1, id);
		List<Cliente> clientes = query.getResultList();
		if (clientes != null && !clientes.isEmpty())
			return Optional.of(clientes.get(0));
		
		return null;
	}

	@Override
	public List<Cliente> findByNombres(String nombres) throws Exception {
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.nombres LIKE '%?1%'", Cliente.class).setParameter(1, nombres);
		return query.getResultList();
	}

	@Override
	public List<Cliente> findByApellidos(String apellidos) throws Exception {
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.apellidos LIKE '%?1%'", Cliente.class).setParameter(1, apellidos);
		return query.getResultList();
	}

	@Override
	public Optional<Cliente> findByNumeroDocumento(String numeroDocumento) throws Exception {
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.numeroDocumento = ?1", Cliente.class).setParameter(1, numeroDocumento);
		List<Cliente> clientes = query.getResultList();
		if (clientes != null && !clientes.isEmpty())
			return Optional.of(clientes.get(0));
		
		return null;
	}

	@Override
	public Optional<Cliente> findByNombreUsuarioYClave(String nombreUsuario, String clave) throws Exception {
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.nombreUsuario = ?1 AND c.clave = ?2", Cliente.class).setParameter(1, nombreUsuario);
		query.setParameter(2, clave);
		List<Cliente> clientes = query.getResultList();
		if (clientes != null && !clientes.isEmpty())
			return Optional.of(clientes.get(0));
		
		return null;
	}

	@Override
	public Optional<Cliente> findByNombreUsuario(String nombreUsuario) throws Exception {
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.nombreUsuario = ?1", Cliente.class).setParameter(1, nombreUsuario);
		List<Cliente> clientes = query.getResultList();
		if (clientes != null && !clientes.isEmpty())
			return Optional.of(clientes.get(0));
		
		return null;
	}
	
}
