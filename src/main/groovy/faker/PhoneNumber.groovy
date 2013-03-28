package faker

import faker.internal.FakerSupport;

class PhoneNumber {
    private static FakerSupport support = new FakerSupport(PhoneNumber)

    public static String phoneNumber() {
        return support.getString('phone_number.formats')
    }

    public static String cellPhone() {
        return support.getString("cell_phone.formats")
    }
}
