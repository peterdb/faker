package faker

import faker.internal.FakerSupport;

class Phone {
    private static FakerSupport support = new FakerSupport(Phone)

    public static String number() {
        return support['phone.number']
    }

    public static String mobile() {
        return support['phone.mobile']
    }
}
