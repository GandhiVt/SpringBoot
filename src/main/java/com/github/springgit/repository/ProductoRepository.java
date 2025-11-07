package com.github.springgit.repository;

import com.github.springgit.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta interfaz es la magia de Spring Data JPA.
 * Al extender JpaRepository, Spring nos da todos los métodos CRUD gratis.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    // ¡No necesitas escribir nada más aquí!
    // Ya tenemos:
    // - findAll() (Leer todos)
    // - findById(int id) (Leer uno)
    // - save(Producto p) (Crear y Actualizar)
    // - deleteById(int id) (Eliminar)
}