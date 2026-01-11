package com.example.TP1.repository;

import com.example.TP1.entity.Client;
import com.example.TP1.entity.Commande;
import com.example.TP1.entity.CommandeLigne;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandeLigneRepository  extends CrudRepository<CommandeLigne,Long> {

    List<CommandeLigne> findByCommande(Commande commande);
}
