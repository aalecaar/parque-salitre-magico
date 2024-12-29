package dev.alecar.parquesalitremagico.repository;

import dev.alecar.parquesalitremagico.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}