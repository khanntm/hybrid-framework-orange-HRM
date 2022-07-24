package utilities;

import com.github.javafaker.Faker;

public class DataUlti {
	private Faker faker;
	
	public static DataUlti getData() {
		return new DataUlti();
	}
	
	public DataUlti() {
		faker = new Faker();
	}
	
	public String getFirstName() {
		return faker.name().firstName();
	}
	
	public String getEditFirstName() {
		return faker.name().firstName();
	}
	
	public String getLastName() {
		return faker.name().lastName();
	}
	
	public String getEditLastName() {
		return faker.name().lastName();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	public String getEditEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getUserName() {
		return faker.name().username();
	}
	public String getEditUserName() {
		return faker.name().username();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}
	public String getEditPassword() {
		return faker.internet().password();
	}
	
	public String cardNumber() {
		return faker.finance().creditCard();
	}

	public String getAddress() {
		return faker.address().fullAddress();
	}
	
	public String getCity() {
		return faker.address().cityName();
	}
	
	public String getZipCode() {
		return faker.address().zipCode();
	}
	public String getPhoneNumber() {
		return faker.phoneNumber().cellPhone();
	}
	
	public String getRandomText() {
		return faker.random().toString();
	}
	
	
	

}

