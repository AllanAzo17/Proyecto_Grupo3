/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ProyectoGrupo3.Service;

import com.ProyectoGrupo3.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    // Se obtiene un listado de categorias en un List
    public List<Categoria> getCategorias(boolean activos);
    
    // Se obtiene un Categoria, a partir del id de una categoria
    public Categoria getCategoria(Categoria categoria);
    
    // Se inserta un nueva categoria si el id de la categoria esta vacio
    //Se actualiza una categoria si el id de la categoria NO esta vacia
    public void save(Categoria categoria);
    
    // Se elimina la categoria que tiene el id pasado por parámetro
    public void delete(Categoria categoria);
   

    
}
