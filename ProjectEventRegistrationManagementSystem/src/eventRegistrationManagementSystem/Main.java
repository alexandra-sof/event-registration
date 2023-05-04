package eventRegistrationManagementSystem;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome! Please enter the number of available tickets: ");
		int noTickets=sc.nextInt();
		GuestsList list=new GuestsList(noTickets); 
		boolean quit=false;
		while(!quit) {
			System.out.println("Waiting for a command: (help - Show the list of commands)");
			String command=sc.next();
			switch(command) {
			case "help":
				System.out.println("help - Show the list of commands\n" + 
						"add - Add a new person (registration)\n" + 
						"check - Check if a person is registered to the event\n" + 
						"remove - Delete an existing person from the list\n" + 
						"update - Updates a person's details\n" + 
						"guests - The list of people participating in the event\n" + 
						"waitlist - People on the waiting list\n" + 
						"available - The number of available tickets\n" + 
						"guests_no - The number of people participating in the event\n" + 
						"waitlist_no - The number of people on the waiting list\n" + 
						"subscribe_no - The total number of registered people\n" + 
						"search - Search all guests according to the entered string of characters\n" + 
						"quit - Close the application\n");
				break;
			case "add":
				list.add(sc);
				break;
			case "check":
				list.check(list.authentification(sc));
				break;
			case "remove":
				list.removeGuest(sc);
				break;
			case "update":
				list.update(sc);
				break;
			case "guests":
				list.displayGuestsList();
				break;
			case "waitlist":
				list.displayWaitingList();
				break;
			case "available":
				list.availableTickets();
				break;
			case "guests_no":
				list.noOfGuests();
				break;
			case "waitlist_no":
				list.noOnWaitingList();
				break;
			case "subscribe_no":
				list.totalNo();
				break;
			case "search":
				list.search(sc);
				break;
			case "quit":
				System.out.println("The application closed.");
				sc.close();
				quit=true;
				break;
			default:
				System.out.println("You entered an invalid command.");
				System.out.println("Type \"help\" to view the entire list of commands.");
				break;
			}
		}
	}
}
