package com.supermercado.GestionVentasSupermercado.service;

import com.supermercado.GestionVentasSupermercado.dto.ProductoDTO;
import com.supermercado.GestionVentasSupermercado.exception.DuplicateResourceException;
import com.supermercado.GestionVentasSupermercado.exception.EmptyResourceListException;
import com.supermercado.GestionVentasSupermercado.exception.NotFoundException;
import com.supermercado.GestionVentasSupermercado.model.Producto;
import com.supermercado.GestionVentasSupermercado.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    public ProductoService(ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProductoDTO create(ProductoDTO productoDto) {
        if(productoRepository.existsByNombre(productoDto.getNombre())) {
            throw new DuplicateResourceException("Ya existe un producto con ese nombre.");
        }

        Producto producto = modelMapper.map(productoDto, Producto.class);

        Producto productoGuardado = productoRepository.save(producto);

        return  modelMapper.map(productoGuardado, ProductoDTO.class);
    }

    @Override
    public ProductoDTO getById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto con ID: " + id + "no encontrado."));

        return modelMapper.map(producto, ProductoDTO.class);
    }

    @Override
    public List<ProductoDTO> getAll() {
        List<Producto> productos = productoRepository.findAll();

        if (productos.isEmpty()) {
            throw new EmptyResourceListException("Aun no hay productos agregados.");
        }

        return productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoDTO.class))
                .toList();
    }

    @Override
    public ProductoDTO updateById(Long id, ProductoDTO productoDto) {
        Producto productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto con ID: " + id + ", no encontrado."));

        productoEncontrado.setNombre(productoDto.getNombre());
        productoEncontrado.setCategoria(productoDto.getCategoria());
        productoEncontrado.setPrecio(productoDto.getPrecio());
        productoEncontrado.setCantidad(productoDto.getCantidad());

        Producto actualizado = productoRepository.save(productoEncontrado);

        return modelMapper.map(actualizado, ProductoDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NotFoundException("Producto con ID: " + id + ", no existe!");
        }
        productoRepository.deleteById(id);
    }
}
