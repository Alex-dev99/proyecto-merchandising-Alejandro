package com.salesianostriana.dam.Merchandising.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.salesianostriana.dam.Merchandising.model.Categoria;
import com.salesianostriana.dam.Merchandising.model.Producto;
import com.salesianostriana.dam.Merchandising.service.CategoriaService;
import com.salesianostriana.dam.Merchandising.service.ProductoService;

@Controller
public class HomeController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public String mostrarIndex(Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        return "index";
    }
    
    @GetMapping("/nosotros")
    public String mostrarNosotros(Model model) {

        return "nosotros";
    }
    
    @GetMapping("/categoria/{id}")
    public String productosPorCategoria(@PathVariable Long id, Model model) {
        List<Producto> productos = productoService.findByCategoriaId(id);
        Categoria categoria = categoriaService.findById(id);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("categoria", categoria);
        return "filtrado";
    }

    @GetMapping("/producto/{id}")
    public String detalleProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        return "info"; // Necesitarás crear esta vista si la usas
    }
    
    @GetMapping("/datos")
    public String mostrardatos(Model model) {

        return "datos";
    }
}

