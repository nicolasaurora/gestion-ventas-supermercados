package com.supermercado.GestionVentasSupermercado.service;

import com.supermercado.GestionVentasSupermercado.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    ProductoDTO create(ProductoDTO productoDto);
    ProductoDTO getById(Long id);
    List<ProductoDTO> getAll();
    ProductoDTO updateById(Long id, ProductoDTO productoDto);
    void deleteById(Long id);
}
