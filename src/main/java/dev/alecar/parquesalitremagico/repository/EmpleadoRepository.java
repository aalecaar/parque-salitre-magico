package dev.alecar.parquesalitremagico.repository;

import dev.alecar.parquesalitremagico.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}