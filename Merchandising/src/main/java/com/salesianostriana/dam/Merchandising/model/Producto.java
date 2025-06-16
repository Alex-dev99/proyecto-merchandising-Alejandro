package com.salesianostriana.dam.Merchandising.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Builder.Default
    private LocalDate fechaAlta= LocalDate.now();

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
    	return descuento>0;
    }
    
}
