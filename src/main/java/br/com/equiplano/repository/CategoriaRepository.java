package br.com.equiplano.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import br.com.equiplano.model.Categoria;

@RequestScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CategoriaRepository {

	@Inject
	private EntityManager em;

	public void save(Categoria categoria) {
		this.em.persist(categoria);
		this.em.flush();
	}
	
	public void update(Categoria categoria) {
		this.em.merge(categoria);
		this.em.flush();
	}

	public void remove(Categoria categoria) {
		Categoria prod = this.em.getReference(Categoria.class, categoria.getId());
		this.em.remove(prod);
		this.em.flush();
	}

	public Categoria findById(Categoria categoria) {
		return this.em.find(Categoria.class, categoria.getId());
	}
	
	public Categoria findById(Long id) {
		return this.em.find(Categoria.class, id);
	}
	
	public List<Categoria> findAll(){
		CriteriaQuery cq = this.em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Categoria.class));
		List<Categoria> result = this.em.createQuery(cq).getResultList();
		
		return result;
	}
	
	public List<Categoria> findManyResults(String namedQuery, Map<String, Object> parameters) {
		
		List<Categoria> result = new ArrayList<Categoria>();
		
		Query query = this.em.createNamedQuery(namedQuery);
		if ((parameters != null) && (!parameters.isEmpty())) {
			populateQueryParameters(query, parameters);
		}
		result = query.getResultList();
		
		return result;
	}
	
	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter((String) entry.getKey(), entry.getValue());
		}
	}

}