package Ecommerce_FullStackcom.example.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecommerce_FullStackcom.example.Ecommerce.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
    // Ejemplo: List<Comuna> findByRegionId(Integer regionId);
}