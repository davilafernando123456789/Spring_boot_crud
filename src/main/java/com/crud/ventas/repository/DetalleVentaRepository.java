package com.crud.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.ventas.model.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}