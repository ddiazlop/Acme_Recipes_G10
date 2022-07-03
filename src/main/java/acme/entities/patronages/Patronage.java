
package acme.entities.patronages;

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
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Patronage extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotNull
	@Enumerated(EnumType.STRING)
	protected PatronageStatus	status;

	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String			code;

	@NotBlank
	@Length(min = 1, max = 255)
	protected String			legalStuff;

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
	@Column(name = "information")
	protected String			info;
	
	protected boolean published;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Patron			patron;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor			inventor;
	
	
	
	


}
