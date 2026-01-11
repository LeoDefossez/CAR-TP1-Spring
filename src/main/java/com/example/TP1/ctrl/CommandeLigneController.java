package com.example.TP1.ctrl;

import com.example.TP1.entity.Commande;
import com.example.TP1.entity.CommandeLigne;
import com.example.TP1.service.CommandeLigneService;
import com.example.TP1.service.CommandeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/store/orders")
public class CommandeLigneController {

    @Autowired
    private CommandeLigneService commandeLigneService;

    @Autowired
    private CommandeService commandeService;


    public CommandeLigneController(CommandeLigneService commandeLigneService) {
        this.commandeLigneService = commandeLigneService;
    }

    @PostMapping("/{id}/newLine")
    public RedirectView newLine(@PathVariable("id") long id,
                                @RequestParam String libelle, @RequestParam long quantity,
                                @RequestParam long cost, HttpSession session){

        Commande commande = commandeService.getCommandeById(id);
        commandeLigneService.createCommandeLine(libelle,quantity,cost,commande);

        return new RedirectView("/store/orders/" + id);
    }

    @GetMapping("/{orderId}/delete/{lineId}")
    public RedirectView deleteLine(@PathVariable("orderId") long orderId, @PathVariable("lineId") long lineId){
        CommandeLigne line = commandeLigneService.getLineById(lineId);
        if(line != null){
            commandeLigneService.deleteLineById(lineId);
        }

        return new RedirectView("/store/orders/" + orderId);
    }
}
