package com.ProyectoGrupo3.service.impl;

import com.ProyectoGrupo3.dao.RolDao;
import com.ProyectoGrupo3.domain.Rol;
import com.ProyectoGrupo3.service.RolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImpl 
        implements RolService {

    @Autowired
    private RolDao rolDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Rol> getRoles() {
        var lista = rolDao.findAll();        
               
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Rol getRol(Rol rol) {
        return rolDao.findById(rol.getIdRol()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    @Transactional
    public void delete(Rol rol) {
        rolDao.delete(rol);
    }
}