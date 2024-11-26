/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.Service.impl;

import com.ProyectoGrupo3.dao.FavoritoDao;
import com.ProyectoGrupo3.Service.FavoritoService;
import com.ProyectoGrupo3.Service.ProductoService;
import com.ProyectoGrupo3.domain.Favorito;
import com.ProyectoGrupo3.domain.Producto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoDao favoritoDao;

    @Autowired
    private ProductoService productoService;

    @Override
    public void agregarAFavoritos(Long idProducto) {
        Producto producto = productoService.getProductoById(idProducto);
        if (producto == null) {
            throw new IllegalArgumentException("El producto con ID " + idProducto + " no existe.");
        }

        Favorito favorito = new Favorito();
        favorito.setProducto(producto);
        favorito.setFechaAgregado(LocalDateTime.now());
        favoritoDao.save(favorito); // Ajustado el uso de FavoritoDao
    }

    @Override
    public void eliminarDeFavoritos(Long idFavorito) {
        if (!favoritoDao.existsById(idFavorito)) {
            throw new IllegalArgumentException("El favorito con ID " + idFavorito + " no existe.");
        }
        favoritoDao.deleteById(idFavorito); // Ajustado el uso de FavoritoDao
    }

    @Override
    public List<Favorito> listarFavoritos() {
        return favoritoDao.findAll(); // Ajustado el uso de FavoritoDao
    }

    @Override
    public Favorito obtenerPorId(Long idFavorito) {
        return favoritoDao.findById(idFavorito)
                .orElseThrow(() -> new IllegalArgumentException("El favorito con ID " + idFavorito + " no existe."));
    }
}
