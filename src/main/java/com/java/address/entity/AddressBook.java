package com.java.address.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class which holds the address details of a user.
 *
 * @author nirmal.arangil
 */
public class AddressBook implements Serializable {

	private static final long serialVersionUID = -6460825103373209122L;

	/** User id of the user to which this AddressBook belongs */
	private String userId;

	/** List of contacts of the user */
	private List<Contact> contactList;

	/**
	 * Constructs an AddressBook object from the specified userId and contactList
	 *
	 * @param userId
	 * @param contactList
	 */
	public AddressBook(String userId, List<Contact> contactList) {
		super();
		this.userId = userId;
		this.contactList = contactList;
	}

	/**
	 * Returns the userId
	 * 
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the value of userId
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Returns the list of contacts
	 * 
	 * @return contactList
	 */
	public List<Contact> getContactList() {
		return contactList;
	}

	/**
	 * Sets the value of contactList
	 * 
	 * @param contactList
	 */
	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	/**
	 * Add the specified contact to the contactList
	 * 
	 * @param contact
	 */
	public void addContact(Contact contact) {
		if (this.contactList != null) {
			contactList.add(contact);
		}
	}

	/**
	 * For the specified address book returns the list of contacts that are unique
	 * to this address book and the passed in address book
	 * 
	 * @param addressBook
	 * 
	 * @return contact list which contain unique contacts from both address books.
	 */
	public List<Contact> getUniqueContacts(AddressBook addressBook) {
		List<Contact> uniqueContactsList = new ArrayList<>();

		uniqueContactsList.addAll(this.getContactList().stream()
				.filter(str -> !addressBook.getContactList().contains(str)).collect(Collectors.toList()));

		uniqueContactsList.addAll(addressBook.getContactList().stream()
				.filter(str -> !this.getContactList().contains(str)).collect(Collectors.toList()));

		return uniqueContactsList;
	}

	/**
	 * Returns a String representation of this object which contains the userId and
	 * the contactList.
	 *
	 * @return a string representation of this object.
	 */
	@Override
	public String toString() {
		return "AddressBook [userId=" + userId + ", contactList=" + contactList + "]";
	}

}
