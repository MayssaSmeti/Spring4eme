package tn.esprit.spring.controllers;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.persistence.entities.Evenement;
import tn.esprit.spring.persistence.entities.Logistique;
import tn.esprit.spring.persistence.entities.Participant;
import tn.esprit.spring.service.interfaces.IEvenemntService;


@RestController
@RequestMapping("/Evenement")
public class EventRestController {

    @Autowired
    IEvenemntService evenServ;


    //Question 1
    //http://localhost:8082/exam/Evenement/add-Participant
    @PostMapping("/add-Participant")
    public Participant addParticipant(@RequestBody Participant p) {
        evenServ.ajouterParticipant(p);
        return (p);
    }

    //Question 2- 1ère signature
    //http://localhost:8082/exam/Evenement/add-Affect-Event/1
    @PostMapping("/add-Affect-Event/{idParticip}")
    public Evenement addAffectEventParticipant(@RequestBody Evenement e, @PathVariable("idParticip") int idParticip) {
        evenServ.ajoutAffectEvenParticip(e, idParticip);
        return e;
    }

    //Question 2- 2ère signature
    @PostMapping("/add-Affect-Event-To-Participant")
    public Evenement addAffectEventParticipant(@RequestBody Evenement e) {
        evenServ.ajoutAffectEvenParticip(e);
        return e;
    }

    //Question 3
    //http://localhost:8082/exam/Evenement/add-Affect-LogEvent/Festival Medina
    @PostMapping("/add-Affect-LogEvent/{descript}")
    public Logistique addAffectLogEvnm(@RequestBody Logistique l, @PathVariable("descript") String description_evnm) {
        evenServ.ajoutAffectLogEven(l, description_evnm);
        return l;
    }

    //Question 4
    //http://localhost:8082/exam/Evenement/retrieveLogistiquesDates/2023-01-01/2023-06-01
    @GetMapping("/retrieveLogistiquesDates/{dateD}/{dateF}")
    public List<Logistique> retrieveLogistiquesDates(@PathVariable("dateD") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDb, @PathVariable("dateF") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin) {
        return evenServ.getLogistiquesDates(dateDb, dateFin);
    }

    //Question 5
    //http://localhost:8082/exam/Evenement/retrieveLogistiquesDates/2023-01-01/2023-06-01
    @GetMapping("/getParticipantsLogis")
    public List<Participant> getParReservLogis() {
        return evenServ.getParReservLogis();
    }


}
