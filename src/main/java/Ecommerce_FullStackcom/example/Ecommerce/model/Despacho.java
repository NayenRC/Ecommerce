package Ecommerce_FullStackcom.example.Ecommerce.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Schema(description = "Modelo que representa la información del despacho de un pedido")
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "despacho_id")
    private Integer despachoId;

    @OneToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

    @NotBlank(message = "La dirección de envío es obligatoria")
    @Column(name = "direccion_envio", nullable = false, length = 150)
    private String direccionEnvio;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    @Column(name = "estado_despacho", length = 50)
    private String estadoDespacho;
}
