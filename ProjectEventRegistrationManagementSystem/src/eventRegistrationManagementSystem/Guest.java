package eventRegistrationManagementSystem;

public class Guest {
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;

	public Guest(String lastName, String firstName, String email, String phoneNumber) {
		this.lastName=lastName;
		this.firstName=firstName;
		this.email=email;
		this.phoneNumber=phoneNumber;
	}

	String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	String getEmail() {
		return this.email;
	}

	String getPhoneNumber() {
		return this.phoneNumber;
	}

	void setLastName(String lastName) {
		this.lastName=lastName;
	}

	void setFirstName(String firstName) {
		this.firstName=firstName;
	}

	void setEmail(String email) {
		this.email=email;
	}

	void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}

	@Override
	public String toString() {
		return this.getFullName() + ", " + this.email + ", " + this.phoneNumber;
	}

}
