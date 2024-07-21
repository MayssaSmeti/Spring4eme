package tn.esprit.spring.service.classes;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.persistence.entities.Evenement;
import tn.esprit.spring.persistence.entities.Logistique;
import tn.esprit.spring.persistence.entities.Participant;
import tn.esprit.spring.persistence.entities.Tache;
import tn.esprit.spring.persistence.repositories.EvenementRepository;
import tn.esprit.spring.persistence.repositories.LogistiqueRepository;
import tn.esprit.spring.persistence.repositories.ParticipantRepository;
import tn.esprit.spring.service.interfaces.IEvenemntService;

@Service
@Slf4j
public class EvenementServiceImpl implements IEvenemntService {
    @Autowired
    ParticipantRepository partRep;
    @Autowired
    EvenementRepository eventRep;

	@Autowired
	LogistiqueRepository logisRep;

    //1ère méthode d'affectation avec la signature Evenement ajoutAffectEvenParticip(Evenement e, int idParticip)
    @Override
    public Evenement ajoutAffectEvenParticip(Evenement e, int idParticip) {
        Participant p = partRep.findById(idParticip).get();
        Evenement savedEvent = eventRep.findById(e.getId()).get();
        Set<Participant> pts;

        if (savedEvent.getParticipants() == null) {
            pts = new HashSet<Participant>();
        } else {
            pts = savedEvent.getParticipants();
        }

        pts.add(p);
        e.setParticipants(pts);
        eventRep.save(e);
        return e;
    }


//2ème méthode d'affectation avec la signature: Evenement ajoutAffectEvenParticip(Evenement e)

    @Override
    public Evenement ajoutAffectEvenParticip(Evenement e) {
        eventRep.save(e);
        return e;
    }


    @Override
    public Participant ajouterParticipant(Participant p) {
        partRep.save(p);
        return p;
    }

    //Question 6
    @Override
    @Scheduled(fixedRate = 60000)
    public void calculCout() {
        float cout = 0;
        List<Evenement> evenements = (List<Evenement>) eventRep.findAll();
        for (Evenement ev : evenements) {
            cout = cout + logisRep.calculPrixLogistiquesReserves(true);
            ev.setCout(cout);
            eventRep.save(ev);
            log.info("le cout de l'evenement : " + ev + " est:" + cout +
                    "il est mis à jour dans la base");
        }
    }


    @Override
    public List<Participant> getParReservLogis() {

        return partRep.participReservLogis(true, Tache.ORGANISATEUR);

    }


    @Override
    public Logistique ajoutAffectLogEven(Logistique l, String description_evnm) {
        Evenement e = eventRep.findByDescription(description_evnm);
        Set<Logistique> logis;
        if (e.getLogistiques() == null) {
            logis = new HashSet<Logistique>();
        } else {
            logis = e.getLogistiques();
        }

        logisRep.save(l);
        logis.add(l);
        e.setLogistiques(logis);
        eventRep.save(e);
        return l;
    }

    @Override
    public List<Logistique> getLogistiquesDates(Date dated, Date datef) {
        List<Evenement> events = eventRep.findByDatedBetween(dated, datef);
        List<Logistique> allLogists = new ArrayList<>();
        for (Evenement e : events) {
            for (Logistique l : e.getLogistiques()) {
                if (l.isReserve())
                    allLogists.add(l);
            }
        }
        return allLogists;
    }


}
	
	

	

