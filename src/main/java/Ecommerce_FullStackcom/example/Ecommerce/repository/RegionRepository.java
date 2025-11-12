package Ecommerce_FullStackcom.example.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    // Ejemplo: List<Region> findByNombreRegionContaining(String texto);
}
