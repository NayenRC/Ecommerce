package Ecommerce_FullStackcom.example.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import Ecommerce_FullStackcom.example.Ecommerce.model.Tienda;
import Ecommerce_FullStackcom.example.Ecommerce.Service.TiendaService;


@RestController
@RequestMapping("/api/tiendas") 
@Tag(name = "Tienda Management System")

public class TiendaController {


    @Autowired
    private TiendaService tiendaService;
    @GetMapping
    @Operation(summary = "View a list of available tiendas")
    public List<Tienda> getAllTiendas() {
         return tiendaService.getAllTiendas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a tienda by Id")
    public Tienda getTiendaById(@PathVariable Long id) {
        return tiendaService.getTiendaById(id);
    }
    @PostMapping
    @Operation(summary = "Add a new tienda")        
    public Tienda createTienda(@RequestBody Tienda tienda) {
        return tiendaService.saveTienda(tienda);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing tienda")
    public Tienda updateTienda(@PathVariable Long id, @RequestBody Tienda tienda) {
        Tienda existingTienda = tiendaService.getTiendaById(id);
        if (existingTienda != null) {
            existingTienda.setName(tienda.getName());
            existingTienda.setDescription(tienda.getDescription());
            existingTienda.setCategory(tienda.getCategory());
            existingTienda.setPrice(tienda.getPrice());
            return tiendaService.saveTienda(existingTienda);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tienda")
    public void deleteTienda(@PathVariable Long id) {
        tiendaService.deleteTienda(id);
    }


}



