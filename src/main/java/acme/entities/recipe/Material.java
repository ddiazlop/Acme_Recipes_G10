package acme.entities.recipe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.toolkits.ItemType;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Material extends AbstractEntity{
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
		
		@NotNull
		@Enumerated(EnumType.STRING)
		protected ItemType			itemType;

		protected boolean			published;

		// Derived attributes -----------------------------------------------------
		
		// Relationships ----------------------------------------------------------
		
		@ManyToOne(optional = false)
		@NotNull
		protected Chef			chef;
		
}
