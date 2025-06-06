package com.salesianostriana.dam.Merchandising.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.Merchandising.model.Categoria;
import com.salesianostriana.dam.Merchandising.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByCategoria(Categoria categoria);
    
    @Query("select p.id from Producto p")
    List<Long> obtenerIds();
    
    @Query("select p from Producto p where p.categoria.id = ?1")
    List<Producto> findByCategoriaId(Long categoriaId);
    
    @Query("select count(p) from Producto p where p.categoria = ?1")
    int findNumProductosByCategoria(Categoria categoria);
}
