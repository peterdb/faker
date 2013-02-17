package faker

import faker.internal.FakerSupport;


class Address {
    private static FakerSupport support = new FakerSupport(Address)

    public static String city() {
        return support.parse('address.city')
    }

    public static String streetName() {
        return support.parse('address.street_name')
    }

    public static String streetAddress(include_secondary = false) {
        return support.numerify(support.parse('address.street_address') + (include_secondary ? ' ' + secondaryAddress() : ''))
    }

    public static String secondaryAddress() {
        return support.numerify(support.fetch('address.secondary_address'))
    }

    public static String buildingNumber() {
        return support.bothify(support.fetch('address.building_number'))
    }

    public static String zip() {
        return zipCode()
    }

    public static String postcode() {
        return zipCode()
    }

    public static String zipCode() {
        return support.bothify(support.fetch('address.postcode'))
    }

    public static String timeZone() {
        return support.bothify(support.fetch('address.time_zone'))
    }

    public static String streetSuffix() {
        return support.fetch('address.street_suffix')
    }
    public static String citySuffix() {
        return support.fetch('address.city_suffix')
    }
    public static String cityPrefix() {
        return support.fetch('address.city_prefix')
    }
    public static String stateAbbr() {
        return support.fetch('address.state_abbr')
    }
    public static String state() {
        return support.fetch('address.state')
    }
    public static String country() {
        return support.fetch('address.country')
    }

    public static Double latitude() {
        return (support.rnd.nextDouble() * 180) - 90
    }

    public static Double longitude() {
        return (support.rnd.nextDouble() * 360) - 180
    }
}
