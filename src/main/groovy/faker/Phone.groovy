package faker

import faker.internal.FakerSupport;

class Phone {
    private static FakerSupport support = new FakerSupport(Phone)

    public static String number() {
        return support.getString('phone.number')
    }

    public static String mobile() {
        return support.getString("phone.mobile")
    }
}
