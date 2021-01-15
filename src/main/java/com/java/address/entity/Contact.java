package com.java.address.entity;

import java.io.Serializable;

/**
 * Class which holds the contact details of a particular contact.
 *
 * @author nirmal.arangil
 */
public class Contact implements Serializable {

	private static final long serialVersionUID = -7505601724011828597L;

	/** Name of the contact */
	private String name;

	/** Phone number of the contact */
	private String phoneNumber;

	/**
	 * Constructs a Contact object from the specified name and phoneNumber
	 *
	 * @param name
	 * @param phoneNumber
	 */
	public Contact(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Returns the name of the contact
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the phone number of the contact
	 * 
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the value of phone number
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Returns a String representation of this object which contains the name and
	 * phone number
	 *
	 * @return a string representation of this object.
	 */
	@Override
	public String toString() {
		return "Contact [name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}

	/**
	 * Returns a hash code for this object.
	 *
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Compares this contact to the specified object. Returns true if and only if
	 * the argument is not null and is a Contact object that has the same name as
	 * this object.
	 *
	 * @param an Object to compare this Contact object against
	 *
	 * @return true if the given object represents a Contact object with the same
	 *         name as this object otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
