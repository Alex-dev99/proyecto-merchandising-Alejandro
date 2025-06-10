package com.salesianostriana.dam.Merchandising.controller;

import com.salesianostriana.dam.Merchandising.model.Producto;
import com.salesianostriana.dam.Merchandising.model.Categoria;
import com.salesianostriana.dam.Merchandising.service.ProductoService;
import com.salesianostriana.dam.Merchandising.service.CategoriaService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "admin/list-producto";
    }

    @GetMapping("/list-producto")
    public String lista(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "admin/list-producto";
    }
    
    @GetMapping("/todos")
    public String todos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "/todos";
    }

    @GetMapping("/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/form-producto";
    }

    @PostMapping("/submit")
    public String submitProducto(@ModelAttribute Producto producto, Model model) {

       /* if (producto.getFechaAlta() == null) {
            producto.setFechaAlta(LocalDate.now());
        */

        Categoria categoria = categoriaService.findById(producto.getCategoria().getId());
        producto.setCategoria(categoria);

        productoService.save(producto);

        return "redirect:/admin/producto/";
    }


    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.findById(id);

        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaService.findAll());
            return "admin/form-producto";
        } else {
            return "redirect:/admin/producto/list-producto";
        }
    }
    
    @GetMapping("/info/{id}")
    public String infoProducto(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.findById(id);

        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaService.findAll());
            return "info";
        } else {
            return "redirect:/admin/producto/list-producto";
        }
    }
    
    @GetMapping("/borrar/{id}")
    public String borrarProducto(@PathVariable("id") Long id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            productoService.delete(producto);
        }
        return "redirect:/admin/producto/list-producto";
    }
    
    @GetMapping("/categoria/{id}")
    public  String categoriaProducto(@PathVariable("id")long id, Model model) {
    	List<Producto> productos = productoService.findByCategoriaId(id);
    	model.addAttribute("productos", productos);
    	return "filtrado";
    }
    
}
