package com.example.TP1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Client {

    @Id @GeneratedValue
    private long id;

    private String email;

    private String password;

    private String name;

    private String firstname;



}
