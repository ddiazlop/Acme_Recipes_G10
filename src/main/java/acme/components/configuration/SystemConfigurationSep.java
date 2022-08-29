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
public class SystemConfigurationSep extends AbstractEntity{

	
		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------
		@NotBlank
		protected String			systemCurrency;

		@NotBlank
		@Pattern(regexp = "^[A-Z]{3}(,[A-Z]{3})*$")
		protected String			acceptedCurrencies;

		@NotBlank
		@Pattern(regexp = "(([a-zA-Z0-9 ’]+:[0].[0-9]+)|[a-zA-Z0-9 ’]+:1.0|,?)+\\b")
		protected String	spamTuple;

		@Range(max = 1, min = 0)
		@NotNull
		protected Double			spamThreshold;

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------

}
