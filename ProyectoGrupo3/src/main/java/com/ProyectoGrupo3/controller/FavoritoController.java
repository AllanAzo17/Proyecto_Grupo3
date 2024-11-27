/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.controller;

import com.ProyectoGrupo3.Service.FavoritoService;
import com.ProyectoGrupo3.domain.Favorito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    // Listar todos los favoritos
    @GetMapping
    public String listarFavoritos(Model model) {
        List<Favorito> favoritos = favoritoService.listarFavoritos();
        model.addAttribute("favoritos", favoritos);
        return "favoritos/listado"; // Renderiza la vista de favoritos (favoritos/listado.html)
    }

    // Agregar un producto a favoritos
    @PostMapping("/agregar/{idProducto}")
    public String agregarAFavoritos(@PathVariable Long idProducto) {
        favoritoService.agregarAFavoritos(idProducto);
        return "redirect:/favoritos"; // Redirigir a la lista de favoritos
    }

    // Eliminar un producto de favoritos
    @PostMapping("/eliminar/{idFavorito}")
    public String eliminarDeFavoritos(@PathVariable Long idFavorito) {
        favoritoService.eliminarDeFavoritos(idFavorito);
        return "redirect:/favoritos"; // Redirigir a la lista de favoritos
    }
}