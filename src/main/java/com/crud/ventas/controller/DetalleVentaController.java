package com.crud.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.ventas.model.DetalleVenta;
import com.crud.ventas.model.Producto;
import com.crud.ventas.model.Venta;
import com.crud.ventas.service.DetalleVentaService;
import com.crud.ventas.service.ProductoService;
import com.crud.ventas.service.VentaService;

@Controller
@RequestMapping("/detalleVentas")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService detalleVentaService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private VentaService ventaService;

    @GetMapping("/")
    public String listDetalleVentas(Model model) {
        List<DetalleVenta> detalleVentas = detalleVentaService.findAll();
        model.addAttribute("detalleVentas", detalleVentas);
        return "detalleventas/detalleVentas";
    }

    @GetMapping("/nuevo")
    public String nuevoDetalleVentaForm(Model model) {
        DetalleVenta detalleVenta = new DetalleVenta();
        List<Producto> productos = productoService.findAll();
        List<Venta> ventas = ventaService.findAll();
        model.addAttribute("detalleVenta", detalleVenta);
        model.addAttribute("productos", productos);
        model.addAttribute("ventas", ventas);
        return "detalleventas/crearDetalleVenta";
    }

    @PostMapping("/guardar")
    public String guardarDetalleVenta(@ModelAttribute("detalleVenta") DetalleVenta detalleVenta) {
        detalleVentaService.save(detalleVenta);
        return "redirect:/detalleVentas/";
    }

    @GetMapping("/editar/{id}")
    public String editarDetalleVentaForm(@PathVariable Long id, Model model) {
        DetalleVenta detalleVenta = detalleVentaService.findById(id);
        List<Producto> productos = productoService.findAll();
        List<Venta> ventas = ventaService.findAll();
        model.addAttribute("detalleVenta", detalleVenta);
        model.addAttribute("productos", productos);
        model.addAttribute("ventas", ventas);
        return "detalleventas/editarDetalleVenta";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarDetalleVenta(@PathVariable Long id, @ModelAttribute("detalleVenta") DetalleVenta detalleVenta) {
        DetalleVenta detalleVentaExistente = detalleVentaService.findById(id);
        detalleVentaExistente.setCantidad(detalleVenta.getCantidad());
        detalleVentaExistente.setPrecio(detalleVenta.getPrecio());
        detalleVentaExistente.setSubTotal(detalleVenta.getSubTotal());
        detalleVentaExistente.setVenta(detalleVenta.getVenta());
        detalleVentaExistente.setProducto(detalleVenta.getProducto());
        detalleVentaService.save(detalleVentaExistente);
        return "redirect:/detalleVentas/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDetalleVenta(@PathVariable Long id) {
        detalleVentaService.deleteById(id);
        return "redirect:/detalleVentas/";
    }
}
