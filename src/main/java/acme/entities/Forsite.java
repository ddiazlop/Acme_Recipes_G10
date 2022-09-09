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
import javax.validation.constraints.Past;
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
public class Forsite extends AbstractEntity{


	// Serialisation identifier 
	protected static final long	serialVersionUID	= 1L;
	
	// Attributes
	
	@NotBlank
	@Pattern(regexp = "^([a-zA-Z_0-9]{5})?-([0][1-9]|[12][0-9]|[3][01])/([0][1-9]|[1][0-2])/([0-9]{2})$")
	@Column(unique = true)
	protected String			ticker;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				instantiationMoment;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String			explanation;
	
	@Valid
	@NotNull
	protected Money				allowance;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	protected Date				startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				endDate;
	
	@URL
	protected String			info;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Chef				chef;
	
	@NotNull
	@Valid
	@OneToOne(optional = false)
	protected Kitchenware 			kitchenware;

}
