package com.supermercado.GestionVentasSupermercado.repository;

import com.supermercado.GestionVentasSupermercado.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    boolean existsByNombre(String nombre);

}
