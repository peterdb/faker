package faker


class Company extends Faker.Base {

	public static String name() {
		parse(Company, 'company.name')
	}

	public static String suffix() {
		fetch('company.suffix')
	}

	// Generate a buzzword-laden catch phrase.
	public static String catchPhrase() {
		translate('company.buzzwords').collect { list -> list.sample() }.join(' ')
	}

	// When a straight answer won't do, BS to the rescue!
	public static String bs() {
		translate('company.bs').collect { list -> list.sample() }.join(' ')
	}

}
