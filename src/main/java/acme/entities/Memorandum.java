package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter

public class Memorandum extends AbstractEntity{


	// Serialisation identifier 
	protected static final long	serialVersionUID	= 1L;

	
	// Attributes
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				moment;
	
	//Me falta añadir matches pattern “〈fine dish-code〉:〈serial-number〉”,
	//where “〈fine dish-code〉”denotes the code of corresponding fine dish
	//and “〈serial-number〉” denotes a sequential number that starts at “0001” 
	//and gets increased with every new memorandum
	@Column(unique = true)
	protected String			sequenceNumber;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String			report;
				
	
	@URL
	protected String			info;
				
					
	// Derived attributes -----------------------------------------------------

	
	// Relationships ----------------------------------------------------------
	
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Epicure			epicure;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Chef			chef;
}


