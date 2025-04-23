package com.security.crudbasico.Servicio;

import com.security.crudbasico.modelo.Producto;
import com.security.crudbasico.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Producto> listar() {
        return repositorio.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return repositorio.findById(id);
    }

    public Producto guardar(Producto producto) {
        return repositorio.save(producto);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
