package br.com.projeto.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projeto.api.modelo.Fornecedor;
import br.com.projeto.api.repositorio.FornecedorRepository;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
@CrossOrigin(origins = "*")
public class FornecedorResource {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public List<Fornecedor> obterTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    @GetMapping("/paginacao")
    public ResponseEntity<List<Fornecedor>> getAllFornecedoress(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "limit") int limit) {
        try {
            PageRequest pageable = PageRequest.of(page - 1, limit);
            Page<Fornecedor> fornecedores = fornecedorRepository.findAll(pageable);

            if (fornecedores.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(fornecedores.getContent());
        } catch (Exception e) {
            // Tratar o erro aqui
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public Fornecedor obterFornecedorPorId(@PathVariable Long id) {
        return fornecedorRepository.findById(id).orElse(null);
    }

    @GetMapping("/descricao")
    public List<Fornecedor> buscarProdutosPorLetra(@RequestParam(value = "palavra") String palavra) {
        return fornecedorRepository.findByNomeContainingIgnoreCase(palavra);
    }

    @PostMapping
    public Fornecedor criarFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @PutMapping("/{id}")
    public Fornecedor atualizarFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElse(null);
        if (fornecedor != null) {
            fornecedor.setNome(fornecedorAtualizado.getNome());
            fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
            fornecedor.setEndereco(fornecedorAtualizado.getEndereco());
            fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
            return fornecedorRepository.save(fornecedor);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletarFornecedor(@PathVariable Long id) {
        fornecedorRepository.deleteById(id);
    }
}
