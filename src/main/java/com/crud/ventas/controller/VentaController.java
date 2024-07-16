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

import com.crud.ventas.model.Cliente;
import com.crud.ventas.model.Venta;
import com.crud.ventas.service.VentaService;
import com.crud.ventas.service.ClienteService;

@Controller
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public String listVentas(Model model) {
        List<Venta> ventas = ventaService.findAll();
        model.addAttribute("ventas", ventas);
        return "ventas/ventas";
    }

    @GetMapping("/nuevo")
    public String nuevaVentaForm(Model model) {
        Venta venta = new Venta();
        List<Cliente> clientes = clienteService.findAll(); 
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clientes);
        return "ventas/crearVenta";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("venta") Venta venta) {
        ventaService.save(venta);
        return "redirect:/ventas/";
    }
    
    @GetMapping("/editar/{id}")
    public String editarVentaForm(@PathVariable Long id, Model model) {
        Venta venta = ventaService.findById(id);
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clientes);  
        return "ventas/editarVenta";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarVenta(@PathVariable Long id, @ModelAttribute("venta") Venta venta) {
        Venta ventaExistente = ventaService.findById(id);
        if (ventaExistente != null) {
            ventaExistente.setFecha(venta.getFecha());
            ventaExistente.setSerie(venta.getSerie());
            ventaExistente.setNumero(venta.getNumero());
            ventaExistente.setTotal(venta.getTotal());
            ventaExistente.setCliente(venta.getCliente());
            ventaService.save(ventaExistente);
        }
        return "redirect:/ventas/";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.deleteById(id);
        return "redirect:/ventas/";
    }
}
