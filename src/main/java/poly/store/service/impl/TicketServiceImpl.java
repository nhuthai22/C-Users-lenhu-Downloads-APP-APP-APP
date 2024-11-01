package poly.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import poly.store.dao.AccountDAO;
import poly.store.dao.TicketRepository;
import poly.store.entity.Account;
import poly.store.entity.Ticket;
import poly.store.service.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        // Kiểm tra vé có tồn tại trước khi cập nhật
        if (!ticketRepository.existsById(ticket.getId())) {
            throw new IllegalArgumentException("Ticket with id " + ticket.getId() + " does not exist!");
        }
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        // Kiểm tra vé có tồn tại trước khi xóa
        if (!ticketRepository.existsById(id)) {
            throw new IllegalArgumentException("Ticket with id " + id + " does not exist!");
        }
        ticketRepository.deleteById(id);
    }

    @Override
    public Account getInfoAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new IllegalStateException("No authenticated user found!");
        }
        return accountDAO.FindByUserName(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException(auth.getName() + " not found!"));
    }
}
