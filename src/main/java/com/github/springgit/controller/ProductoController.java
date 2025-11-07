package com.github.springgit.controller;

import com.github.springgit.model.Producto;
import com.github.springgit.repository.ProductoRepository; // ¡Importamos el Repositorio!
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Este es el "request.setAttribute" de Spring
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // Le dice a Spring que esta clase es un Controlador web
public class ProductoController {

    @Autowired // Inyección de Dependencias: Spring nos "inyecta" el repositorio
    private ProductoRepository repo;

    /**
     * OPERACIÓN READ (Leer Todos)
     * Mapea la URL raíz ("/") para mostrar la lista de productos.
     */
    @GetMapping("/")
    public String listarProductos(Model model) {
        // 1. Llama al repositorio para obtener todos los productos
        List<Producto> productos = repo.findAll();
        
        // 2. Agrega la lista al "model" para que la vista (HTML) la pueda leer
        model.addAttribute("productos", productos);
        
        // 3. Devuelve el nombre del archivo HTML que debe mostrarse
        return "index"; // Spring buscará "src/main/resources/templates/index.html"
    }

    /**
     * OPERACIÓN CREATE (Mostrar Formulario)
     * Mapea la URL "/nuevo" para mostrar el formulario de registro.
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        // 1. Crea un objeto Producto vacío para enlazarlo al formulario
        model.addAttribute("producto", new Producto());
        
        // 2. Agrega un título para la página
        model.addAttribute("titulo", "Agregar Producto");
        
        // 3. Devuelve el nombre del formulario
        return "formulario"; // Buscará "formulario.html"
    }

    /**
     * OPERACIÓN CREATE y UPDATE (Guardar)
     * Mapea la URL "/guardar" (método POST) que recibe los datos del formulario.
     */
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        // 1. El @ModelAttribute mapea los datos del formulario al objeto "producto"
        // 2. Llama al repositorio para guardar (si tiene ID, actualiza; si no, crea)
        repo.save(producto);
        
        // 3. Redirige al usuario de vuelta a la página principal
        return "redirect:/";
    }

    /**
     * OPERACIÓN UPDATE (Mostrar Formulario)
     * Mapea la URL "/editar/{id}" para buscar un producto y mostrarlo en el formulario.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        // 1. @PathVariable toma el {id} de la URL
        // 2. Busca el producto. .orElse(null) devuelve null si no lo encuentra.
        Producto producto = repo.findById(id).orElse(null);
        
        if (producto != null) {
            // 3. Si lo encuentra, lo pasa al formulario
            model.addAttribute("producto", producto);
            model.addAttribute("titulo", "Editar Producto");
            return "formulario"; // Reutiliza el mismo formulario
        } else {
            // 4. Si no existe, redirige al inicio
            return "redirect:/";
        }
    }

    /**
     * OPERACIÓN DELETE (Eliminar)
     * Mapea la URL "/eliminar/{id}" para borrar un producto.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id) {
        // 1. Llama al repositorio y borra el producto por su ID
        repo.deleteById(id);
        
        // 2. Redirige al usuario de vuelta a la página principal
        return "redirect:/";
    }
}