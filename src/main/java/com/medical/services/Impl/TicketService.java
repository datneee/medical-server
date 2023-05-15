package com.medical.services.Impl;

import com.medical.entity.Ticket;
import com.medical.repositories.ITicketRepository;
import com.medical.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private ITicketRepository ticketRepository;
    @Override
    public List<Ticket> getListTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(Integer id) {
        return ticketRepository.getTicketById(id);
    }

    @Override
    public Ticket createTicket(String name, Integer discount, Date endDate) {
        Ticket ticket = null;
        if(ticketRepository.existsByName(name)) {
            ticket = ticketRepository.getByName(name);
            ticket.setDiscount(discount);
            ticket.setEndDate(endDate);
        } else {
            ticket = new Ticket(name, discount, endDate);
        }
        return ticketRepository.save(ticket);
    }


}
