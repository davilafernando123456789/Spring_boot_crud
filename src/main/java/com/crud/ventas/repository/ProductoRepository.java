package com.crud.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.ventas.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
