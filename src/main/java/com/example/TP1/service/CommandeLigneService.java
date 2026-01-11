package com.example.TP1.service;

import com.example.TP1.entity.Commande;
import com.example.TP1.entity.CommandeLigne;
import com.example.TP1.repository.CommandeLigneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeLigneService {

    @Autowired
    private CommandeLigneRepository commandeLigneRepository;


    public CommandeLigneService(CommandeLigneRepository commandeLigneRepository) {
        this.commandeLigneRepository = commandeLigneRepository;
    }

    public void createCommandeLine(String libelle, long quantity, long cost, Commande commande){
        CommandeLigne line = new CommandeLigne(libelle,quantity,cost,commande);
        commandeLigneRepository.save(line);
    }

    public CommandeLigne getLineById(long id){
        return commandeLigneRepository.findById(id).orElse(null);
    }

    public void deleteLineById(long id) {
        commandeLigneRepository.deleteById(id);
    }
}
