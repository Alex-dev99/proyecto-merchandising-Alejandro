package com.salesianostriana.dam.Merchandising.controller;

import com.salesianostriana.dam.Merchandising.model.Categoria;
import com.salesianostriana.dam.Merchandising.service.CategoriaService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/list-categoria")
    public String index(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "admin/list-categoria";
    }

    @GetMapping("/nueva")
    public String nuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/form-categoria";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "admin/form-categoria";
        } else {
            return "redirect:/admin/categoria/list-categoria";
        }
    }

    @PostMapping("/submit")
    public String submitCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/admin/categoria/list-categoria";
    }

    @GetMapping("/borrar/{id}")
    public String borrarCategoria(@PathVariable("id") Long id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria != null) {
            categoriaService.delete(categoria);
        }
        return "redirect:/admin/categoria/list-categoria";
    }
}
