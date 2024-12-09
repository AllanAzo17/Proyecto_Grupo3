/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.Service.impl;

import com.ProyectoGrupo3.Service.ProductoService;
import com.ProyectoGrupo3.dao.ResenaDao;
import com.ProyectoGrupo3.domain.Resena;
import com.ProyectoGrupo3.Service.ResenaService;
import com.ProyectoGrupo3.Service.UsuarioService;
import com.ProyectoGrupo3.domain.Producto;
import com.ProyectoGrupo3.domain.Usuario;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResenaServiceImpl implements ResenaService {

    @Autowired
    private ResenaDao resenaDao;    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public List<Resena> getResenas() {
        return resenaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Resena getResena(Resena resena) {
        return resenaDao.findById(resena.getIdResena()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Resena resena, Long idProducto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        if (username == null || username.isBlank()) {
            throw new IllegalStateException("Usuario no autenticado.");
        }

        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        if (usuario == null) {
            throw new IllegalStateException("Usuario no encontrado.");
        }

        Producto producto = productoService.getProductoById(idProducto);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado.");
        }

        resena.setUsuario(usuario);
        resena.setProducto(producto);
        resena.setFecha(new Date());

        // Guardar la reseña
        resenaDao.save(resena);
    }

    @Override
    @Transactional
    public void delete(Long idResena) {
        resenaDao.deleteById(idResena);
    }

    @Override
    @Transactional(readOnly = true)
    public Double obtenerPromedioCalificacionPorProducto(Long idProducto) {
        return resenaDao.obtenerPromedioCalificacionPorProducto(idProducto);
    }

}
