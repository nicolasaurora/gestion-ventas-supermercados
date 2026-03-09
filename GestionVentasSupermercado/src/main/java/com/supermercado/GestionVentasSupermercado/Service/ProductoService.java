package com.supermercado.GestionVentasSupermercado.Service;

import com.supermercado.GestionVentasSupermercado.Dto.ProductoDTO;
import com.supermercado.GestionVentasSupermercado.Model.Producto;
import com.supermercado.GestionVentasSupermercado.Repository.ProductoRepository;
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
        if(productoRepository.existByNombre(productoDto.getNombre())) {
            throw new RuntimeException("Ya existe un producto con ese nombre.");
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
            throw new RuntimeException("Aun no hay productos agregados.");
        }

        return productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoDTO.class))
                .toList();
    }

    @Override
    public ProductoDTO updateById(Long id, ProductoDTO productoDto) {
        Producto productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto con ID: " + id + ", no encontrado."));

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
            throw new RuntimeException("Producto con ID: " + id + ", no existe!");
        }
        productoRepository.deleteById(id);
    }
}
