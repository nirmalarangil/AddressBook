package com.java.address.service;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.address.entity.AddressBook;
import com.java.address.entity.Contact;

/**
 * Service Class which exposes services for the address book application
 *
 * @author nirmal.arangil
 */
public class AddressBookService {

	/** Map which stores address books for users */
	private Map<String, AddressBook> addressBooks;
	
	private final String DATA_FILENAME = "AddressBooks.txt";

	/**
	 * Creates and initializes the AddressBookService class. addressBooks map is
	 * created and is loaded with already stored address books
	 */
	public AddressBookService() {
		addressBooks = new HashMap<String, AddressBook>();
		addressBooks = loadAddressBooks();
	}

	/**
	 * Adds the specified addressBook to the addressBooks map and stores into
	 * filesystem.
	 * 
	 * @param addressBook
	 */
	public void addAddressBook(AddressBook addressBook) {
		addressBooks.put(addressBook.getUserId(), addressBook);
	}

	/**
	 * Returns the address book for the specified userId
	 * 
	 * @param userId of the user
	 * 
	 * @return AddressBook
	 */
	private AddressBook getAddressBook(String userId) {
		AddressBook addressBook = addressBooks.get(userId);
		return addressBook;

	}

	/**
	 * Stores the map of address books to the filesystem.
	 * 
	 * @param addressBooks map of address books
	 */
	public void storeAddressBooks() {
		try {
			FileOutputStream fos = new FileOutputStream(DATA_FILENAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(addressBooks);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loads and returns the map of address books after fetching it from the
	 * filesystem.
	 * 
	 * @return Map<String, AddressBook> map of address books
	 */
	private Map<String, AddressBook> loadAddressBooks() {
		Map<String, AddressBook> addressBooks = new HashMap<>();

		try (FileInputStream fis = new FileInputStream(DATA_FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			boolean check = true;
			while (check) {
				try {
					addressBooks = (Map<String, AddressBook>) ois.readObject();
				} catch (EOFException ex) {
					check = false;
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("No address books stored");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return addressBooks;
	}

	/**
	 * Returns the contact list for the specified user after sorting it by name
	 * 
	 * @param userId of the user
	 * @return list of contacts sorted by name
	 */
	public List<Contact> getSortedContactList(String userId) {
		List<Contact> contactList = null;
		AddressBook addressBook = addressBooks.get(userId);
		if (addressBook != null) {
			Comparator<Contact> contactNameComparator = Comparator.comparing(Contact::getName);
			contactList = addressBook.getContactList();
			Collections.sort(addressBook.getContactList(), contactNameComparator);
		}
		return contactList;

	}

	/**
	 * For the specified userId and address book returns the list of contacts that
	 * are unique to the address book of the user and the passed in address book
	 * 
	 * @param userId      of the user
	 * @param addressBook
	 * 
	 * @return contact list which contain unique contacts from address books of the
	 *         user and the passed in addressbook.
	 */
	public List<Contact> getUniqueContactList(String userId, AddressBook newAddressBook) {
		List<Contact> uniqueContactsList = null;
		AddressBook addressBook = getAddressBook(userId);
		if (addressBook != null) {
			uniqueContactsList = addressBook.getUniqueContacts(newAddressBook);
		}
		return uniqueContactsList;
	}

}
