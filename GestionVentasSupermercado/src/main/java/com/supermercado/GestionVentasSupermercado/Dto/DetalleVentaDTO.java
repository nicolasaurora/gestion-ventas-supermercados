package com.supermercado.GestionVentasSupermercado.Dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class DetalleVentaDTO {
    private Long id;
    private String nombreProducto;
    private Integer cantProducto;
    private Double precio;
    private Double subtotal;
}
