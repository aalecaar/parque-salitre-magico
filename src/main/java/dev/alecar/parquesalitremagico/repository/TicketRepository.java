package dev.alecar.parquesalitremagico.repository;

import dev.alecar.parquesalitremagico.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}