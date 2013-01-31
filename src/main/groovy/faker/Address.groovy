package faker


class Address {
	private static Faker.Base delegate = new Faker.Base(Address)

	public static String city() {
		return delegate.parse('address.city')
	}

	public static String streetName() {
		return delegate.parse('address.street_name')
	}

	public static String streetAddress(include_secondary = false) {
		return delegate.numerify(delegate.parse('address.street_address') + (include_secondary ? ' ' + secondaryAddress() : ''))
	}

	public static String secondaryAddress() {
		return delegate.numerify(delegate.fetch('address.secondary_address'))
	}

	public static String buildingNumber() {
		return delegate.bothify(delegate.fetch('address.building_number'))
	}

	public static String zip() {
		return zipCode()
	}

	public static String postcode() {
		return zipCode()
	}

	public static String zipCode() {
		return delegate.bothify(delegate.fetch('address.postcode'))
	}

	public static String timeZone() {
		return delegate.bothify(delegate.fetch('address.time_zone'))
	}

	public static String streetSuffix() {
		return delegate.fetch('address.street_suffix')
	}
	public static String citySuffix() {
		return delegate.fetch('address.city_suffix')
	}
	public static String cityPrefix() {
		return delegate.fetch('address.city_prefix')
	}
	public static String stateAbbr() {
		return delegate.fetch('address.state_abbr')
	}
	public static String state() {
		return delegate.fetch('address.state')
	}
	public static String country() {
		return delegate.fetch('address.country')
	}

	public static String latitude() {
		return (delegate.rnd.nextDouble() * 180) - 90
	}

	public static String longitude() {
		return (delegate.rnd.nextDouble() * 360) - 180
	}
}
