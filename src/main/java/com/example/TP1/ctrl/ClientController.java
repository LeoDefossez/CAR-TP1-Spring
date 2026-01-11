package com.example.TP1.ctrl;

import com.example.TP1.entity.Client;
import com.example.TP1.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;


@Controller
@RequestMapping("/store")
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public RedirectView login(@RequestParam String email, @RequestParam String password, HttpSession session){
        Client client = this.clientService.clientWithEmailAndPassword(email,password);


        if (client == null ){
            return new RedirectView("home");
        }

        session.setAttribute("loggedClient",client);
        return new RedirectView("clienthome");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session){
        session.invalidate();

        return new RedirectView("home");
    }

    @GetMapping("/clienthome")
    public ModelAndView clienthome(HttpSession session){
        Client client = (Client) session.getAttribute("loggedClient");
        return new ModelAndView("clienthome", Map.of("client",client));
    }

    @PostMapping("/registerClient")
    public RedirectView registerClient(@RequestParam String email, @RequestParam String password,
                                     @RequestParam String name, @RequestParam String firstname) {
        clientService.registerClient(email, password, name, firstname);
        return new RedirectView("home");
    }


}
