package com.supermercado.GestionVentasSupermercado.Dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
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
