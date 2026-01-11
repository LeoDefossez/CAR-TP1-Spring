package com.example.TP1.repository;

import com.example.TP1.entity.Client;
import com.example.TP1.entity.Commande;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CommandeRepository extends CrudRepository<Commande,Long> {

    List<Commande> findByClient(Client client);

}
