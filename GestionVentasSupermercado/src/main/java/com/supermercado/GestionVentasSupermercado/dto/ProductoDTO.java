package com.supermercado.GestionVentasSupermercado.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String categoria;
    private int cantidad;
}
