
package acme.components.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotBlank
	protected String			systemCurrency;

	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}(,[A-Z]{3})*$")
	protected String			acceptedCurrencies;

	@NotBlank
	protected String			strongSpamTerms;

	@Range(max = 100, min = 0)
	@NotNull
	protected Double			strongSpamThreshold;

	@NotBlank
	protected String			weakSpamTerms;

	@Range(max = 100, min = 0)
	@NotNull
	protected Double			weakSpamThreshold;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
