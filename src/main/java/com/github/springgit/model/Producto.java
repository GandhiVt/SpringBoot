package com.github.springgit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Esta es nuestra Entidad o Modelo.
 * Spring JPA leerá esto y creará/actualizará la tabla "productos".
 */
@Entity
@Table(name = "productos") // Especifica el nombre de la tabla en MySQL
public class Producto {

    @Id // Marca este campo como la Llave Primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Autoincrementable
    private int id;
    
    private String nombre;
    private double precio;
    private int stock;

    // --- Getters y Setters ---
    // (En NetBeans: Clic derecho > Insert Code... > Getter and Setter... > Seleccionar todos)
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}