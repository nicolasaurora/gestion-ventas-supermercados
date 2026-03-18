package com.supermercado.GestionVentasSupermercado.controller;


import com.supermercado.GestionVentasSupermercado.dto.VentaDTO;
import com.supermercado.GestionVentasSupermercado.service.IVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    private IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listarTodas() {
        return ResponseEntity.ok(ventaService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<VentaDTO> obtenerPorId(@PathVariable Long id) {
        VentaDTO venta = ventaService.getVentaById(id);
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<List<VentaDTO>> obtenerPorSucursal(@PathVariable Long sucursalId) {
        List<VentaDTO> ventas = ventaService.getVentasBySucursal(sucursalId);
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO ventaDTO) {
        VentaDTO nuevaVenta = ventaService.create(ventaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ventaService.deleteById(id);
        return ResponseEntity.ok("Venta con ID: " + id + " Eliminada con exito.");
    }

}
