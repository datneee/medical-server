package com.medical.repositories;

import com.medical.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket getTicketById(Integer id);

    boolean existsByName(String name);

    Ticket getByName(String name);
}
