package faker

class PhoneNumber {
    private static Faker.Base delegate = new Faker.Base(PhoneNumber)

    public static String phoneNumber() {
        return delegate.numerify(delegate.fetch('phone_number.formats'))
    }

    public static String cellPhone() {
        def formats = delegate.translate("cell_phone.formats")

        if(formats) {
            return delegate.numerify(formats.sample())
        } else {
            return delegate.numerify(delegate.fetch('phone_number.formats'))
        }
    }
}
