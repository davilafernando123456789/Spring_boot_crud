package com.crud.ventas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.crud.ventas.model.Producto;
import com.crud.ventas.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String listProductos(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "productos/productos";
    }

    @GetMapping("/nuevo")
    public String nuevoProductoForm(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        return "productos/crearProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
     
        producto.setEstado(Byte.valueOf(producto.getEstado()));
        productoService.save(producto);
        return "redirect:/productos/";
    }

    @GetMapping("/editar/{id}")
    public String editarProductoForm(@PathVariable Integer id, Model model) {
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        return "productos/editarProducto";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Integer id, @ModelAttribute("producto") Producto producto) {
        Producto productoExistente = productoService.findById(id);
        productoExistente.setIdProducto(id);
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setCantidad(producto.getCantidad());
        productoExistente.setEstado(Byte.valueOf(producto.getEstado())); // Convertir a Byte aquí también
        productoService.save(productoExistente);
        return "redirect:/productos/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.deleteById(id);
        return "redirect:/productos/";
    }
}
