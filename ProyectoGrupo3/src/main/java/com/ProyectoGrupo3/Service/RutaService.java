package com.ProyectoGrupo3.service;

import com.ProyectoGrupo3.domain.Ruta;
import java.util.List;

public interface RutaService {
    
    // Se obtiene un listado de rutas en un List
    public List<Ruta> getRutas();
    
    // Se obtiene un Ruta, a partir del id de un rutae
    public Ruta getRuta(Ruta ruta);
       
    public void save(Ruta ruta);
    
    // Se elimina el ruta que tiene el id pasado por parámetro
    public void delete(Ruta ruta);
    
}