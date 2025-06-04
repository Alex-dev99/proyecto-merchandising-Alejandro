package com.salesianostriana.dam.Merchandising.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.Merchandising.model.Producto;
import com.salesianostriana.dam.Merchandising.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repositorio;
    
    private final List<Producto> productos = new ArrayList<>();
    private Long nextId = 1L;
    
    public List<Producto> findAll() {
        return repositorio.findAll();
    }

    public Producto save(Producto producto) {
        return repositorio.save(producto);
    }

    public Producto findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Producto delete(Producto producto) {
        Producto result = findById(producto.getId());
        if (result != null) {
            repositorio.delete(result);
        }
        return result;
    }
    
    public Optional<Producto> obtenerProducto(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

	public List<Producto> findByCategoriaId(Long categoriaId) {
		
		return ProductoRepository.findByCategoriaId(categoriaId);
	}
}
