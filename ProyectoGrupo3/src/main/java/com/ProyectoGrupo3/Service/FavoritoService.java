/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.Service;

import com.ProyectoGrupo3.domain.Favorito;
import java.util.List;
    
public interface FavoritoService {
    void agregarAFavoritos(Long idProducto);
    void eliminarDeFavoritos(Long idFavorito);
    List<Favorito> listarFavoritos();
    Favorito obtenerPorId(Long idFavorito);
}


