/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.Service;

import com.ProyectoGrupo3.domain.Favorito;
import java.util.ArrayList;
import java.util.List;

public interface FavoritoService {
    List<Favorito> listaFavoritos = new ArrayList<>();

    List<Favorito> gets();

    Favorito get(Favorito favorito);

    void delete(Favorito favorito);

    void save(Favorito favorito);
}



