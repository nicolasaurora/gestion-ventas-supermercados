package com.supermercado.GestionVentasSupermercado.service;

import com.supermercado.GestionVentasSupermercado.dto.DetalleVentaDTO;
import com.supermercado.GestionVentasSupermercado.dto.VentaDTO;
import com.supermercado.GestionVentasSupermercado.exception.EmptyResourceListException;
import com.supermercado.GestionVentasSupermercado.exception.NotFoundException;
import com.supermercado.GestionVentasSupermercado.model.Sucursal;
import com.supermercado.GestionVentasSupermercado.model.Venta;
import com.supermercado.GestionVentasSupermercado.repository.SucursalRepository;
import com.supermercado.GestionVentasSupermercado.repository.VentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {
    private final VentaRepository ventaRepository;
    private final SucursalRepository sucursalRepository;
    private final ModelMapper modelMapper;

    public VentaService(VentaRepository ventaRepository, SucursalRepository sucursalRepository, ModelMapper modelMapper) {
        this.ventaRepository = ventaRepository;
        this.sucursalRepository = sucursalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VentaDTO create(VentaDTO ventaDto) {
        ventaDto.getDetalle()
                .forEach(d -> d.setSubtotal(d.getPrecio() * d.getCantProducto()));

        Double total = ventaDto.getDetalle()
                .stream()
                .mapToDouble(DetalleVentaDTO::getSubtotal)
                .sum();
        ventaDto.setTotal(total);

        Venta venta = modelMapper.map(ventaDto, Venta.class);

        Venta ventaNueva = ventaRepository.save(venta);

        return modelMapper.map(ventaNueva, VentaDTO.class);
    }

    @Override
    public VentaDTO getVentaById(Long id) {
        Venta ventaEncontrada = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La venta con ID: " + id + " no existe."));

        return modelMapper.map(ventaEncontrada, VentaDTO.class);
    }

    @Override
    public List<VentaDTO> getAll() {
        List<Venta> ventas = ventaRepository.findAll();

        if(ventas.isEmpty()) {
            throw new EmptyResourceListException("Aun no hay ventas registradas.");
        }

        return ventas.stream()
                .map(venta -> modelMapper.map(venta, VentaDTO.class))
                .toList();
    }

    @Override
    public List<VentaDTO> getVentasBySucursal(Long sucursalId) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new NotFoundException("La sucursal con ID: " + sucursalId + " no existe."));

        List<Venta> ventasSucursal = ventaRepository.findBySucursal(sucursal);

        if(ventasSucursal.isEmpty()) {
            throw new RuntimeException("La sucursal aun no registra ventas.");
        }

        return ventasSucursal.stream()
                .map(venta -> modelMapper.map(venta, VentaDTO.class))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        if(!ventaRepository.existsById(id)) {
            throw new NotFoundException("La venta con ID: " + id + " no existe!");
        }
        ventaRepository.deleteById(id);
    }
}
