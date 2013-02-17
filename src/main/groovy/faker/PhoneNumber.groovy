package faker

import faker.internal.FakerSupport;

class PhoneNumber {
    private static FakerSupport support = new FakerSupport(PhoneNumber)

    public static String phoneNumber() {
        return support.numerify(support.fetch('phone_number.formats'))
    }

    public static String cellPhone() {
        def formats = support.translate("cell_phone.formats")

        if(formats) {
            return support.numerify(formats.sample())
        } else {
            return support.numerify(support.fetch('phone_number.formats'))
        }
    }
}
