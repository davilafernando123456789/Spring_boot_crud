package com.crud.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.ventas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}