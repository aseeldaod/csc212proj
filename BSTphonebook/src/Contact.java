
public class Contact implements Comparable<Contact> {

	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String birthday;
	private String notes;
	private LinkedList<Event> eventList;

	public Contact(String name, String phoneNumber, String email, String address, String birthday, String notes) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.notes = notes;
		eventList = new LinkedList<Event>();
	}

	public void addEvent(Event event) {
		eventList.insert(event);
	}

	@Override
	public int compareTo(Contact o) {
		return this.name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "Name:" + name + "\nPhoneNumber:" + phoneNumber + "\nEmail addres:" + email + "\nAddress:" + address
				+ "\nBirthday:" + birthday + "\nnotes:" + notes;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getNotes() {
		return notes;
	}

	public LinkedList<Event> getEventList() {
		return eventList;
	}

}
