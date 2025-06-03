package com.example.crudapi.controller;

import com.example.crudapi.model.Product;
import com.example.crudapi.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "API de Produtos", description = "API para gerenciamento de produtos")
public class ProductController {
    private final ProductRepository repositorio;

    public ProductController(ProductRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    @Operation(summary = "Buscar todos os produtos")
    public List<Product> buscarTodosProdutos() {
        return repositorio.findAll();
    }
    
@PostMapping
@Operation(summary = "Criar um novo produto")
public ResponseEntity<String> criarProduto(@RequestBody Product produto) {
    repositorio.save(produto);
    return ResponseEntity.ok("Produto adicionado com sucesso!");
}

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto existente")
    public ResponseEntity<Product> atualizarProduto(@PathVariable Long id, @RequestBody Product produto) {
        if (repositorio.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        return ResponseEntity.ok(repositorio.save(produto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um produto")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        if (repositorio.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}