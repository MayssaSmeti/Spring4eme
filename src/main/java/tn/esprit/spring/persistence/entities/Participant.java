package tn.esprit.spring.persistence.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPart;

	private String nom;
	private String prenom;

	@Enumerated (EnumType.STRING)
	private Tache tache;

	// Ce n'est pas indiqué dans l'énonocé qui est le fils particpant ou evenement :
	// Dans la question 2 : on va affecter plusieurs participants à un evenement
	// Donc, l'idéal de mettre le participant comme fils
	@ManyToMany(mappedBy = "participants")
	@JsonIgnore
	Set<Evenement> evenements = new HashSet<Evenement>();

}
