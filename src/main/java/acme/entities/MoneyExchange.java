
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MoneyExchange extends AbstractEntity {

	// Query attributes -------------------------------------------------------

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= 1L;

	@NotNull
	@Valid
	protected Money				source;

	@NotBlank
	protected String			targetCurrency;

	// Response attributes ----------------------------------------------------

	@Valid
	protected Double			rate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				date;

	@Transient
	protected Money				change;

}
