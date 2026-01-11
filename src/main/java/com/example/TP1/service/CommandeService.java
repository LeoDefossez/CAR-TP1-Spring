package com.example.TP1.service;

import com.example.TP1.entity.Client;
import com.example.TP1.entity.Commande;
import com.example.TP1.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public void createCommande(Client client, String name) {
        Commande commande = new Commande(name, client);
        commandeRepository.save(commande);
    }

    public Commande getCommandeById(long id) {
        return commandeRepository.findById(id).orElse(null);
    }


}
