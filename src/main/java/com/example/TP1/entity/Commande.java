package com.example.TP1.entity;

import jakarta.persistence.*;

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToOne
    private Client client;


    public Commande() {
    }

    public Commande(String name, Client client) {
        this.client = client;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Client getClient() {
        return client;
    }
}
