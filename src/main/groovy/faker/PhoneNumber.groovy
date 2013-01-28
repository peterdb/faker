package faker

class PhoneNumber extends Faker.Base {
	public static String phoneNumber() {
		numerify(fetch('phone_number.formats'))
	}

	public static String cellPhone() {
		def formats = translate("cell_phone.formats")

		if(formats) {
			numerify(formats.sample())
		} else {
			numerify(fetch('phone_number.formats'))
		}
	}
}
