/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.Service;

import com.ProyectoGrupo3.domain.Tiquete;
import java.util.List;

public interface TiqueteService {

    public List<Tiquete> getTiquetes();

    public Tiquete getTiquete(Long idTiquete);

    public void save(Tiquete tiquete);

    public void delete(Tiquete tiquete);
    
    public List<Tiquete> getTiquetesPorUsuario(Long idUsuario);
    
    public List<Tiquete> filtrarPorEstado(String estado);


}