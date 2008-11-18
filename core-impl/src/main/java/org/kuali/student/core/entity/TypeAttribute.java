package org.kuali.student.core.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TypeAttribute extends Attribute {

	@ManyToOne
	@JoinColumn(name = "OWNER")
	private Type owner;

	public Type getOwner() {
		return owner;
	}

	public void setOwner(Type owner) {
		this.owner = owner;
	}
}
