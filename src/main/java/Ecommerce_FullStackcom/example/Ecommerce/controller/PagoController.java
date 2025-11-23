package Ecommerce_FullStackcom.example.Ecommerce.controller;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Value("${mercadopago.access.token}")
    private String mpToken;

    @PostMapping("/create-preference")
public ResponseEntity<?> createPreference(@RequestBody Map<String, Object> data) {

    MercadoPagoConfig.setAccessToken(mpToken);

    PreferenceClient client = new PreferenceClient();

    List<Map<String, Object>> itemsData = (List<Map<String, Object>>) data.get("items");
    List<PreferenceItemRequest> items = new ArrayList<>();

    for (Map<String, Object> item : itemsData) {
        items.add(
            PreferenceItemRequest.builder()
                    .title(item.get("title").toString())
                    .quantity(Integer.parseInt(item.get("quantity").toString()))
                    .unitPrice(new BigDecimal(item.get("unit_price").toString()))
                    .build()
        );
    }

    PreferenceRequest request = PreferenceRequest.builder()
            .items(items)
            .backUrls(
                PreferenceBackUrlsRequest.builder()
                        .success("http://localhost:5173/checkout/success")
                        .failure("http://localhost:5173/checkout/failure")
                        .pending("http://localhost:5173/checkout/pending")
                        .build()
            )
            .autoReturn("approved")
            .build();

    try {
        Preference preference = client.create(request);
        return ResponseEntity.ok(preference);

    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error al crear preferencia: " + e.getMessage());
    }
}
}