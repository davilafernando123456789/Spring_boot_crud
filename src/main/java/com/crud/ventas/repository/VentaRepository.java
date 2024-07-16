package com.crud.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.ventas.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
