package Ecommerce_FullStackcom.example.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecommerce_FullStackcom.example.Ecommerce.model.MetodoPago;
import Ecommerce_FullStackcom.example.Ecommerce.repository.MetodoPagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> listar() {
        return metodoPagoRepository.findAll();
    }

    public MetodoPago obtenerPorId(Integer id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

    public MetodoPago guardar(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public MetodoPago actualizarTodo(Integer id, MetodoPago mp) {
        MetodoPago existente = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        existente.setTipoPago(mp.getTipoPago());
        return metodoPagoRepository.save(existente);
    }

    public MetodoPago patchMetodoPago(Integer id, MetodoPago parcial) {
        MetodoPago existente = obtenerPorId(id);
        if (existente != null) {

            if (parcial.getTipoPago() != null)
                existente.setTipoPago(parcial.getTipoPago());

            return guardar(existente);
        }
        return null;
    }

    public void eliminar(Integer id) {
        MetodoPago mp = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        metodoPagoRepository.delete(mp);
    }
}
