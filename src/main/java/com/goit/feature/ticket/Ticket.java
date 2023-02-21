package com.goit.feature.ticket;

import com.goit.feature.client.Client;
import com.goit.feature.planet.Planet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Table(name = "ticket")
@Entity
@Data
public class Ticket {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "created_at")
    private String createdAt;

    //(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet planetFrom;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet planetTo;

}
