package com.java.address.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.java.address.entity.AddressBook;
import com.java.address.entity.Contact;
import com.java.address.service.AddressBookService;

/**
 * Test Class for the address book application
 *
 * @author nirmal.arangil
 */
public class AddressBookTest {

	/** Instance of AddressBookService for calling the service methods */
	private AddressBookService addressBookService;

	/**
	 * Initializes the AddressBookService class and creates and stores User1 address
	 * book
	 */
	@Before
	public void setUp() {
		addressBookService = new AddressBookService();
		AddressBook addressBook = createUser1Address();
		addressBookService.addAddressBook(addressBook);
		addressBookService.storeAddressBooks();
	}

	/**
	 * Creates and returns User1 address book
	 * 
	 * @return AddressBook
	 */
	private AddressBook createUser1Address() {
		List<Contact> contactList = createUser1Contacts();
		AddressBook addressBook = new AddressBook("User1", contactList);
		return addressBook;
	}

	/**
	 * Creates and returns User1 contacts
	 * 
	 * @return List<Contact>
	 */
	private List<Contact> createUser1Contacts() {
		Contact contact1 = new Contact("Name3", "12345678");
		Contact contact2 = new Contact("Name4", "11254448");
		Contact contact3 = new Contact("Name1", "82802201");
		Contact contact4 = new Contact("Name5", "39262622");
		Contact contact5 = new Contact("Name2", "27393933");
		List<Contact> contactList = new ArrayList<>();
		contactList.add(contact1);
		contactList.add(contact2);
		contactList.add(contact3);
		contactList.add(contact4);
		contactList.add(contact5);
		return contactList;
	}

	/**
	 * Creates and returns expected sorted contacts
	 * 
	 * @return List<Contact>
	 */
	private List<Contact> createExpectedSortedContacts() {
		Contact contact1 = new Contact("Name1", "82802201");
		Contact contact2 = new Contact("Name2", "27393933");
		Contact contact3 = new Contact("Name3", "12345678");
		Contact contact4 = new Contact("Name4", "11254448");
		Contact contact5 = new Contact("Name5", "39262622");
		List<Contact> contactList = new ArrayList<>();
		contactList.add(contact1);
		contactList.add(contact2);
		contactList.add(contact3);
		contactList.add(contact4);
		contactList.add(contact5);
		return contactList;
	}

	/**
	 * Test method which checks the sorting by name functionality for the contact
	 * list of User1
	 * 
	 */
	@Test
	public void displaySortedFriendList() {
		System.out.println("Start displaySortedFriendList");
		List<Contact> friendList = addressBookService.getSortedContactList("User1");
		System.out.println(friendList);
		List<Contact> expectedSortedContacts = createExpectedSortedContacts();
		Assert.assertEquals(friendList, expectedSortedContacts);
		System.out.println("End displaySortedFriendList");
	}

	/**
	 * Creates and returns User2 address book
	 * 
	 * @return AddressBook
	 */
	private AddressBook createUser2Address() {
		Contact contact1 = new Contact("Name2", "27393933");
		Contact contact2 = new Contact("Name5", "39262622");
		Contact contact3 = new Contact("Name6", "45602201");
		Contact contact4 = new Contact("Name7", "99264322");
		Contact contact5 = new Contact("Name8", "64373932");
		List<Contact> contactList = new ArrayList<>();
		contactList.add(contact1);
		contactList.add(contact2);
		contactList.add(contact3);
		contactList.add(contact4);
		contactList.add(contact5);
		AddressBook addressBook = new AddressBook("User2", contactList);
		return addressBook;
	}

	/**
	 * Creates and returns expected list of unique contacts
	 * 
	 * @return List<Contact>
	 */
	private List<Contact> getExpectedUniqueContacts() {
		Contact contact1 = new Contact("Name3", "12345678");
		Contact contact2 = new Contact("Name4", "11254448");
		Contact contact3 = new Contact("Name1", "82802201");
		Contact contact4 = new Contact("Name6", "45602201");
		Contact contact5 = new Contact("Name7", "99264322");
		Contact contact6 = new Contact("Name8", "64373932");

		List<Contact> contactList = new ArrayList<>();
		contactList.add(contact1);
		contactList.add(contact2);
		contactList.add(contact3);
		contactList.add(contact4);
		contactList.add(contact5);
		contactList.add(contact6);
		return contactList;
	}

	/**
	 * Test method which checks the unique friend list functionality. For given two
	 * distinct address books this method checks if the returned list of contacts
	 * are unique to each address books.
	 * 
	 */
	@Test
	public void displayUniqueFriendList() {
		System.out.println("Start displayUniqueFriendList");
		AddressBook addressBookForUser1 = createUser1Address();
		AddressBook addressBookForUser2 = createUser2Address();
		List<Contact> uniqueContactList = addressBookForUser1.getUniqueContacts(addressBookForUser2);
		System.out.println(uniqueContactList);
		List<Contact> expectedUniqueContacts = getExpectedUniqueContacts();
		Assert.assertEquals(uniqueContactList, expectedUniqueContacts);
		System.out.println("End displayUniqueFriendList");
	}

	/**
	 * Test method which checks the unique friend list functionality. For a given
	 * userId and an address books this method checks if the returned list of
	 * contacts are unique to address books of the user and the passed in
	 * addressbook.
	 * 
	 */
	@Test
	public void displayUniqueFriendListForUser() {
		System.out.println("Start displayUniqueFriendListForUser");
		AddressBook addressBookForUser2 = createUser2Address();
		List<Contact> uniqueContactList = addressBookService.getUniqueContactList("User1", addressBookForUser2);
		System.out.println(uniqueContactList);
		List<Contact> expectedUniqueContacts = getExpectedUniqueContacts();
		Assert.assertEquals(uniqueContactList, expectedUniqueContacts);
		System.out.println("End displayUniqueFriendListForUser");
	}

}
