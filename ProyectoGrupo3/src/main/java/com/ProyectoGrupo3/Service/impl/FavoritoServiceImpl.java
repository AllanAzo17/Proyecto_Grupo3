/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.Service.impl;

import com.ProyectoGrupo3.Service.FavoritoService;
import com.ProyectoGrupo3.domain.Favorito;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Override
    public List<Favorito> gets() {
        return listaFavoritos;
    }

    @Override
    public void save(Favorito favorito) {
        if (!listaFavoritos.stream()
            .anyMatch(f -> Objects.equals(f.getIdProducto(), favorito.getIdProducto()))) {
            listaFavoritos.add(favorito);
        }
    }

    @Override
    public void delete(Favorito favorito) {
        listaFavoritos.removeIf(f -> Objects.equals(f.getIdProducto(), favorito.getIdProducto()));
    }

    @Override
    public Favorito get(Favorito favorito) {
        return listaFavoritos.stream()
            .filter(f -> Objects.equals(f.getIdProducto(), favorito.getIdProducto()))
            .findFirst()
            .orElse(null);
    }
}
