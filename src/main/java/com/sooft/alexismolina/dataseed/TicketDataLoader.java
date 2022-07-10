package com.sooft.alexismolina.dataseed;

import com.sooft.alexismolina.domain.model.Ticket;
import com.sooft.alexismolina.domain.repository.TicketRepository;
import com.sooft.alexismolina.enums.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class TicketDataLoader implements CommandLineRunner {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void run(String... args) throws Exception {
        loadTicketData();
    }

    private void loadTicketData() {

        if (ticketRepository.count() == 0) {
            this.ticketRepository.save(new Ticket("Help, my printer is on fire!", Priority.MEDIUM));
            this.ticketRepository.save(new Ticket("In our hospital there are many problems",Priority.HIGH));
            this.ticketRepository.save(new Ticket("The book is complex",Priority.LOW));
            this.ticketRepository.save(new Ticket("Help, my car is on fire!", Priority.HIGH));
            this.ticketRepository.save(new Ticket("index not found",Priority.MEDIUM));
            this.ticketRepository.save(new Ticket("broken screen",Priority.LOW));
        }
    }



}

