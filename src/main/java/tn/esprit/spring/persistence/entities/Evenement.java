package tn.esprit.spring.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Evenement implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;
	@Temporal(TemporalType.DATE)
	private Date dated;
	@Temporal(TemporalType.DATE)
	private Date datef;
	private float cout;

	// N'oublier pas d'initiliser les Set : new HashSet<...> :
	@ManyToMany
	Set<Participant> participants = new HashSet<Participant>();

	// Unidirectionnel, donc pas de mappedBy :
	@OneToMany
	@JsonIgnore
	@ToString.Exclude
	Set<Logistique> logistiques = new HashSet<Logistique>();
}
