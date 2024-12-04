/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.dao;

import com.ProyectoGrupo3.domain.Favorito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoDao extends JpaRepository<Favorito, Long> {
}

