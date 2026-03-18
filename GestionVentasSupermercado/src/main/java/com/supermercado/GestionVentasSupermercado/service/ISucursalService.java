package com.supermercado.GestionVentasSupermercado.service;

import com.supermercado.GestionVentasSupermercado.dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {

    SucursalDTO create(SucursalDTO sucursalDto);
    SucursalDTO getById(Long id);
    List<SucursalDTO> getAll();
    SucursalDTO updateById(Long id, SucursalDTO sucursalDto);
    void deleteById(Long id);
}
