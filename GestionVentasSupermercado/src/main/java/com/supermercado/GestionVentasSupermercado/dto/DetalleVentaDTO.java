package com.supermercado.GestionVentasSupermercado.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DetalleVentaDTO {
    private Long id;
    private String nombreProducto;
    private Integer cantProducto;
    private Double precio;
    private Double subtotal;
}
