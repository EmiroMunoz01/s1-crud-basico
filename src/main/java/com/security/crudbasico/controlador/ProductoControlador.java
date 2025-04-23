package com.security.crudbasico.controlador;


import com.security.crudbasico.Servicio.ProductoServicio;
import com.security.crudbasico.modelo.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("")
    public ResponseEntity<?> listarProductos() {
        List<Producto> productos = servicio.listar();
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){

        Producto productoNuevo = servicio.guardar(producto);

        return ResponseEntity.ok(productoNuevo);

    }

}
