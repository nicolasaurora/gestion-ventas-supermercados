package com.supermercado.GestionVentasSupermercado.service;

import com.supermercado.GestionVentasSupermercado.dto.VentaDTO;
import com.supermercado.GestionVentasSupermercado.model.Sucursal;

import java.util.List;

public interface IVentaService {

    VentaDTO create(VentaDTO ventaDto);
    VentaDTO getVentaById(Long id);
    List<VentaDTO> getAll();
    List<VentaDTO> getVentasBySucursal(Long sucursalId);
    void deleteById(Long id);
}
