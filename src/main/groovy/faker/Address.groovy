package faker

import faker.internal.FakerSupport;


class Address {
    private static FakerSupport support = new FakerSupport(Address)

    public static String city() {
        return support['address.city']
    }

    public static String streetName() {
        return support['address.street_name']
    }

    public static String streetAddress(boolean include_secondary = false) {
        return support['address.street_address'] + (include_secondary ? ' ' + secondaryAddress() : '')
    }

    public static String secondaryAddress() {
        return support['address.secondary_address']
    }

    public static String buildingNumber() {
        return support['address.building_number']
    }

    public static String zip() {
        return zipCode()
    }

    public static String postcode() {
        return zipCode()
    }

    public static String zipCode() {
        return support['address.postcode']
    }

    public static String timeZone() {
        return support['address.time_zone']
    }

    public static String streetSuffix() {
        return support['address.street_suffix']
    }
    public static String citySuffix() {
        return support['address.city_suffix']
    }
    public static String cityPrefix() {
        return support['address.city_prefix']
    }
    public static String stateAbbr() {
        return support['address.state_abbr']
    }
    public static String state() {
        return support['address.state']
    }
    public static String country() {
        return support['address.country']
    }

    public static Double latitude() {
        return (support.rnd.nextDouble() * 180) - 90
    }

    public static Double longitude() {
        return (support.rnd.nextDouble() * 360) - 180
    }
}
