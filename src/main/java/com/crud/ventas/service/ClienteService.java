package com.crud.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.ventas.model.Cliente;
import com.crud.ventas.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
