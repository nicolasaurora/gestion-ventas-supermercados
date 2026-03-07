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
public class SucursalDTO {
    private Long id;
    private String nombre;
    private String direccion;
}
