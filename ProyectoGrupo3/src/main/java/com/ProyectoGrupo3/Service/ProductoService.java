/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.Service;

import com.ProyectoGrupo3.domain.Producto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {

    public List<Producto> getProductos(boolean activo);

    public Producto getProducto(Producto producto);

    public void save(Producto producto);

    public void delete(Producto producto);

    public Producto getProductoById(Long idProducto);

    // Nuevo método para obtener productos paginados
    public Page<Producto> getProductosPaginados(Pageable pageable, boolean activo);
}
