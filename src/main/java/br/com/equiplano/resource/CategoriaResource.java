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
import br.com.equiplano.dto.CategoriaDTO;
import br.com.equiplano.services.CategoriaService;

@Controller
@Path("/categorias")
public class CategoriaResource {

	@Inject
	private Result result;

	@Inject
	private CategoriaService service;

	@Post("")
	@Consumes(value = "application/json", options = WithoutRoot.class)
	public void insert(CategoriaDTO categoriaDTO) {

		try {

			this.service.save(categoriaDTO);
			result.use(Results.status()).created();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}

	}

	@Put("")
	@Consumes(value = "application/json", options = WithoutRoot.class)
	public void update(CategoriaDTO categoriaDTO) {

		try {

			this.service.update(categoriaDTO);
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

			CategoriaDTO categoriaDTO = this.service.findById(id);
			this.service.remove(categoriaDTO);
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
			List<CategoriaDTO> produtos = this.service.findAll();
			result.use(Results.json()).withoutRoot().from(produtos).serialize();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}
	}

	@Get("/{id}")
	public void findById(Long id) {

		try {

			CategoriaDTO categoriaDTO = this.service.findById(id);
			
			if(categoriaDTO != null){
				result.use(Results.json()).withoutRoot().from(categoriaDTO).serialize();
			}else{
				result.use(Results.status()).noContent();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
			result.use(Results.status()).internalServerError();
		}

	}

}