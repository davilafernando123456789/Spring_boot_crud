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
import com.crud.ventas.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public String listClientes(Model model) {
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes/clientes";
    }

    @GetMapping("/nuevo")
    public String nuevoClienteForm(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "clientes/crearCliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.save(cliente);
        return "redirect:/clientes/";
    }

    @GetMapping("/editar/{id}")
    public String editarClienteForm(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.findById(id);
        model.addAttribute("cliente", cliente);
        return "clientes/editarCliente";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute("cliente") Cliente cliente) {
        Cliente clienteExistente = clienteService.findById(id);
        clienteExistente.setIdCliente(id);
        clienteExistente.setNombres(cliente.getNombres());
        clienteExistente.setApellidos(cliente.getApellidos());
        clienteExistente.setNroDocumento(cliente.getNroDocumento());
        clienteExistente.setEmail(cliente.getEmail());
        clienteService.save(clienteExistente);
        return "redirect:/clientes/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return "redirect:/clientes/";
    }
}
