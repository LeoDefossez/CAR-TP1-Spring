package com.example.TP1.ctrl;


import com.example.TP1.entity.Client;
import com.example.TP1.entity.Commande;
import com.example.TP1.service.CommandeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/store")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @PostMapping("orders/newOrder")
    public RedirectView newOrder(@RequestParam String name, HttpSession session){
        Client client = (Client) session.getAttribute("loggedClient");

        if (client == null){
            return new RedirectView("/store/home");
        }

        commandeService.createCommande(client,name);

        return new RedirectView("/store/clienthome");
    }

    @GetMapping("orders/{id}")
    public ModelAndView order(@PathVariable("id") long id, HttpSession session){
        Commande commande = commandeService.getCommandeById(id);

        if(this.isInvalidAccess(session,commande)){
            return this.invalidAccess();
        }

        return new ModelAndView("commande", Map.of("commande",commande));
    }

    @GetMapping("orders/{id}/print")
    public ModelAndView printOrder(@PathVariable("id") long id, HttpSession session){
        Commande commande = commandeService.getCommandeById(id);

        if(this.isInvalidAccess(session,commande)){
           return this.invalidAccess();
        }

        return new ModelAndView("printCommande", Map.of("commande",commande));
    }

    private boolean isInvalidAccess(HttpSession session, Commande commande){
        Client client = (Client) session.getAttribute("loggedClient");
        return client == null || commande == null || !Objects.equals(client.getEmail(), commande.getClient().getEmail());
    }

    private ModelAndView invalidAccess(){
        return new ModelAndView("error",Map.of("error","Impossible access, this order is not yours"));
    }
}
