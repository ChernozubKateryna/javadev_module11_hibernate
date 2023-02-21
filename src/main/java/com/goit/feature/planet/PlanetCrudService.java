package com.goit.feature.planet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {
    private Session session;

    public PlanetCrudService(Session session) {
        this.session = session;
    }

    public void createNewPlanet(String id, String name) {
        Planet newPlanet = new Planet();

        boolean validValue = id.matches("^[A-Z0-9]+$");

        if (!validValue) {
            System.out.println("Not valid id! The planet id must be write only latins letters in upper case and can have numbers.");
        } else {
            Transaction transaction = session.beginTransaction();
                newPlanet.setId(id);
                newPlanet.setName(name);
            session.persist(newPlanet);
            transaction.commit();
            System.out.println("A new planet has been created. New planet: " + newPlanet);
        }
    }

    public List<Planet> getAllPlanets() {
        return session.createQuery("from Planet", Planet.class).list();
    }

    public Planet getPlanetById(String id) {
        return session.get(Planet.class, id);
    }

    public Planet updatePlanetById(String id, String newName) {
        Transaction transaction = session.beginTransaction();
            Planet updatePlanet = getPlanetById(id);
            updatePlanet.setName(newName);
            session.persist(updatePlanet);
        transaction.commit();
        System.out.println("The planet with id: " + id + " has been updated.");
        return updatePlanet;
    }

    public void deletePlanetById(String id) {
        Transaction transaction = session.beginTransaction();

        Planet planet = session.get(Planet.class, id);
        if (planet == null) {
            System.out.println("The planet with id: " + id + " wasn't found.");
        } else {
            session.remove(planet);
            transaction.commit();
            System.out.println("The planet with id:" + id + " was deleted.");
        }
    }
}

/* EXAMPLE

    public Ticket updateToById(long id, Planet to) {
        Transaction transaction = session.beginTransaction();
        Ticket existing = getById(id);
        existing.setTo(to);
        session.persist(existing);
        transaction.commit();
        return existing;
    }

    public Ticket updateFromById(long id, Planet from) {
        Transaction transaction = session.beginTransaction();
        Ticket existing = getById(id);
        existing.setFrom(from);
        session.persist(existing);
        transaction.commit();
        return existing;
    }

    public Ticket updateClientById(long id, Client client) {
        Transaction transaction = session.beginTransaction();
        Ticket existing = getById(id);
        existing.setClient(client);
        session.persist(existing);
        transaction.commit();
        return existing;
    }

    public void deleteById(long id) {
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("delete from Ticket where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        transaction.commit();
        if (result > 0) {
            System.out.println("Ticket was removed");
        }
    }

 */
