package com.goit.feature;

import com.goit.feature.client.ClientCrudService;
import com.goit.feature.database.DatabaseInitService;
import com.goit.feature.database.hibernate.HibernateUtil;
import com.goit.feature.planet.PlanetCrudService;
import com.goit.feature.ticket.Ticket;
import com.goit.feature.ticket.TicketCrudService;
import org.hibernate.Session;

import java.util.List;

public class App {
    public static void main(String[] args) {
        new DatabaseInitService().initDb();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        ClientCrudService clientCrudService = new ClientCrudService(session);
        PlanetCrudService planetCrudService = new PlanetCrudService(session);
        TicketCrudService ticketCrudService = new TicketCrudService(session);

        ticketCrudService.createNewTicket(clientCrudService.getClientById(2), planetCrudService.getPlanetById("MARS"), planetCrudService.getPlanetById("ER"));

        List<Ticket> ticketList = ticketCrudService.getAllTicket();
        System.out.println(ticketList);

        System.out.println(ticketCrudService.getTicketById(5));

        ticketCrudService.updateTicket(8, clientCrudService.getClientById(4), planetCrudService.getPlanetById("VEN"), planetCrudService.getPlanetById("JUP"));

        ticketCrudService.deleteTicketById(8);

        session.close();
    }
}
