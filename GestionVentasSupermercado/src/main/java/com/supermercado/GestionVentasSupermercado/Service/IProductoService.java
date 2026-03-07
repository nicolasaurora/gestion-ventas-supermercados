package com.supermercado.GestionVentasSupermercado.Service;

import com.supermercado.GestionVentasSupermercado.Dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    ProductoDTO create(ProductoDTO productoDto);
    ProductoDTO getById(Long id);
    List<ProductoDTO> getAll();
    ProductoDTO updateById(Long id, ProductoDTO productoDto);
    void deleteById(Long id);
}
