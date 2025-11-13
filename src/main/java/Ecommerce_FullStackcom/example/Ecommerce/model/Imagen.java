package Ecommerce_FullStackcom.example.Ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "imagen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa imágenes asociadas a un producto")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagen_id")
    private Integer imagenId;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;

    @Column(name = "descripcion", length = 200)
    private String descripcion;
}
