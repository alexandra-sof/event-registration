package eventRegistrationManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class GuestsList {
	private final int noTickets;
	private ArrayList<Guest> guests;
	private ArrayList<Guest> waiting;

	public GuestsList(int noTickets) {
		this.noTickets=noTickets;
		this.guests=new ArrayList<Guest>(noTickets);
		this.waiting=new ArrayList<Guest>();
	}

	private ArrayList<Guest> getListAllSubscribers(){
		ArrayList<Guest> total=new ArrayList<Guest>();
		for(Guest guest:this.guests) {
			total.add(guest);
		}
		for(Guest guest:this.waiting) {
			total.add(guest);
		}
		return total;
	}

	private int searchByFullName(ArrayList<Guest> list, String fullName) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getFullName().equalsIgnoreCase(fullName)) {
				return i;
			}
		}
		return -1;
	}

	private int searchByEmail(ArrayList<Guest> list, String email) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getEmail().equalsIgnoreCase(email)) {
				return i;
			}
		}
		return -1;
	}

	private int searchByPhoneNumber(ArrayList<Guest> list, String phoneNumber) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getPhoneNumber().equals(phoneNumber)) {
				return i;
			}
		}
		return -1;
	}

	private boolean validatePhoneNumber(String phoneNumber) {
		if(phoneNumber.length()!=12) {
			return false;
		}
		if(phoneNumber.charAt(0)!='+') {
			return false;
		}
		if(phoneNumber.charAt(1)!='4') {
			return false;
		}
		if(phoneNumber.charAt(2)!='0') {
			return false;
		}
		if(phoneNumber.charAt(3)!='7') {
			return false;
		}
		for(int i=4; i<phoneNumber.length(); i++) {
			if(!Character.isDigit(phoneNumber.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private int authentificationByFullName(Scanner sc) {
		System.out.println("Enter last name: ");
		String lastName=sc.next();
		System.out.println("Enter first name: ");
		String firstName=sc.next();
		return searchByFullName(this.getListAllSubscribers(), firstName+" "+lastName);
	}

	private int authentificationByEmail(Scanner sc) {
		System.out.println("Enter e-mail: ");
		String email=sc.next();
		return searchByEmail(this.getListAllSubscribers(), email);
	}

	private int authentificationByPhoneNumber(Scanner sc) {
		System.out.println("Enter phone number (format \"+40733386463\"):");
		String phoneNumber=sc.next();
		while(!this.validatePhoneNumber(phoneNumber)) {
			System.out.println("Invalid format!");
			System.out.println("Enter phone number (format \"+40733386463\"):");
			phoneNumber=sc.next();
		}
		return searchByPhoneNumber(this.getListAllSubscribers(), phoneNumber);
	}

	int authentification(Scanner sc) {
		System.out.println("Choose the authentification method by typing:" +
				"\n\t\"1\" - Name" + 
				"\n\t\"2\" - Email" + 
				"\n\t\"3\" - Phone number");
		String optionNo=sc.next();
		int idx=-1;
		switch(optionNo){
		case "1":
			idx=this.authentificationByFullName(sc);
			return idx;
		case "2":
			idx=this.authentificationByEmail(sc);
			return idx;
		case "3":
			idx=this.authentificationByPhoneNumber(sc);
			return idx;
		default:
			System.out.println("Error. The option entered does not exist.");
			return -2;
		}
	}

	int check(int idx) {
		if (idx>=0){
			if(idx<this.guests.size()) {
				System.out.println("The person is registered for the event.");
			} else {
				System.out.println("The person is number " + (idx-guests.size()+1) 
						+" on the waiting list.");
			}
		} else if(idx==-1) {
			System.out.println("The person is not registered for the event.");
		} else {}
		return idx;
	}

	int add(Scanner sc) {
		System.out.println("A new person is being added…");		
		System.out.println("Enter last name:");
		String lastName=sc.next();
		System.out.println("Enter first name:");
		String firstName=sc.next();
		int idx=searchByFullName(this.getListAllSubscribers(), firstName+" "+lastName);
		if(idx>=0) {
			this.check(idx);
			return -1;
		}
		System.out.println("Enter e-mail:");
		String email=sc.next();
		idx=searchByEmail(this.getListAllSubscribers(), email);
		if(idx>=0) {
			this.check(idx);
			return -1;
		}
		System.out.println("Enter phone number (format \"+40733386463\"):");
		String phoneNumber=sc.next();
		while(!this.validatePhoneNumber(phoneNumber)) {
			System.out.println("Invalid format!");
			System.out.println("Enter phone number (format \"+40733386463\"):");
			phoneNumber=sc.next();
		}
		idx=searchByPhoneNumber(this.getListAllSubscribers(), phoneNumber);
		if(idx>=0) {
			this.check(idx);
			return -1;
		}
		Guest guest=new Guest(lastName, firstName, email, phoneNumber);
		if(guests.size()<noTickets) {
			guests.add(guest);
			System.out.println("Congratulations, " + guest.getFullName()+ "! "
					+ "Your place at the event is confirmed. We look forward to seeing you!");
			return 0;
		} else {
			waiting.add(guest);
			System.out.println("Person " + guest.getFullName()+" is number " 
					+ waiting.size() + " on the waiting list.");
			return waiting.size();
		}
	}

	boolean removeGuest(Scanner sc) {
		System.out.println("A person is being deleted from the list…");
		int idx=this.authentification(sc);
		if(idx==-2) {
			return false;
		} else if(idx==-1) {
			System.out.println("Error: The person is not registered for the event.");
			return false;
		} else {
			if(idx<this.guests.size()) {
				this.guests.remove(idx);
				if(this.waiting.size()>0) {
					this.guests.add(this.waiting.get(0));
					System.out.println("Congratulations, " + waiting.get(0).getFullName()+ "! "
							+ "Your place at the event is confirmed. We look forward to seeing you!");
					this.waiting.remove(0);
				}
				System.out.println("The person was successfully deleted from the list.");
				return true;
			} else {
				this.waiting.remove(idx-this.guests.size());
				System.out.println("The person was successfully deleted from the list.");
				return true;
			}
		}			
	}

	boolean update(Scanner sc) {
		System.out.println("Updating a person's details...");
		int idx=this.authentification(sc);
		if(idx>=0) {
			System.out.println("Choose the field to update, by typing:" +
					"\n\t\"1\" - Last Name" + 
					"\n\t\"2\" - First Name" + 
					"\n\t\"3\" - Email" + 
					"\n\t\"4\" - Phone number");
			int optionForUpdate=sc.nextInt();
			switch(optionForUpdate) {
			case 1:
				System.out.println("Enter last name:");
				String newLastName=sc.next();
				if(idx<guests.size()) {
					guests.get(idx).setLastName(newLastName);
				} else {
					waiting.get(idx-guests.size()).setLastName(newLastName);
				}
				System.out.println("Update done.");
				return true;
			case 2:
				System.out.println("Enter first name:");
				String newFirstName=sc.next();
				if(idx<guests.size()) {
					guests.get(idx).setFirstName(newFirstName);
				} else {
					waiting.get(idx-guests.size()).setFirstName(newFirstName);
				}
				System.out.println("Update done.");
				return true;
			case 3:
				System.out.println("Enter email:");
				String newEmail=sc.next();
				if(idx<guests.size()) {
					guests.get(idx).setEmail(newEmail);
				} else {
					waiting.get(idx-guests.size()).setEmail(newEmail);
				}
				System.out.println("Update done.");
				return true;
			case 4:
				System.out.println("Enter phone number (format \"+40733386463\")");
				String newPhoneNumber=sc.next();
				while(!this.validatePhoneNumber(newPhoneNumber)) {
					System.out.println("Invalid format!");
					System.out.println("Enter phone number (format \"+40733386463\"):");
					newPhoneNumber=sc.next();
				}
				if(idx<guests.size()) {
					guests.get(idx).setPhoneNumber(newPhoneNumber);
				} else {
					waiting.get(idx-guests.size()).setPhoneNumber(newPhoneNumber);
				}
				System.out.println("Update done.");
				return true;
			default:
				System.out.println("Error. The option entered does not exist.");
				return false;
			}
		} else if (idx==-1){
			System.out.println("Error: The person is not registered for the event.");
			return false;
		} else {
			return false;
		}
	}

	void displayGuestsList() {
		if(this.guests.size()==0) {
			System.out.println("No participant registered…");
		} else {
			for(int i=0; i<this.guests.size(); i++) {
				System.out.println(i+1 + ". " + guests.get(i).toString());
			}
		}
	}

	void displayWaitingList() {
		if(this.waiting.size()==0) {
			System.out.println("The waiting list is empty…");
		} else {
			for(int i=0; i<this.waiting.size(); i++) {
				System.out.println(i+1+ ". " + waiting.get(i).toString());
			}
		}
	}

	int availableTickets() {
		int availableTickets=this.noTickets-this.guests.size();
		System.out.println("Number of available tickets: " + availableTickets);
		return availableTickets;
	}

	int noOfGuests() {
		int noOfGuests=this.guests.size();
		System.out.println("Number of participants: " + noOfGuests);
		return noOfGuests;
	}

	int noOnWaitingList() {
		int noOnWaitingList=this.waiting.size();
		System.out.println("Waiting list size: " + noOnWaitingList);
		return noOnWaitingList;
	}	

	int totalNo() {
		int totalNo=this.guests.size()+this.waiting.size();
		System.out.println("Total number of registered people: " + totalNo);
		return totalNo;
	}

	void search(Scanner sc) {
		System.out.println("Enter the string of characters you want to search for:");
		String s=sc.next().toLowerCase();
		ArrayList<Guest> searchResult=new ArrayList<Guest>();
		ArrayList<Guest> total=this.getListAllSubscribers();
		for(Guest guest:total) {
			int idx=guest.toString().toLowerCase().indexOf(s);
			if(idx>=0) {
				searchResult.add(guest);
			}			
		}
		if(searchResult.size()>0) {
			System.out.println("Search results:");
			for(Guest guest:searchResult) {
				System.out.println(guest.toString());
			}
		} else {
			System.out.println("The search returned no results.");
		}
	}
}
