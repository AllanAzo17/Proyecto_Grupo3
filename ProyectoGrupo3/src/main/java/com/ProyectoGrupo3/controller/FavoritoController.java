/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.controller;

import com.ProyectoGrupo3.Service.FavoritoService;
import com.ProyectoGrupo3.domain.Favorito;
import com.ProyectoGrupo3.domain.Producto;
import com.ProyectoGrupo3.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/favoritos/listado")
    public String listadoFavoritos(Model model) {
        var favoritos = favoritoService.gets();
        model.addAttribute("favoritos", favoritos);
        return "/favoritos/listado";
    }

    @PostMapping("/favoritos/agregar/{idProducto}")
    public String agregarFavorito(@PathVariable Long idProducto, RedirectAttributes redirectAttributes) {
        Producto producto = productoService.getProductoById(idProducto);
        if (producto == null) {
            redirectAttributes.addFlashAttribute("error", "El producto no existe.");
            return "redirect:/productos/listado";
        }

        Favorito favorito = new Favorito(producto, java.time.LocalDate.now().toString());

        favoritoService.save(favorito);

        redirectAttributes.addFlashAttribute("mensaje", "Producto agregado a favoritos con éxito.");
        return "redirect:/favoritos/listado";
    }

    @PostMapping("/favoritos/eliminar/{idProducto}")
    public String eliminarFavorito(@PathVariable Long idProducto) {
        Favorito favorito = new Favorito();
        favorito.setIdProducto(idProducto);
        favoritoService.delete(favorito);
        return "redirect:/favoritos/listado";
    }

}
