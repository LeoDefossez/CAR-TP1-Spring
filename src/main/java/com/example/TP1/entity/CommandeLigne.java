package com.example.TP1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CommandeLigne {

    @Id @GeneratedValue
    private long Id;

    private String libelle;

    private long quantity;

    private long cost;

    @ManyToOne
    private Commande commande;

    public CommandeLigne() {
    }

    public CommandeLigne(String libelle, long quantity, long cost, Commande commande) {
        this.libelle = libelle;
        this.quantity = quantity;
        this.cost = cost;
        this.commande = commande;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
