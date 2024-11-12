/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.Service;

import com.ProyectoGrupo3.domain.Resena;
import java.util.List;

public interface ResenaService {

    public List<Resena> getResenas();

    public Resena getResena(Resena resena);
    
    public void save(Resena resena);
    
    public void delete(Long idResena);
    
}