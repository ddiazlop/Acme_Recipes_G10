package acme.components.configuration;

import java.util.Map;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.entities.AbstractEntity;
import org.hibernate.validator.constraints.Range;

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
		protected String	SpamTuple;

		//Supuestamente el rango es de 0.00 a 1.00, por comodidad de 0 a 100
		@Range(max = 100, min = 0)
		@NotNull
		protected Double			SpamThreshold;

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------

}