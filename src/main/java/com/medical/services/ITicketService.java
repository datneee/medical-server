package com.medical.services;

import com.medical.entity.Ticket;

import java.util.Date;
import java.util.List;

public interface ITicketService {
    List<Ticket> getListTickets();

    Ticket getTicketById(Integer id);

    Ticket createTicket(String name, Integer discount, Date endDate);
}
