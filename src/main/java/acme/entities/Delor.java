package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.recipes.Kitchenware;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delor extends AbstractEntity{
	// Serialisation identifier -----------------------------------------------

			protected static final long	serialVersionUID	= 1L;

			// Attributes -------------------------------------------------------------

		
			
			@Pattern(regexp = "^([0-9]{6}:)?[0-9]{2}[0-1]{1}[0-9]{1}[0-3]{1}[0-9]{1}$")
			@NotNull
			@Column(unique = true)
			protected String			keyLet;
			
			
			
			@Temporal(TemporalType.TIMESTAMP)
			@NotNull
			protected Date instantiationMoment;
				
			@Temporal(TemporalType.TIMESTAMP)
			@NotNull
			protected Date startDate;
			
			@Temporal(TemporalType.TIMESTAMP)
			@NotNull
			protected Date finishDate;

			@NotBlank
			@Length(min = 1, max = 100)
			protected String			subject;
			
			@NotBlank
			@Length(min = 1, max = 255)
			protected String			explanation;

			@Valid
			@NotNull
			protected Money				income;
			
			@URL
			protected String			moreInfo;

		

			// Derived attributes -----------------------------------------------------
			
			// Relationships ----------------------------------------------------------
			
			@ManyToOne(optional = false)
			@NotNull
			protected Chef			chef;
			
			@OneToOne(optional = false)
			@NotNull
			protected Kitchenware			kitchenware;
}
