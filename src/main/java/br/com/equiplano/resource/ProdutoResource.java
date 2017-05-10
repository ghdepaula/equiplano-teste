package br.com.equiplano.resource;

import java.util.List;
import javax.inject.Inject;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;
import br.com.equiplano.dto.ProdutoDTO;
import br.com.equiplano.services.ProdutoService;

@Controller
@Path("/produtos")
public class ProdutoResource {

	@Inject
	private Result result;

	@Inject
	private ProdutoService service;

	@Post("")
	@Consumes(value = "application/json", options = WithoutRoot.class)
	public void insert(ProdutoDTO produtoDTO) {

		try {

			this.service.save(produtoDTO);
			result.use(Results.status()).created();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}

	}

	@Put("")
	@Consumes(value = "application/json", options = WithoutRoot.class)
	public void update(ProdutoDTO produtoDTO) {

		try {

			this.service.update(produtoDTO);
			result.use(Results.status()).ok();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}

	}

	@Delete("/{id}")
	public void delete(Long id) {

		try {

			ProdutoDTO produtoDTO = this.service.findById(id);
			this.service.remove(produtoDTO);
			result.use(Results.status()).ok();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}

	}

	@Get("")
	public void findAll() {

		try {
			List<ProdutoDTO> produtos = this.service.findAll();
			result.use(Results.json()).withoutRoot().from(produtos).include("categoriaDTO").serialize();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}
	}

	@Get("/{id}")
	public void findById(Long id) {

		try {

			ProdutoDTO produtoDTO = this.service.findById(id);
			
			if(produtoDTO != null){
				result.use(Results.json()).withoutRoot().from(produtoDTO).include("categoriaDTO").serialize();
			}else{
				result.use(Results.status()).noContent();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}

	}

	@Get("/descricao/{descricao}")
	public void findByDescricao(String descricao) {

		try {

			List<ProdutoDTO> produtosDTO = this.service.findByDescricao(descricao);
			result.use(Results.json()).withoutRoot().from(produtosDTO).include("categoriaDTO").serialize();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}

	}

}