package com.supermercado.GestionVentasSupermercado.service;

import com.supermercado.GestionVentasSupermercado.dto.SucursalDTO;
import com.supermercado.GestionVentasSupermercado.exception.DuplicateResourceException;
import com.supermercado.GestionVentasSupermercado.exception.EmptyResourceListException;
import com.supermercado.GestionVentasSupermercado.exception.NotFoundException;
import com.supermercado.GestionVentasSupermercado.model.Sucursal;
import com.supermercado.GestionVentasSupermercado.repository.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    private final SucursalRepository sucursalRepository;
    private final ModelMapper modelMapper;

    public SucursalService(SucursalRepository sucursalRepository, ModelMapper modelMapper) {
        this.sucursalRepository = sucursalRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public SucursalDTO create(SucursalDTO sucursalDto) {
        if(sucursalRepository.existsByNombre(sucursalDto.getNombre())) {
            throw new DuplicateResourceException("Ya existe una sucursal con ese nombre.");
        }

        Sucursal sucursal = modelMapper.map(sucursalDto, Sucursal.class);

        Sucursal sucursalNueva = sucursalRepository.save(sucursal);

        return modelMapper.map(sucursalNueva, SucursalDTO.class);
    }

    @Override
    public SucursalDTO getById(Long id) {
        Sucursal sucursalEncontrada = sucursalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sucursal con ID: " + id + " no encontrada."));

        return modelMapper.map(sucursalEncontrada, SucursalDTO.class);
    }

    @Override
    public List<SucursalDTO> getAll() {
        List<Sucursal> sucursales = sucursalRepository.findAll();

        if(sucursales.isEmpty()) {
            throw new EmptyResourceListException("Aun no hay sucursales agregadas.");
        }

        return sucursales.stream()
                .map(sucursal -> modelMapper.map(sucursal, SucursalDTO.class))
                .toList();

    }

    @Override
    public SucursalDTO updateById(Long id, SucursalDTO sucursalDto) {
        Sucursal sucursalEncontrada = sucursalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sucursal con ID: " + id + " no encontrada."));

        sucursalEncontrada.setNombre(sucursalDto.getNombre());
        sucursalEncontrada.setDireccion(sucursalDto.getDireccion());

        Sucursal actualizada = sucursalRepository.save(sucursalEncontrada);
        return modelMapper.map(actualizada, SucursalDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        if(!sucursalRepository.existsById(id)) {
            throw new NotFoundException("Sucursal con ID: " + id + " no encontrada.");
        }

        sucursalRepository.deleteById(id);
    }
}
