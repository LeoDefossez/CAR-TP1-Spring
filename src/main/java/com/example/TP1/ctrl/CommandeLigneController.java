package com.example.TP1.ctrl;

import com.example.TP1.entity.Client;
import com.example.TP1.entity.Commande;
import com.example.TP1.entity.CommandeLigne;
import com.example.TP1.service.CommandeLigneService;
import com.example.TP1.service.CommandeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

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
        if( this.isInvalidAccess(session,commande) ){
            return new RedirectView("/store/home");
        }

        commandeLigneService.createCommandeLine(libelle,quantity,cost,commande);

        return new RedirectView("/store/orders/" + id);
    }


    @GetMapping("/{orderId}/delete/{lineId}")
    public RedirectView deleteLine(@PathVariable("orderId") long orderId, @PathVariable("lineId") long lineId, HttpSession session){
        CommandeLigne line = commandeLigneService.getLineById(lineId);

        if(line != null){
            if(this.isInvalidAccess(session,line.getCommande())){
                return new RedirectView("/store/home");
            }
            commandeLigneService.deleteLineById(lineId);
        }

        return new RedirectView("/store/orders/" + orderId);
    }

    private boolean isInvalidAccess(HttpSession session, Commande commande){
        Client client = (Client) session.getAttribute("loggedClient");
        return client == null || commande == null || !Objects.equals(client.getEmail(), commande.getClient().getEmail());
    }

}
