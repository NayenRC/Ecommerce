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
@Table(name = "despacho")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un despacho de productos")
public class Despacho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank(message = "La dirección de despacho es obligatoria")
    @Column(nullable = false, length = 200)
    private String direccionDespacho;

    @Column(length = 100)
    private String estadoDespacho;  




}
