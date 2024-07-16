package com.crud.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.ventas.model.Venta;
import com.crud.ventas.repository.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta findById(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }
}