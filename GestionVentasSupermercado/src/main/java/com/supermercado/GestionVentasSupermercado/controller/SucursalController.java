package com.supermercado.GestionVentasSupermercado.controller;

import com.supermercado.GestionVentasSupermercado.dto.SucursalDTO;
import com.supermercado.GestionVentasSupermercado.service.ISucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    private ISucursalService sucursalService;

    public SucursalController(ISucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> listarTodos() {
        return ResponseEntity.ok(sucursalService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SucursalDTO> obtenerPorId(@PathVariable Long id) {
        SucursalDTO sucursal = sucursalService.getById(id);
        return ResponseEntity.ok(sucursal);
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crear(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO nuevasucursal = sucursalService.create(sucursalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevasucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizar(@PathVariable Long id, @RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO sucursalActualizada = sucursalService.updateById(id, sucursalDTO);
        return ResponseEntity.ok(sucursalActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        sucursalService.deleteById(id);
        return ResponseEntity.ok("sucursal con ID: " + id + " Eliminada correctamente.");
    }
}
