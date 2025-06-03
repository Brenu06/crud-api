package com.example.crudapi.repository;

import com.example.crudapi.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public Product save(Product product) {
    if (product.getId() == null) {
        product.setId(counter.incrementAndGet());
        products.add(product);
        System.out.println("Produto adicionado: " + product);
    } else {
        update(product);
        System.out.println("Produto atualizado: " + product);
    }
    return product;
}

    public Product update(Product product) {
        products.replaceAll(p -> p.getId().equals(product.getId()) ? product : p);
        return product;
    }

    public void deleteById(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}