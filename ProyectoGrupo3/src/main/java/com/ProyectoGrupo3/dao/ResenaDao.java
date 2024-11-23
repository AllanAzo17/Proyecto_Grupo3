/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.dao;

import com.ProyectoGrupo3.domain.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResenaDao extends JpaRepository<Resena, Long> {
    
    // Consulta para obtener el promedio de calificación de un producto
    @Query("SELECT AVG(r.calificacion) FROM Resena r WHERE r.producto.idProducto = :idProducto")
    Double obtenerPromedioCalificacionPorProducto(@Param("idProducto") Long idProducto);
}
