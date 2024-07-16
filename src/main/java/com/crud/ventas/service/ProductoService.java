package com.crud.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.ventas.model.Producto;
import com.crud.ventas.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }
}