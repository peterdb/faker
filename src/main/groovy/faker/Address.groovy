package faker

import faker.internal.FakerSupport;


class Address {
    private static FakerSupport support = new FakerSupport(Address)

    public static String city() {
        return support.getString('address.city')
    }

    public static String streetName() {
        return support.getString('address.street_name')
    }

    public static String streetAddress(include_secondary = false) {
        return support.getString('address.street_address') + (include_secondary ? ' ' + secondaryAddress() : '')
    }

    public static String secondaryAddress() {
        return support.getString('address.secondary_address')
    }

    public static String buildingNumber() {
        return support.getString('address.building_number')
    }

    public static String zip() {
        return zipCode()
    }

    public static String postcode() {
        return zipCode()
    }

    public static String zipCode() {
        return support.getString('address.postcode')
    }

    public static String timeZone() {
        return support.getString('address.time_zone')
    }

    public static String streetSuffix() {
        return support.getString('address.street_suffix')
    }
    public static String citySuffix() {
        return support.getString('address.city_suffix')
    }
    public static String cityPrefix() {
        return support.getString('address.city_prefix')
    }
    public static String stateAbbr() {
        return support.getString('address.state_abbr')
    }
    public static String state() {
        return support.getString('address.state')
    }
    public static String country() {
        return support.getString('address.country')
    }

    public static Double latitude() {
        return (support.rnd.nextDouble() * 180) - 90
    }

    public static Double longitude() {
        return (support.rnd.nextDouble() * 360) - 180
    }
}
