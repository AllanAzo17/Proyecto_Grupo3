/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.controller;

import com.ProyectoGrupo3.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/registro")
public class RegistroController {
    
    
    @GetMapping("/nuevo")
    public String nuevo(Model model, Usuario usuario) {
        return "/registro/nuevo";
    }
    
    @PostMapping("/registro/crear")
    public String crear(Model model, Usuario usuario) {
        
        
        model.addAttribute("titulo", usuario.getUsername());
        model.addAttribute("mensaje", "Porfavor verificar el correo, para recibir el mensaje de activación");
        
        return "/registro/salida";
    }
}