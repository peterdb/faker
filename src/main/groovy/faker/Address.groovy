package faker


class Address extends Faker.Base {
	public static String city() {
		return parse(Address, 'address.city')
	}

	public static String streetName() {
		return parse(Address, 'address.street_name')
	}

	public static String streetAddress(include_secondary = false) {
		return numerify(parse(Address, 'address.street_address') + (include_secondary ? ' ' + secondaryAddress() : ''))
	}

	public static String secondaryAddress() {
		return numerify(fetch('address.secondary_address'))
	}

	public static String buildingNumber() {
		return bothify(fetch('address.building_number'))
	}

	public static String zip() {
		return zipCode()
	}

	public static String postcode() {
		return zipCode()
	}

	public static String zipCode() {
		return bothify(fetch('address.postcode'))
	}

	public static String timeZone() {
		return bothify(fetch('address.time_zone'))
	}

	public static String streetSuffix() {
		return fetch('address.street_suffix')
	}
	public static String citySuffix() {
		return fetch('address.city_suffix')
	}
	public static String cityPrefix() {
		return fetch('address.city_prefix')
	}
	public static String stateAbbr() {
		return fetch('address.state_abbr')
	}
	public static String state() {
		return fetch('address.state')
	}
	public static String country() {
		return fetch('address.country')
	}

	public static String latitude() {
		return (rnd.nextDouble() * 180) - 90
	}

	public static String longitude() {
		return (rnd.nextDouble() * 360) - 180
	}
}
