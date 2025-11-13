package Ecommerce_FullStackcom.example.Ecommerce.controller;

@RestController
@RequestMapping("/api/v1/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    @Operation(summary = "Lista todos los métodos de pago", description = "Retorna todos los métodos de pago disponibles")
    public ResponseEntity<List<MetodoPago>> listar() {
        List<MetodoPago> lista = metodoPagoService.listar();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un método de pago por ID", description = "Busca un método de pago específico")
    public ResponseEntity<MetodoPago> obtener(@PathVariable Integer id) {
        MetodoPago mp = metodoPagoService.obtenerPorId(id);
        return mp != null ? ResponseEntity.ok(mp) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo método de pago", description = "Registra un nuevo tipo de pago")
    public ResponseEntity<MetodoPago> crear(@Valid @RequestBody MetodoPago metodoPago) {
        MetodoPago creado = metodoPagoService.guardar(metodoPago);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza completamente un método de pago", description = "Reemplaza todos los datos del método de pago")
    public ResponseEntity<MetodoPago> actualizar(@PathVariable Integer id, @Valid @RequestBody MetodoPago metodoPago) {
        MetodoPago actualizado = metodoPagoService.actualizarTodo(id, metodoPago);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualización parcial de método de pago", description = "Modifica solo campos específicos")
    public ResponseEntity<MetodoPago> patch(@PathVariable Integer id, @RequestBody MetodoPago parcial) {
        MetodoPago actualizado = metodoPagoService.patchMetodoPago(id, parcial);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un método de pago", description = "Borra un método de pago por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        MetodoPago mp = metodoPagoService.obtenerPorId(id);
        if (mp == null)
            return ResponseEntity.notFound().build();
        metodoPagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
