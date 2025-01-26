package org.nanotek.meta.identity.rdbms.test;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.junit.jupiter.api.Test;

public class TestInstancioModels {

	public TestInstancioModels() {
	}

	@Test
	void testInstancioRdbmsMetaClass() {
		var meta = RdbmsMetaClassBuilder.buildInstancioRdbmsMetaClass();
		System.out.println(meta.toString());
	}
	
	@Test
	void testInstancioPersonMode() {
		Model<Person> personModel = Instancio.of(Person.class)
			    .ignore(Select.field(Person::getAge))
			    .toModel();

			// Create a person from the model, and additionally ignore the address field
			Person personWithoutAge = Instancio.of(personModel)
			    .create();

			System.out.println(personWithoutAge.toString());
	}
	
	
	class Person {
		private String firstName; 
		private String lastName;
		private Integer age;
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
		}
		
	}
}
