package faker

import faker.groovy.ListExtension;

class TTT {
	public static void main(String[] args) {
		println Locale.getDefault()

		Faker.config.locale = Locale.FRENCH
		println Name.name()

		Faker.config.locale = Locale.ENGLISH
		println Name.name()
	}
}
