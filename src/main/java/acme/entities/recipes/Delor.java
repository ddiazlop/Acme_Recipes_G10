package acme.entities.recipes;

import java.time.Period;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delor extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotNull
	@Pattern(regexp = "^\\d{6}:yymmdd$")
	protected String			keylet;

	@NotNull
	protected Date			instantiationMoment;

	@NotBlank
	@Length(min = 1, max = 100)
	protected String			subject;

	@NotBlank
	@Length(min = 1, max = 255)
	protected String			explanation;
	
	@NotBlank
	@Min(0)
	protected Money    			income;

	
	protected Period            period; 
	
	@URL
	protected String			moreInfo;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	
}
