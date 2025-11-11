package Ecommerce_FullStackcom.example.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "comuna")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una comuna dentro de una región")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comuna_id;

    @NotBlank(message = "El nombre de la comuna es obligatorio")
    private String nombre_comuna;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
