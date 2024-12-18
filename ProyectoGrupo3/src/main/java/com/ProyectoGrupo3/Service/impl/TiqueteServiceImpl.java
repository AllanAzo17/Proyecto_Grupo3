/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.Service.impl;

import com.ProyectoGrupo3.dao.TiqueteDao;
import com.ProyectoGrupo3.domain.Tiquete;
import com.ProyectoGrupo3.Service.TiqueteService;
import com.ProyectoGrupo3.Service.UsuarioService;
import com.ProyectoGrupo3.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class TiqueteServiceImpl implements TiqueteService {

    @Autowired
    private TiqueteDao tiqueteDao;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public List<Tiquete> getTiquetes() {
        return tiqueteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tiquete getTiquete(Long idTiquete) {
        return tiqueteDao.findById(idTiquete).orElse(null);
    }

    @Override
    @Transactional
    public void save(Tiquete tiquete) {
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

        tiquete.setUsuario(usuario);

        tiqueteDao.save(tiquete);
    }

    @Override
    @Transactional
    public void delete(Tiquete tiquete) {
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

        tiqueteDao.delete(tiquete);
    }

    @Override
    @Transactional
    public List<Tiquete> getTiquetesPorUsuario(Long idUsuario) {
        return tiqueteDao.findByUsuarioIdUsuario(idUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tiquete> filtrarPorEstado(String estado) {
        return tiqueteDao.filtrarPorEstado(estado);
    }

}
