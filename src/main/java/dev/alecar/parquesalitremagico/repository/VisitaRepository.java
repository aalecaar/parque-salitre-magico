package dev.alecar.parquesalitremagico.repository;

import dev.alecar.parquesalitremagico.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {
}