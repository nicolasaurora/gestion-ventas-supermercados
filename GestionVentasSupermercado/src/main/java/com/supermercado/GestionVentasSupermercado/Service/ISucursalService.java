package com.supermercado.GestionVentasSupermercado.Service;

import com.supermercado.GestionVentasSupermercado.Dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {

    SucursalDTO create(SucursalDTO sucursalDto);
    SucursalDTO getById(Long id);
    List<SucursalDTO> getAll();
    SucursalDTO updateById(Long id, SucursalDTO sucursalDto);
    void deleteById(Long id);
}
