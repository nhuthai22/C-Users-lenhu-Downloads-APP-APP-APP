package poly.store.service;

import java.util.List;

import poly.store.entity.Account;
import poly.store.entity.Ticket;

public interface TicketService {
    Ticket findById(Long id);

    List<Ticket> findAll();
    
    Ticket create(Ticket ticket);

    Ticket update(Ticket ticket);

    void delete(Long id);
    
    Account getInfoAuth();
}
