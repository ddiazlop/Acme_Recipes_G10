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
import javax.validation.constraints.Pattern;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.roles.Chef;
import acme.roles.Epicure;
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
	
	@NotBlank
	@Pattern(regexp = "^[0-9]{3}(-[A-Z])?:[0-9]{4}$")
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


