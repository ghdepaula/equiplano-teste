package br.com.equiplano.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import br.com.equiplano.model.Produto;

@RequestScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProdutoRepository {

	@Inject
	private EntityManager em;

	public void save(Produto produto) {
		this.em.persist(produto);
		this.em.flush();
	}
	
	public void update(Produto produto) {
		this.em.merge(produto);
		this.em.flush();
	}

	public void remove(Produto produto) {
		Produto prod = this.em.getReference(Produto.class, produto.getId());
		this.em.remove(prod);
		this.em.flush();
	}

	public Produto findById(Produto produto) {
		return this.em.find(Produto.class, produto.getId());
	}
	
	public Produto findById(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> findAll(){
		CriteriaQuery cq = this.em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Produto.class));
		List<Produto> result = this.em.createQuery(cq).getResultList();
		
		return result;
	}
	
	public List<Produto> findManyResults(String namedQuery, Map<String, Object> parameters) {
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		Query query = this.em.createNamedQuery(namedQuery);
		if ((parameters != null) && (!parameters.isEmpty())) {
			populateQueryParameters(query, parameters);
		}
		produtos = query.getResultList();
		
		return produtos;
	}
	
	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter((String) entry.getKey(), entry.getValue());
		}
	}

}