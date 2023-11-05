
public class Event implements Comparable<Event> {
	private String title;
	private LinkedList<Contact> contacts;
	private String date;
	private String time;
	private String location;
	private char status;

	public Event(String title, Contact contact, String date, String time, String location,char status) {
		this.title = title;
		this.contacts = new LinkedList<>();
		addContact(contact);
		this.date = date;
		this.time = time;
		this.location = location;
		this.status = status;//check status in schedule E or A
	}

	public void addContact(Contact contact) {
		if(status == 'E' || status=='e')
		contacts.insert(contact);
		else if( status=='A' || status == 'a') {
			if(contacts == null)
				contacts.insert(contact);
			else
				System.out.println("You already have appointment with: "+toString());
		}
	}

	// check if contact in event or not
	public boolean checkContactName(String name) {
		contacts.findfirst();
		for (int i = 0; i < contacts.length(); i++) {
			if (contacts.retrieve().getName().equals(name))
				return true;
			contacts.findfirst();
		}
		return false;
	}

	@Override
	public int compareTo(Event o) {
		return this.title.compareTo(o.title);
	}

	@Override
	public String toString() {
		String string = "Event title: " + title + "\nContact name: ";

		// loop for printing all contacts name in event
		contacts.findfirst();
		for (int i = 0; i < contacts.length(); i++) {
			string += contacts.retrieve().getName() + " ";
			contacts.findnext();
		}

		string += "\nEvent date and time (MM/DD/YYYY HH:MM): " + date + time + "\nEvent location: " + location + "\n";

		return string;
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getLocation() {
		return location;
	}

}

