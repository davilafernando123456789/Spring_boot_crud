package com.crud.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.ventas.model.DetalleVenta;
import com.crud.ventas.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    public DetalleVenta findById(Long id) {
        return detalleVentaRepository.findById(id).orElse(null);
    }

    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public void deleteById(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}