package com.example.TP1.ctrl;

import com.example.TP1.entity.Client;
import com.example.TP1.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    private Client loggedClient = null;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/store/clientlogin")
    public RedirectView clientlogin(@RequestParam String email, @RequestParam String password){
        this.loggedClient = this.clientService.clientWithEmailAndPassword(email,password);


        if (loggedClient == null ){
            return new RedirectView("/store/home");
        }
        return new RedirectView("/store/clienthome");
    }

    @GetMapping("/store/clientlogout")
    public RedirectView clientlogout(){
        loggedClient =null;
        return new RedirectView("home");
    }

    @GetMapping("/store/clienthome")
    public ModelAndView clienthome(){
        return new ModelAndView("clienthome",Map.of("client",loggedClient));
    }

    @PostMapping("/store/createClient")
    public RedirectView createClient(@RequestParam String email, @RequestParam String password,
                                     @RequestParam String name, @RequestParam String firstname) {
        clientService.createClient(email, password, name, firstname);
        return new RedirectView("/store/home");
    }

    @GetMapping("/store/home")
    public String home(){
        return "login";
    }

}
