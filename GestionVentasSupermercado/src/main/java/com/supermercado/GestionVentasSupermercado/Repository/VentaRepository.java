package com.supermercado.GestionVentasSupermercado.Repository;

import com.supermercado.GestionVentasSupermercado.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}
