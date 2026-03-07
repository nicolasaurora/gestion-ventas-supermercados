package com.supermercado.GestionVentasSupermercado.Service;

import com.supermercado.GestionVentasSupermercado.Dto.ProductoDTO;
import com.supermercado.GestionVentasSupermercado.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public ProductoDTO create(ProductoDTO productoDto) {
        return productoRepository.save(productoDto);
    }

    @Override
    public ProductoDTO getById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto con ID: " + id + "no encontrado."));
    }

    @Override
    public List<ProductoDTO> getAll() {
        List<ProductoDTO> productosEncontrados = productoRepository.findAll();
        if (productosEncontrados.isEmpty()) {
            throw new RuntimeException("Aun no hay productos agregados.");
        }
        return productosEncontrados;
    }

    @Override
    public ProductoDTO updateById(Long id, ProductoDTO productoDto) {
        return productoRepository.findById(id).map(prodEncontrado -> {
            prodEncontrado.setCategoria(productoDto.getCategoria());
            prodEncontrado.setNombre(productoDto.getNombre());
            prodEncontrado.setPrecio(productoDto.getPrecio());
            prodEncontrado.setCantidad(productoDto.getCantidad());

            return productoRepository.save(prodEncontrado);
        }).orElseThrow(() -> new RuntimeException("Producto con ID: " + id + "no encontrado."));
    }

    @Override
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto con ID: " + id + ", no existe!");
        }
        productoRepository.deleteById(id);
    }
}
