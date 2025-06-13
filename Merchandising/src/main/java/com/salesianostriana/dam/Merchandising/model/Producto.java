package com.salesianostriana.dam.Merchandising.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "productos")
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;
    @Column(length = 1000)
    private String imagen;
    @Column(length = 1000)
    private String altImagen;

    //private LocalDate fechaAlta;

    @Column(nullable = false)
    private Double precio;
    private Double descuento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    public double getPrecioFinal() {
    	return precio-precio*descuento;
    }
    
    public boolean enOferta() {
    	if (descuento>0) {
			return true;
		}else {
			return false;
		}
    }
    
}
