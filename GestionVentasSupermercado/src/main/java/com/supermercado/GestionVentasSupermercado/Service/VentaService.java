package com.supermercado.GestionVentasSupermercado.Service;

import com.supermercado.GestionVentasSupermercado.Dto.SucursalDTO;
import com.supermercado.GestionVentasSupermercado.Dto.VentaDTO;
import com.supermercado.GestionVentasSupermercado.Model.Venta;
import com.supermercado.GestionVentasSupermercado.Repository.VentaRepository;
import org.modelmapper.ModelMapper;

import java.util.List;

public class VentaService implements IVentaService {
    private final VentaRepository ventaRepository;
    private final ModelMapper modelMapper;

    public VentaService(VentaRepository ventaRepository, ModelMapper modelMapper) {
        this.ventaRepository = ventaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VentaDTO create(VentaDTO ventaDto) {
        Venta venta = modelMapper.map(ventaDto, Venta.class);

        Venta ventaNueva = ventaRepository.save(venta);

        return modelMapper.map(ventaNueva, VentaDTO.class);
    }

    @Override
    public VentaDTO getVentaById(Long id) {
        Venta ventaEncontrada = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La venta con ID: " + id + " no existe."));

        return modelMapper.map(ventaEncontrada, VentaDTO.class);
    }

    @Override
    public List<VentaDTO> getAll() {
        List<Venta> ventas = ventaRepository.findAll();

        if(ventas.isEmpty()) {
            throw new RuntimeException("Aun no hay ventas registradas.");
        }

        return ventas.stream()
                .map(venta -> modelMapper.map(venta, VentaDTO.class))
                .toList();
    }

    @Override
    public List<VentaDTO> getVentasBySucursal(SucursalDTO sucursaldto) {
        List<Venta> ventasSucursal = ventaRepository.findBySucursal(sucursaldto);

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
            throw new RuntimeException("La venta con ID: " + id + " no existe!");
        }
        ventaRepository.deleteById(id);
    }
}
