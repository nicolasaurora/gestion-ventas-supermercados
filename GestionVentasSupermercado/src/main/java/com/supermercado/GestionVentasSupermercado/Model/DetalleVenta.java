package com.supermercado.GestionVentasSupermercado.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Venta
    @ManyToOne
    private Venta venta;

    // Producto
    @ManyToOne
    private Producto producto;
    private Integer cantidadProd;
    private Double precio;


}
