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
public class Pimpam extends AbstractEntity{
	// Serialisation identifier -----------------------------------------------

			protected static final long	serialVersionUID	= 1L;

			// Attributes -------------------------------------------------------------

			@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
			@NotBlank
			protected String code;
			
			@Temporal(TemporalType.TIMESTAMP)
			@NotNull
			protected Date instantiationMoment;
			
			@Length(max=100)
			@NotBlank
			protected String title;

			@NotBlank
			@Length(min = 1, max = 255)
			protected String			description;
			
			@Temporal(TemporalType.TIMESTAMP)
			@NotNull
			protected Date startDate;
			
			@Temporal(TemporalType.TIMESTAMP)
			@NotNull
			protected Date finishDate;

			@Valid
			@NotNull
			protected Money				budget;

			@URL
			@Column(name = "further_information")
			protected String			info;
		

			// Derived attributes -----------------------------------------------------
			
			// Relationships ----------------------------------------------------------
			
			@Valid
			@ManyToOne(optional = false)
			@NotNull
			protected Chef			chef;
			
			@Valid
			@OneToOne(optional = false)
			@NotNull
			protected Kitchenware			kitchenware;
}
