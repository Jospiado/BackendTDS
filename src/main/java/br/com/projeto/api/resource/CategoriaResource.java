package br.com.projeto.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.api.modelo.Categoria;
import br.com.projeto.api.repositorio.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categoriaSalva.getId()).toUri();

        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categoriaSalva);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable Long id) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);

        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @GetMapping("/descricao")
    public List<Categoria> buscarProdutosPorLetra(@RequestParam(value = "palavra") String palavra) {
        return categoriaRepository.findByNomeContainingIgnoreCase(palavra);
    }

    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAtualizado) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null) {
            categoria.setNome(categoriaAtualizado.getNome());
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
    }
}
