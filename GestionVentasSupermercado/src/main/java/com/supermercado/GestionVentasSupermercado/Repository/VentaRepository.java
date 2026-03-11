package com.supermercado.GestionVentasSupermercado.Repository;

import com.supermercado.GestionVentasSupermercado.Model.Sucursal;
import com.supermercado.GestionVentasSupermercado.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findBySucursal(Sucursal sucursal);
}
