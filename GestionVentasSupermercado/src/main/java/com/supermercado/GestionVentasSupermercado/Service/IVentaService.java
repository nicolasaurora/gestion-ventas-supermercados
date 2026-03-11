package com.supermercado.GestionVentasSupermercado.Service;

import com.supermercado.GestionVentasSupermercado.Dto.VentaDTO;
import com.supermercado.GestionVentasSupermercado.Model.Sucursal;

import java.util.List;

public interface IVentaService {

    VentaDTO create(VentaDTO ventaDto);
    VentaDTO getVentaById(Long id);
    List<VentaDTO> getAll();
    List<VentaDTO> getVentasBySucursal(Sucursal sucursal);
    void deleteById(Long id);
}
