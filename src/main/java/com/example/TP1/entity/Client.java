package com.example.TP1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Client {


    @Id
    private String email;

    private String password;

    private String name;

    private String firstname;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Commande> commandes;

    public Client(String email, String password, String name, String firstname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.firstname = firstname;
    }

    public Client() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Commande> getCommandes() { return commandes; }
    public void setCommandes(List<Commande> commandes) { this.commandes = commandes; }
}
