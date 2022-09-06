
package acme.entities.fineDish;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import acme.roles.Epicure;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FineDish extends AbstractEntity {

	// Serialisation identifier 
	protected static final long	serialVersionUID	= 1L;

	
	// Attributes
	@NotNull
	@Enumerated(EnumType.STRING)
	protected DishStatus	status;
	
	
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	@NotNull
	@Column(unique = true)
	protected String			code;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String			request;
				
	@Valid
	@NotNull
	protected Money				budget;
				
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				creationDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	protected Date				startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				endDate;
	
	@URL
	protected String			info;
	
	protected boolean 			published;
				
					
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

