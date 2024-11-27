/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.Service.impl;

import com.ProyectoGrupo3.dao.ResenaDao;
import com.ProyectoGrupo3.domain.Resena;
import com.ProyectoGrupo3.Service.ResenaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResenaServiceImpl implements ResenaService {

     @Autowired
    private ResenaDao resenaDao;

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
    public void save(Resena resena) {
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