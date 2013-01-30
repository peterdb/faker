package faker


class Name extends Faker.Base {
	public static String name() {
		return parse(Name, "name.name")
	}
	
	public static String firstName() {
		return fetch("name.first_name")
	}

	public static String lastName() {
		return fetch("name.last_name")
	}

	public static String prefix() {
		return fetch("name.prefix")
	}

	public static String suffix() {
		return fetch("name.suffix")
	}

	public static String title() {
		return fetch('name.title.descriptor') + ' ' + fetch('name.title.level') + ' ' + fetch('name.title.job')
	}
}
