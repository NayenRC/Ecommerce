package Ecommerce_FullStackcom.example.Ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto_carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductoCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer producto_carrito_id;

    @NotNull(message = "La cantidad de productos es obligatoria")
    @Column(nullable = false)
    private Integer cantidadProducto;
    
    @NotNull(message = "El subtotal de producto debe ser obligatorio")
    @Column(nullable = false)
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    
    @ManyToOne 
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

}
