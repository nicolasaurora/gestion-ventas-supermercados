package com.supermercado.GestionVentasSupermercado.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter @Setter
public class VentaDTO {
    //Datos de la venta
    private Long id;
    private LocalDate fecha;
    private String estado;

    //Datos de la sucursal
    private Long idSucursal;

    // Lista detalle venta
    private List<DetalleVentaDTO> detalle;

    // Total de la venta
    private Double total;
}
