package com.example.concertsystem.service.tickets;

import com.example.concertsystem.dto.TicketResponse;
import com.example.concertsystem.entity.Seats;
import com.example.concertsystem.entity.Ticket;
import com.example.concertsystem.entity.Tier;
import com.example.concertsystem.entity.User;

import java.util.concurrent.ExecutionException;

public interface TicketService {
    void generateTicket(int count, String userId, String tierId, String eventId) throws ExecutionException, InterruptedException;
    void updateTicket(String id, int count, String userId, String tierId, String eventId) throws ExecutionException, InterruptedException;
    TicketResponse getTicketById(String id) throws ExecutionException, InterruptedException;
    TicketResponse getTicketByUserName(String userName) throws ExecutionException, InterruptedException;
    void deleteTicketById(String id);
}
