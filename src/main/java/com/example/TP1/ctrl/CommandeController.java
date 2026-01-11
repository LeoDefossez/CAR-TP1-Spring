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
    public ModelAndView order(@PathVariable("id") long id){
        Commande commande = commandeService.getCommandeById(id);

        return new ModelAndView("commande", Map.of("commande",commande));
    }
}
