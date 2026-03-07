package com.supermercado.GestionVentasSupermercado.Service;

import com.supermercado.GestionVentasSupermercado.Dto.SucursalDTO;
import com.supermercado.GestionVentasSupermercado.Dto.VentaDTO;

import java.util.List;

public class VentaService implements IVentaService {
    @Override
    public VentaDTO create(VentaDTO ventaDto) {
        return null;
    }

    @Override
    public VentaDTO getVentaById(Long id) {
        return null;
    }

    @Override
    public List<VentaDTO> getAll() {
        return List.of();
    }

    @Override
    public List<VentaDTO> getVentasBySucursal(SucursalDTO sucursaldto) {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
