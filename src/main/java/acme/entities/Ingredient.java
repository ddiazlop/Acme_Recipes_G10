package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingredient {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Length(min = 1, max = 100)
	@NotBlank
	protected String			name;

	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	@NotNull
	@Column(unique = true)
	protected String			code;

	@NotBlank
	@Length(min = 1, max = 255)
	protected String			description;

	@Valid
	@NotNull
	protected Money				retailPrice;

	@URL
	@Column(name = "further_information")
	protected String			info;

	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	@Valid
    @NotNull
    @ManyToOne
    protected Chef chef;

}
