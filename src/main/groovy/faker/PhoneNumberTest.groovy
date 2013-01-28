package faker

class PhoneNumberTest {
	public static void main(String[] args) {
		10.times {
			println PhoneNumber.phoneNumber()
		}
	}
}
