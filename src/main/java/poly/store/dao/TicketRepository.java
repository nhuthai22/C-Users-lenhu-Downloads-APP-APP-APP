package poly.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.store.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
}
