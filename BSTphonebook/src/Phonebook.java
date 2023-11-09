
public class Phonebook {
	public static BST<Contact> contactTree = new BST<Contact>();
	public static LinkedList<Event> eventList = new LinkedList<Event>();
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to the BST Phonebook!");
		int choice;
		do {
			System.out.println("Please choose an option:");
			System.out.println("1. Add a contact");
			System.out.println("2. Search for a contact");
			System.out.println("3. Delete a contact");
			System.out.println("4. Schedule an event/appointment");
			System.out.println("5. Print event details");
			System.out.println("6. Print contacts by first name");
			System.out.println("7. Print all events alphabetically");
			System.out.println("8. Exit");
			System.out.println("");
			System.out.print("Enter your choice:");
			choice = input.nextInt();
			input.nextLine();// for garbage
			System.out.println("");

			switch (choice) {
			// add contact
			case 1:
				addContactToBST();
				break;

			// search contact
			case 2:
				searchContact();
				break;

			// delete contact
			case 3:
				deleteContact();
				break;

			// schedule event
			case 4:
				scheduleEvent();
				break;

			// Print event details
			case 5:
				System.out.println("Enter search criteria:");
				System.out.println("1. contact name");
				System.out.println("2. Event tittle");
				System.out.println("");
				System.out.print("Enter your choice:");
				int searchChoice = input.nextInt();
				input.nextLine();// for garbage

				switch (searchChoice) {
				case 1:
					// search event by name
					System.out.println("");
					System.out.print("Enter the contact name:");
					String searchName = input.nextLine();
					printSearchedEvent(searchName);
					break;

				case 2:
					// search event by title
					System.out.println("");
					System.out.print("Enter the event title:");
					String searchTitle = input.nextLine();
					printSearchedEvent(searchTitle);
					break;

				default:
					System.out.println("Invalid Input!");

				}
				break;

			// Print contacts by first name
			case 6:
				printAllContactShareFirstName();
				break;

			// Print all events alphabetically
			case 7:
				PrintAllEventsAlphabetically();
				break;

			case 8:
				System.out.println("Goodbye!");
				break;

			default:
				System.out.println("Wrong input! reEnter");

			}
		} while (choice != 8);
	}

	// add contact to contactList
	public static void addContactToBST() {
		String name, phoneNumber, email, address, birthday, notes;

		System.out.print("Enter the contact's name:");
		name = input.nextLine();

		System.out.print("Enter the contact's phone number:");
		phoneNumber = input.nextLine();
		System.out.print("");

		System.out.print("Enter the contact's email address:");
		email = input.nextLine();
		System.out.print("");

		System.out.print("Enter the contact's address:");
		address = input.nextLine();
		System.out.print("");

		System.out.print("Enter the contact's birthday:");
		birthday = input.nextLine();
		System.out.print("");

		System.out.print("Enter any notes for the contact:");
		notes = input.nextLine();
		System.out.print("");
		System.out.println("");

		Contact contact = new Contact(name, phoneNumber, email, address, birthday, notes);
		// check if contact is unique
		if (contactTree.findKey(name)) {
			System.out.println("Contact already exist!");
			System.out.println("");
		} else if (contactTree.checkPhoneNumber(phoneNumber)) {
			System.out.println("Contact already exist!");
			System.out.println("");
		} else {// contact is unique ,so we can insert it in BST
			contactTree.insert(name, contact);
			System.out.println("Contact added successfully!");
			System.out.println("");
		}
	}

	public static void searchContact() {

		System.out.println("Enter search criteria:");
		System.out.println("1. Name");
		System.out.println("2. Phone Number");
		System.out.println("3. Email Address");
		System.out.println("4. Address");
		System.out.println("5. Birthday");
		System.out.println("");
		System.out.print("Enter your choice:");
		int searchChoice = input.nextInt();
		input.nextLine();// for garbage
		System.out.println("");

		switch (searchChoice) {
		case 1:
			System.out.print("Enter the contact's name:");
			String name = input.nextLine();

			BST<Contact> searchContactByName = contactTree.searchContactByInformation(name);
			if (!searchContactByName.empty()) {
				System.out.println("");
				System.out.println("Contact found!");

				searchContactByName.printBST();

			} else {
				System.out.println("");
				System.out.println("Contact doesn't exist!");
				System.out.println("");
			}
			break;

		case 2:
			System.out.print("Enter the contact's  Phone Number:");
			String PhoneNumber = input.nextLine();

			BST<Contact> searchContactByPhoneNumber = contactTree.searchContactByInformation(PhoneNumber);

			if (!searchContactByPhoneNumber.empty()) {
				System.out.println("");
				System.out.println("Contact found!");

				searchContactByPhoneNumber.printBST();

			} else {
				System.out.println("");
				System.out.println("Contact doesn't exist!");
				System.out.println("");
			}

			break;
		case 3:
			System.out.print("Enter the contact's  Email Address:");
			String EmailAddress = input.nextLine();

			BST<Contact> searchContactByEmailAddress = contactTree.searchContactByInformation(EmailAddress);

			if (!searchContactByEmailAddress.empty()) {
				System.out.println("");
				System.out.println("Contacts found!");

				searchContactByEmailAddress.printBST();

			} else {
				System.out.println("");
				System.out.println("Contact doesn't exist!");
				System.out.println("");
			}

			break;
		case 4:
			System.out.print("Enter the contact's  Address:");
			String Address = input.nextLine();

			BST<Contact> searchContactByAddress = contactTree.searchContactByInformation(Address);

			if (!searchContactByAddress.empty()) {
				System.out.println("");
				System.out.println("Contacts found!");

				searchContactByAddress.printBST();

			} else {
				System.out.println("");
				System.out.println("Contact doesn't exist!");
				System.out.println("");
			}

			break;
		case 5:
			System.out.print("Enter the contact's  Birthday:");
			String Birthday = input.nextLine();

			BST<Contact> searchContactByBirthday = contactTree.searchContactByInformation(Birthday);

			if (!searchContactByBirthday.empty()) {
				System.out.println("");
				System.out.println("Contact found!");

				searchContactByBirthday.printBST();

			} else {
				System.out.println("");
				System.out.println("Contact doesn't exist!");
				System.out.println("");
			}
			break;
		default:
			System.out.println("Invalid Input!");
		}
	}

	public static void deleteContact() {
		System.out.print("Enter contact name:");
		String name = input.nextLine();

		BST<Contact> contactsToDelete = contactTree.searchContactByInformation(name);

		// check if contact exist
		if (!contactsToDelete.empty()) {

			LinkedList<Event> appointmentsToDelete = new LinkedList<>();

			// collect all appointments with the contact to be deleted
			eventList.findfirst();
			for (int i = 0; i < eventList.length(); i++) {
				Event event = eventList.retrieve();
				if (event.isAppointmen() && event.checkContactName(name)) {
					appointmentsToDelete.insert(event);
				}
				eventList.findnext();
			}

			// Delete all appointments with the contact
			if (!appointmentsToDelete.empty()) {

				appointmentsToDelete.findfirst();
				for (int i = 0; i < appointmentsToDelete.length(); i++) {
					Event appointment = appointmentsToDelete.retrieve();
					eventList.delete(appointment);
					appointmentsToDelete.findnext();
				}
			}

			Contact contact = contactsToDelete.retrieve();

			// delete contact from event's contacts list if it exist
			eventList.findfirst();
			for (int i = 0; i < eventList.length(); i++) {

				Event event = eventList.retrieve();
				event.deleteContact(contact);

				eventList.findnext();
			}

			// delete events that it's contacts became empty
			eventList.findfirst();
			for (int i = 0; i < eventList.length(); i++) {
				Event event = eventList.retrieve();
				if (event.getContacts().empty()) {
					eventList.delete(event);
				}
				eventList.findnext();
			}

			// Delete the contact
			contactTree.remove_key(name);
			System.out.println("");
			System.out.println("Contact deleted successfully!");
			System.out.println("");
		} else {
			System.out.println("");
			System.out.println("Contact doesn't exist!");
			System.out.println("");
		}

	}

	public static void scheduleEvent() {

		System.out.println("Enter type:");
		System.out.println("1. event");
		System.out.println("2. appointment");
		System.out.println("");
		System.out.print("Enter your choice:");
		int searchChoice = input.nextInt();
		input.nextLine();// for garbage
		System.out.println("");

		String title, name, date, time, location;

		System.out.print("Enter event title:");
		title = input.nextLine();

		System.out.print("Enter contact name:");
		name = input.nextLine();

		System.out.print("Enter event date and time (MM/DD/YYYY HH:MM):");
		date = input.next();
		time = input.nextLine();

		System.out.print("Enter event location:");
		location = input.nextLine();

		BST<Contact> CheckContact = contactTree.searchContactByInformation(name);

		// check if contact exist
		if (!CheckContact.empty()) {

			Contact contact = CheckContact.retrieve();

			switch (searchChoice) {
			case 1:
				Event event = new Event(title, contact, date, time, location, 'E');

				// check if event has contradiction
				boolean check1 = true;
				eventList.findfirst();
				for (int i = 0; i < eventList.length(); i++) {
					if (eventList.retrieve().getTime().equals(time) && eventList.retrieve().getDate().equals(date)) {
						check1 = false;
						return;
					}
					eventList.findnext();
				}

				if (check1) {
					event.addContact(contact);
					contact.addEvent(event);
					eventList.insert(event);
					System.out.println("");
					System.out.println("Event scheduled successfully!");
					System.out.println("");
				} else {
					System.out.println("");
					System.out.println("Event contradiction!");
					System.out.println("");
				}

				break;
			case 2:
				Event appointment = new Event(title, contact, date, time, location, 'A');

				// check if appointment has contradiction
				boolean check2 = true;
				eventList.findfirst();
				for (int i = 0; i < eventList.length(); i++) {
					if (eventList.retrieve().getTime().equals(time) && eventList.retrieve().getDate().equals(date)) {
						check2 = false;
						return;
					}
					eventList.findnext();
				}

				if (check2) {
					appointment.addContact(contact);
					contact.addEvent(appointment);
					eventList.insert(appointment);
					System.out.println("");
					System.out.println("Appointment scheduled successfully!");
					System.out.println("");
				} else {
					System.out.println("");
					System.out.println("Appointment contradiction!");
					System.out.println("");
				}
				break;

			default:
				System.out.println("Invalid Input!");
			}
		} else {
			System.out.println("");
			System.out.println("Contact doesn't exist!");
			System.out.println("");
		}
	}

	public static void printSearchedEvent(String info) {
		boolean found = false;
		// check event list not empty
		if (eventList.empty()) {
			System.out.println("");
			System.out.println("You don't have scheduled events or appointments, try adding one.\n");
			return;

		} else {

			eventList.findfirst();
			// search event by contact name or event title and print it
			for (int i = 0; i < eventList.length(); i++) {
				if (eventList.retrieve().checkContactName(info)
						|| eventList.retrieve().getTitle().equalsIgnoreCase(info)) {
					System.out.println("");
					System.out.println("Event found!");
					System.out.println(eventList.retrieve().toString());
					found = true;
				}
				eventList.findnext();
			}
		}

		if (!found) {
			System.out.println("");
			System.out.println("You don't have scheduled events or appointments with this information!");
		}
	}

	// Print all events or appointments alphabetically in O(n)
	public static void PrintAllEventsAlphabetically() {
		if (eventList.empty()) {
			System.out.println("");
			System.out.println("You don't have scheduled events or appointments, try adding one.");
			return;
		} else {
			eventList.findfirst();
			for (int i = 0; i < eventList.length(); i++) {
				System.out.println(eventList.retrieve().toString());
				eventList.findnext();
			}
		}

	}

	public static void printAllContactShareEvent() {

		System.out.print("Enter Event title:");
		String eventTitle = input.nextLine();

		boolean find = false;
		// check if event exist
		for (int i = 0; i < eventList.length(); i++) {
			if (eventList.retrieve().getTitle().equalsIgnoreCase(eventTitle))
				find = true;
			eventList.findnext();
		}

		if (find) {
			eventList.findfirst();

			for (int i = 0; i < eventList.length(); i++) {
				if (eventList.retrieve().getTitle().equalsIgnoreCase(eventTitle))
					System.out.println(eventList.retrieve().toString());
				eventList.findnext();
			}
		} else {
			System.out.println("");
			System.out.println("event doesn't exist!");
			System.out.println("");
		}
	}

	public static void printAllContactShareFirstName() {
		System.out.print("Enter the first name: ");
		String name = input.next();
		System.out.println();

		if (!contactTree.empty()) {

			LinkedList<Contact> firstNameList = contactTree.searchContactByFirstName(name);
			if (firstNameList.empty()) {
				System.out.println("");
				System.out.println("No contacts found with same first name.");
				System.out.println("");
				return;
			} else {
				System.out.println("Contacts found!");
				firstNameList.findfirst();
				for (int i = 0; i < firstNameList.length(); i++) {
					System.out.println(firstNameList.retrieve().toString());
					System.out.println();
					firstNameList.findnext();
				}
			}
		} else {

			System.out.println("");
			System.out.println("Contact doesn't exist!");
			System.out.println("");
		}

	}
}
