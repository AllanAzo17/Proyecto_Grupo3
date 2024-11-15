/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.controller;

import com.ProyectoGrupo3.domain.Producto;
import com.ProyectoGrupo3.Service.CategoriaService;
import com.ProyectoGrupo3.Service.ProductoService;
import com.ProyectoGrupo3.Service.ResenaService;
import com.ProyectoGrupo3.Service.impl.FirebaseStorageServiceImpl;
import com.ProyectoGrupo3.domain.Resena;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto")
public class ProductoController {
  
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ResenaService resenaService;
    
    @GetMapping("/listado")
    private String listado(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        
        model.addAttribute("totalProductos",productos.size());
        return "/producto/listado";
    }
    
     @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "producto", 
                            producto.getIdProducto()));
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        
        return "/producto/modifica";
    }   
    
@GetMapping("/resenas/{idProducto}")
public String verResenas(@PathVariable("idProducto") Long idProducto, Model model) {
    // Obtiene el producto por su ID
    Producto producto = productoService.getProductoById(idProducto);
    
    // Obtiene la lista de reseñas asociadas al producto
    List<Resena> resenas = producto.getResenas();
    
    // Agrega el producto y las reseñas al modelo para que estén disponibles en la vista
    model.addAttribute("producto", producto);
    model.addAttribute("resenas", resenas);
    
    // Retorna la vista donde se mostrarán las reseñas del producto
    return "producto/resenas";
}

   // Método para mostrar el formulario para agregar una reseña
    @GetMapping("/resenas/agregar/{idProducto}")
    public String mostrarFormularioAgregar(@PathVariable Long idProducto, Model model) {
        Producto producto = productoService.getProductoById(idProducto);
        model.addAttribute("producto", producto);
        model.addAttribute("resena", new Resena());
        return "producto/formulario"; // Vista del formulario de agregar reseña
    }

    // Método para guardar la reseña en la base de datos
    @PostMapping("/resenas/guardar")
    public String guardarResena(Resena resena, @RequestParam Long idProducto) {
        Producto producto = productoService.getProductoById(idProducto);
        resena.setProducto(producto);
        resena.setFecha(new Date()); // Asigna la fecha actual
        resenaService.save(resena);
        return "redirect:/producto/resenas/" + idProducto; // Redirige a la vista de reseñas del producto
    }

    // Método para borrar una reseña
    @GetMapping("/resenas/eliminar/{idResena}")
    public String eliminarResena(@PathVariable Long idResena, @RequestParam Long idProducto) {
        resenaService.delete(idResena);
        return "redirect:/producto/resenas/" + idProducto; // Redirige a la vista de reseñas del producto
    }

}
