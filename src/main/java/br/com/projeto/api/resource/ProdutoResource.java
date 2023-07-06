package br.com.projeto.api.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.projeto.api.modelo.Produto;
import br.com.projeto.api.repositorio.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> obterTodosProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto obterProdutoPorId(@PathVariable Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @GetMapping("/paginacao")
    public ResponseEntity<List<Produto>> getAllProdutos(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "limit") int limit) {
        try {
            PageRequest pageable = PageRequest.of(page - 1, limit);
            Page<Produto> produtos = produtoRepository.findAll(pageable);

            if (produtos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(produtos.getContent());
        } catch (Exception e) {
            // Tratar o erro aqui
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/descricao")
    public List<Produto> buscarProdutosPorLetra(@RequestParam(value = "palavra") String palavra) {
        return produtoRepository.findByNomeContainingIgnoreCase(palavra);
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto != null) {
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setEstoque(produtoAtualizado.getEstoque());
            produto.setCategoria(produtoAtualizado.getCategoria());
            produto.setFornecedor(produtoAtualizado.getFornecedor());
            return produtoRepository.save(produto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}
