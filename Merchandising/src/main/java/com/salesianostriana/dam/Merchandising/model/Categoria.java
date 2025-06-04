package com.salesianostriana.dam.Merchandising.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Table(name = "categorias") @NoArgsConstructor @AllArgsConstructor
@Builder
public class Categoria {
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false)
	    private String nombre;    
	    
	    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
	    private List <Producto> productos = new ArrayList <>();
}
