package Ecommerce_FullStackcom.example.Ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer producto_id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(length = 50, nullable = false)
    private String nombreProducto;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(length = 200, nullable = false)
    private String descripcionProducto;

    @NotNull(message = "El precio es obligatorio")
    @Column(nullable = false )
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

}
