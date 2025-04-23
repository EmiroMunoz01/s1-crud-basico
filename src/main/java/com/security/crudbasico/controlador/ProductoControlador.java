package com.security.crudbasico.controlador;


import com.security.crudbasico.Servicio.ProductoServicio;
import com.security.crudbasico.modelo.Producto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto) {

        Producto productoNuevo = servicio.guardar(producto);

        return ResponseEntity.ok(productoNuevo);

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {

        Optional<Producto> productoOpt = servicio.obtenerPorId(id);

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizar(@RequestBody Producto producto, @PathVariable Long id) {

        Optional<Producto> productoOptional = servicio.obtenerPorId(id);

        if (productoOptional.isPresent()) {
            Producto existente = productoOptional.get();
            existente.setNombre(producto.getNombre());
            existente.setPrecio(producto.getPrecio());
            Producto actualizado = servicio.guardar(existente);
            return ResponseEntity.ok(actualizado);

        } else {

            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Producto> productoOptional = servicio.obtenerPorId(id);

        if (productoOptional.isPresent()) {
            servicio.eliminar(id);
            return ResponseEntity.noContent().build(); // 204 sin cuerpo
        } else {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
    }




}
