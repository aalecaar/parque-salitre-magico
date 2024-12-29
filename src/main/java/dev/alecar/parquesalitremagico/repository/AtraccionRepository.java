package dev.alecar.parquesalitremagico.repository;

import dev.alecar.parquesalitremagico.model.Atraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtraccionRepository extends JpaRepository<Atraccion, Long> {
}