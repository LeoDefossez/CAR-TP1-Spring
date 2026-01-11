package com.example.TP1.ctrl;


import com.example.TP1.entity.Client;
import com.example.TP1.service.CommandeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/store")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @PostMapping("newOrder")
    public RedirectView newOrder(@RequestParam String name, HttpSession session){
        Client client = (Client) session.getAttribute("loggedClient");

        if (client == null){
            return new RedirectView("home");
        }

        commandeService.createCommande(client,name);

        return new RedirectView("clienthome");
    }
}
