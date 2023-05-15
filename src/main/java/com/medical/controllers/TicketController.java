package com.medical.controllers;

import com.medical.base.BaseController;
import com.medical.entity.Ticket;
import com.medical.services.IShipFeesService;
import com.medical.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/tickets")
@CrossOrigin("*")
public class TicketController extends BaseController<Ticket> {

    @Autowired
    private ITicketService ticketService;


    @GetMapping()
    public ResponseEntity<?> getAllTickets() {
        return new ResponseEntity<>(ticketService.getListTickets(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createTicket(@RequestParam(name = "name") String name, @RequestParam(name = "discount") Integer discount, @RequestParam(name = "endDate")Date endDate) {
        return  new ResponseEntity<>(ticketService.createTicket(name, discount, endDate), HttpStatus.OK);
    }
}
