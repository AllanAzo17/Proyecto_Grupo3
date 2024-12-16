/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.dao;

import com.ProyectoGrupo3.domain.Tiquete;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TiqueteDao extends JpaRepository<Tiquete, Long> {

    List<Tiquete> findByUsuarioIdUsuario(Long idUsuario);

    @Query(value = "SELECT t FROM Tiquete t WHERE t.estado = :estado")
    public List<Tiquete> filtrarPorEstado(@Param("estado") String estado);

}
