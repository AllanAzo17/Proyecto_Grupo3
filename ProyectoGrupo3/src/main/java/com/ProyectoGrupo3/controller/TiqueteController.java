/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.controller;

import com.ProyectoGrupo3.Service.TiqueteService;
import com.ProyectoGrupo3.Service.UsuarioService;
import com.ProyectoGrupo3.domain.Tiquete;
import com.ProyectoGrupo3.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/tiquete")
public class TiqueteController {

    @Autowired
    private TiqueteService tiqueteService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        List<Tiquete> tiquetes;

        if (isAdmin) {
            tiquetes = tiqueteService.getTiquetes();
        } else {
            Usuario usuario = usuarioService.getUsuarioPorUsername(username);
            tiquetes = tiqueteService.getTiquetesPorUsuario(usuario.getIdUsuario());
        }

        model.addAttribute("tiquetes", tiquetes);
        model.addAttribute("totalTiquetes", tiquetes.size());

        return "/tiquete/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        Tiquete nuevoTiquete = new Tiquete();
        nuevoTiquete.setEstado("NUEVO");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        nuevoTiquete.setUsuario(usuario);
        model.addAttribute("tiquete", nuevoTiquete);
        return "/tiquete/crear";
    }

    @PostMapping("/guardar")
    public String guardarTiquete(Tiquete tiquete) {
        if (tiquete.getIdTiquete() == null) {
            tiqueteService.save(tiquete);
        } else {
            Tiquete tiqueteExistente = tiqueteService.getTiquete(tiquete.getIdTiquete());
            if (tiqueteExistente == null) {
                throw new RuntimeException("El tiquete no existe.");
            }

            tiqueteExistente.setUsuario(tiqueteExistente.getUsuario());

            tiqueteExistente.setDescripcionProblema(tiquete.getDescripcionProblema());
            tiqueteExistente.setDescripcionSolucion(tiquete.getDescripcionSolucion());
            tiqueteExistente.setEstado(tiquete.getEstado());

            tiqueteService.save(tiqueteExistente);
        }
        return "redirect:/tiquete/listado";
    }

    @GetMapping("/modificar/{idTiquete}")
    public String modificarTiquete(Tiquete tiquete, Model model) {
        tiquete = tiqueteService.getTiquete(tiquete.getIdTiquete());
        if (tiquete == null) {
            throw new IllegalArgumentException("Tiquete no encontrado.");
        }
        model.addAttribute("tiquete", tiquete);
        return "/tiquete/modifica";
    }

    @GetMapping("/eliminar/{idTiquete}")
    public String eliminarTiquete(Tiquete tiquete) {
        tiqueteService.delete(tiquete);
        return "redirect:/tiquete/listado";
    }

    @PostMapping("/filtrarEstado")
    public String filtrarPorEstado(@RequestParam(value = "estado") String estado, Model model) {
        var tiquetes = tiqueteService.filtrarPorEstado(estado);
        model.addAttribute("tiquetes", tiquetes);
        model.addAttribute("totalTiquetes", tiquetes.size());
        model.addAttribute("estadoSeleccionado", estado);
        return "/tiquete/listado";
    }

}
