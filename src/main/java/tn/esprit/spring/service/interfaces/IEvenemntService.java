package tn.esprit.spring.service.interfaces;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.persistence.entities.Evenement;
import tn.esprit.spring.persistence.entities.Logistique;
import tn.esprit.spring.persistence.entities.Participant;

public interface IEvenemntService {

	public Evenement ajoutAffectEvenParticip(Evenement e, int idParticip);
	public Evenement ajoutAffectEvenParticip(Evenement e);
	public Logistique ajoutAffectLogEven(Logistique l, String description_evnm);
	public List<Logistique> getLogistiquesDates (Date date_debut, Date date_fin);
	public Participant ajouterParticipant (Participant p) ;
	public void calculCout ( );
	public List<Participant> getParReservLogis();

}
