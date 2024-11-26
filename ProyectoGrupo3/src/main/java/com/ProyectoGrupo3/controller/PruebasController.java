/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.controller;

import com.ProyectoGrupo3.domain.Categoria;
import com.ProyectoGrupo3.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ProyectoGrupo3.Service.ProductoService;
import com.ProyectoGrupo3.domain.Producto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "8") int size,
                          Model model) {
        var pageable = PageRequest.of(page, size);
        Page<Producto> productosPage = productoService.getProductosPaginados(pageable, false);
        var categorias = categoriaService.getCategorias(false);

        model.addAttribute("productos", productosPage.getContent());
        model.addAttribute("totalProductos", productosPage.getTotalElements());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productosPage.getTotalPages());
        model.addAttribute("categorias", categorias);
        model.addAttribute("size", size);
        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "8") int size,
                          Model model, Categoria categoria) {
        var pageable = PageRequest.of(page, size);
        var productos = categoriaService.getCategoria(categoria).getProductos();
        var categorias = categoriaService.getCategorias(false);

        // Implementación de paginación manual para productos por categoría
        int start = page * size;
        int end = Math.min((start + size), productos.size());
        var productosPaginados = productos.subList(start, end);

        model.addAttribute("productos", productosPaginados);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) productos.size() / size));
        model.addAttribute("categorias", categorias);
        model.addAttribute("size", size);
        return "/pruebas/listado";
    }
}
