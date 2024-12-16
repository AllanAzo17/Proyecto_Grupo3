/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoGrupo3.controller;

import com.ProyectoGrupo3.domain.Producto;
import com.ProyectoGrupo3.domain.Item;
import com.ProyectoGrupo3.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ProyectoGrupo3.Service.ProductoService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    private String listado(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/index";
    }

    //Para ver el carrito
    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        var carritoTotalVenta = 0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal",
                carritoTotalVenta);
        return "/carrito/listado";
    }

    //Para Agregar un producto al carrito
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregarItem(Model model, Item item) {
        Item item2 = itemService.get(item);
        if (item2 == null) {
            Producto producto = productoService.getProducto(item);
            item2 = new Item(producto);
        }
        itemService.save(item2);
        var lista = itemService.gets();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    //Para mofificar un producto del carrito
    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificarItem(Item item, Model model) {
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modificar";
    }

    //Para eliminar un elemento del carrito
    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    //Para actualizar un producto del carrito (cantidad)
    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item) {
        itemService.actualiza(item);
        return "redirect:/carrito/listado";
    }

    //Para facturar los productos del carrito... no implementado...
    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }

    @PostMapping("/carrito/agregarDesdeFavoritos/{idProducto}")
    public String agregarDesdeFavoritos(@PathVariable Long idProducto, Model model) {
        // Recuperar el producto usando ProductoService
        Producto producto = productoService.getProductoById(idProducto);

        if (producto == null) {
            model.addAttribute("error", "El producto no existe.");
            return "redirect:/favoritos/listado";
        }

        // Crear un nuevo item para el carrito
        Item item = new Item(producto);
        item.setCantidad(1); // Agregar una unidad por defecto al carrito

        // Guardar el producto en el carrito usando ItemService
        itemService.save(item);

        model.addAttribute("mensaje", "Producto agregado al carrito con éxito.");
        return "redirect:/favoritos/listado";
    }

}
