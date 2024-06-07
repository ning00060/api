package ch02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	int id;
	String name;
	String username;
	String email;
	Address address;
	String phone;
	String website;
	Company company;

	class Address {
		String street;
		String suite;
		String city;
		String zipcode;
		Geo geo;

		class Geo {
			String lat;
			String lng;

		}
	}

	class Company {
		String name;
		String catchPhrase;
		String bs;
	}
}