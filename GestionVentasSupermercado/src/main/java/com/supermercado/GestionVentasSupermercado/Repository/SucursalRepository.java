package com.supermercado.GestionVentasSupermercado.Repository;

import com.supermercado.GestionVentasSupermercado.Dto.SucursalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalDTO, Long> {

}
