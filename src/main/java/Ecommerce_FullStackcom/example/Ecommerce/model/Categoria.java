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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoria_id;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Column(length = 15, nullable = false)
    private String nombreCategoria;

    @ManyToOne
    @JoinColumn(name = "categorias_id", nullable = false)
    private Categorias categorias;
    
}
