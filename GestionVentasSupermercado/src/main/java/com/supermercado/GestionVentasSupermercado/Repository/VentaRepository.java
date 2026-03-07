package com.supermercado.GestionVentasSupermercado.Repository;

import com.supermercado.GestionVentasSupermercado.Dto.VentaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VentaRepository extends JpaRepository<VentaDTO, Long> {

}
