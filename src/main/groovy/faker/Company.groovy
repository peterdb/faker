package faker


class Company {
	private static Faker.Base delegate = new Faker.Base(Company)
	
	public static String name() {
		delegate.parse('company.name')
	}

	public static String suffix() {
		delegate.fetch('company.suffix')
	}

	// Generate a buzzword-laden catch phrase.
	public static String catchPhrase() {
		delegate.translate('company.buzzwords').collect { list -> list.sample() }.join(' ')
	}

	// When a straight answer won't do, BS to the rescue!
	public static String bs() {
		delegate.translate('company.bs').collect { list -> list.sample() }.join(' ')
	}

}
