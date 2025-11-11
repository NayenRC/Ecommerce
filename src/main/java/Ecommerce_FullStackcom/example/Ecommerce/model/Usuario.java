package Ecommerce_FullStackcom.example.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa a un usuario del sistema (cliente o administrador)")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El correo es obligatorio")
    @Column(nullable = false, unique = true, length = 80)
    private String correoElectronico;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(nullable = false, unique = true, length = 80)
    private String nombreUsuario;

    // Se oculta la contraseña en las respuestas JSON
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "La clave es obligatoria")
    @Column(nullable = false, length = 60)
    private String clave;

    @Column(length = 120)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "comuna_id", nullable = false)
    private Comuna comuna;
}